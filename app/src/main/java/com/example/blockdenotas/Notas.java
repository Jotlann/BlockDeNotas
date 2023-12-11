package com.example.blockdenotas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Notas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas);

        ImageButton buttonBackN = findViewById(R.id.buttonBackN);
        SearchView searchNotes = findViewById(R.id.searchNotes);
        RecyclerView noteList = findViewById(R.id.listNotes);


        // Establecer el listener para manejar el evento de clic
        buttonBackN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Establecer el listener para manejar el evento de búsqueda
        searchNotes.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Filtrar los elementos de la lista de blocs de notas según el texto de búsqueda
                //adapter.getFilter().filter(newText);
                return true;
            }
        });



    }
}