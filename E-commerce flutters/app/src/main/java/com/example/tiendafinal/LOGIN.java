package com.example.tiendafinal;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class LOGIN extends AppCompatActivity {
    // Declaración de variables
    private ProgressBar progressBar;
    private Button btnLogin;
    private EditText etUsername;
    private EditText etPassword;
    private List<Usuario> usuarios; // Lista para almacenar usuarios

    // Método onCreate, llamado al iniciar la actividad
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login); // Establecer el diseño de la actividad

        // Asignación de vistas a las variables
        progressBar = findViewById(R.id.progressBar);
        btnLogin = findViewById(R.id.btn_login);
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);

        // Inicialización de la lista de usuarios con algunos ejemplos
        usuarios = new ArrayList<>();
        usuarios.add(new Usuario("Hola", "Contraseña1"));
        usuarios.add(new Usuario("Hola2", "Contraseña2"));
        usuarios.add(new Usuario("h", "h"));

        // Deshabilitar el botón de inicio de sesión al principio
        btnLogin.setEnabled(false);

        // Crear un TextWatcher para habilitar el botón cuando ambos campos estén llenos
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Verificar si ambos campos están llenos para habilitar el botón
                if (!etUsername.getText().toString().isEmpty() && !etPassword.getText().toString().isEmpty()) {
                    btnLogin.setEnabled(true);
                } else {
                    btnLogin.setEnabled(false);
                }
            }
        };

        // Asignar el TextWatcher a los campos de nombre de usuario y contraseña
        etUsername.addTextChangedListener(textWatcher);
        etPassword.addTextChangedListener(textWatcher);

        // Configurar el OnClickListener para el botón de inicio de sesión
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mostrar la animación de carga
                progressBar.setVisibility(View.VISIBLE);
                Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale_animation);
                btnLogin.startAnimation(anim);
                btnLogin.setEnabled(false); // Deshabilitar el botón durante la autenticación

                // Simular una autenticación que tarda 2 segundos
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        String username = etUsername.getText().toString();
                        String password = etPassword.getText().toString();

                        boolean credencialesCorrectas = false;
                        // Verificar las credenciales con los usuarios de ejemplo
                        for (Usuario usuario : usuarios) {
                            if (usuario.getUsername().equals(username) && usuario.getPassword().equals(password)) {
                                credencialesCorrectas = true;
                                break;
                            }
                        }

                        // Mostrar la siguiente actividad si las credenciales son correctas, o un mensaje de error
                        if (credencialesCorrectas) {
                            Intent intent = new Intent(LOGIN.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(LOGIN.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                        }

                        // Ocultar la animación de carga y habilitar el botón de inicio de sesión
                        progressBar.setVisibility(View.GONE);
                        btnLogin.setEnabled(true);
                    }
                }, 2000); // Retraso de 2 segundos para simular la autenticación
            }
        });
    }
}
