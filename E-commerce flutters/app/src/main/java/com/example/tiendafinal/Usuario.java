package com.example.tiendafinal;

// Definición de la clase Usuario
public class Usuario {
    private String username; // Nombre de usuario
    private String password; // Contraseña

    // Constructor para inicializar un usuario con un nombre de usuario y contraseña
    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Método para obtener el nombre de usuario
    public String getUsername() {
        return username;
    }

    // Método para obtener la contraseña
    public String getPassword() {
        return password;
    }
}
