package com.example.tiendafinal;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class carrito extends AppCompatActivity {


//    private List<Producto> listaProductos; // Lista para almacenar los productos en el carrito

    // Otros métodos y variables...

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);

        // Inicializa la lista de productos
//        listaProductos = new ArrayList<>();
//
        // Otros métodos...
    }

    private void agregarProductoAlCarrito(Context producto) {
        // Agrega el producto a la lista del carrito
     //   listaProductos.add(producto);
    }
}

