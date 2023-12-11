package com.example.blockdenotas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Método Botón Login
    public void Login(View v){
        Intent login = new Intent(MainActivity.this, Login.class);
        startActivity(login);
    }

    //Método Botón Notas
    public void Notas(View v){
        Intent notas=new Intent(MainActivity.this, Notas.class);
        startActivity(notas);
    }

    //Método Botón Diario
    public void Diario(View v){
        Intent diario=new Intent(MainActivity.this, Diario.class);
        startActivity(diario);
    }

}