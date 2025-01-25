package com.example.tiendafinal;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tiendafinal.SOPORTE.CBaseDatos;

public class DetalleProducto extends AppCompatActivity {

    private CBaseDatos baseDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_producto);

        // Inicializar la instancia de la base de datos
        baseDatos = new CBaseDatos(this, "dbtiendaFi", null, 1);

        // Obtener el ID del producto de los extras del Intent
        int productoId = getIntent().getIntExtra("productoId", -1);

        // Verificar si se recibió un ID válido
        if (productoId != -1) {
            // Obtener una instancia de la base de datos en modo lectura
            SQLiteDatabase db = baseDatos.getReadableDatabase();

            // Realizar una consulta para obtener los detalles del producto
            Cursor cursor = db.rawQuery("SELECT * FROM Productos WHERE codProducto = ?", new String[]{String.valueOf(productoId)});

            // Verificar si se encontraron resultados
            if (cursor.moveToFirst()) {
                // Obtener los datos del producto del cursor
                @SuppressLint("Range") String nombreProducto = cursor.getString(cursor.getColumnIndex("producto"));
                @SuppressLint("Range") int precioProducto = cursor.getInt(cursor.getColumnIndex("precioUnidad"));
                @SuppressLint("Range") String talla = cursor.getString(cursor.getColumnIndex("talla"));
                @SuppressLint("Range") String nombreImagen = cursor.getString(cursor.getColumnIndex("img"));
                @SuppressLint("Range") int codCategoria = cursor.getInt(cursor.getColumnIndex("codCategoria"));
                @SuppressLint("Range") int codMarca = cursor.getInt(cursor.getColumnIndex("codMarca"));






                // Obtener la categoría del producto
                String categoria = obtenerCategoria(codCategoria);
                String marca = obtenerMarca(codMarca);
                // Mostrar los detalles del producto en las vistas correspondientes
                mostrarDetallesProducto(nombreProducto, precioProducto, nombreImagen, talla, marca, categoria);
            }

            // Cerrar el cursor y la base de datos
            cursor.close();
            db.close();

        }

    }



    @SuppressLint("Range")
    private String obtenerCategoria(int codCategoria) {
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT Categoria FROM Categoria WHERE codCategoria = ?", new String[]{String.valueOf(codCategoria)});
        String categoria = "";
        if (cursor.moveToFirst()) {
            categoria = cursor.getString(cursor.getColumnIndex("Categoria"));
        }
        cursor.close();
        db.close();
        return categoria;
    }
    @SuppressLint("Range")
    private String obtenerMarca(int codMarca) {
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT Marca FROM Marca WHERE codMarca = ?", new String[]{String.valueOf(codMarca)});
        String marca = "";
        if (cursor.moveToFirst()) {
            marca = cursor.getString(cursor.getColumnIndex("Marca"));
        }
        cursor.close();
        db.close();
        return marca;
    }
    private void mostrarDetallesProducto(String nombreProducto, int precioProducto, String talla, String nombreImagen, String categoria,String marca) {
        // Obtener referencias a las TextViews correspondientes en el layout de la actividad
        TextView textViewNombreProducto = findViewById(R.id.textViewNombreProducto);
        TextView textViewPrecioProducto = findViewById(R.id.textViewPrecioProducto);
        ImageView textViewTalla = findViewById(R.id.imageView);
        TextView imageViewProduct = findViewById(R.id.textViewTalla);
        TextView textViewCategoria = findViewById(R.id.textViewMarca);
        TextView textViewMarca = findViewById(R.id.textViewCategoria);


        // Establecer los valores de los TextViews con los datos del producto
        textViewNombreProducto.setText(nombreProducto);
        textViewPrecioProducto.setText("$" + precioProducto);

        // Cargar la imagen del producto en el ImageView


        // Asignar los valores de talla, marca y categoría a las TextViews correspondientes
        imageViewProduct.setText("Talla: " + nombreImagen);
        int idImagen = getResources().getIdentifier(talla, "drawable", getPackageName());
        textViewTalla.setImageResource(idImagen);
        textViewCategoria.setText("Marca:" + categoria);
        textViewMarca.setText("Categoría:  " + marca);
        Button botonCarrito = findViewById(R.id.btncomprar);
        botonCarrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un intent para abrir la actividad del carrito
                Intent intent = new Intent(DetalleProducto.this, carrito.class);
                // Pasar el ID del producto como extra al Intent
                intent.putExtra("productoId",nombreProducto);
                // Abrir la actividad del carrito
                startActivity(intent);
            }
        });
    }

}