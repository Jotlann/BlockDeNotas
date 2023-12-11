package com.example.blockdenotas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Notas extends AppCompatActivity {

    RecyclerView listNotes;
    List<Notes> listarNotas;
    NoteListAdapter adaptadorNotas;
    SearchView searchNotes;
    DatabaseReference databaseReference;
    ValueEventListener eventListener;
    ImageButton buttonBackN;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas);

        buttonBackN = findViewById(R.id.buttonBackN);
        searchNotes = findViewById(R.id.searchNotes);
        listNotes = findViewById(R.id.listNotes);
        listarNotas = new ArrayList<>();
        searchNotes.clearFocus();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(Notas.this, 1);
        listNotes.setLayoutManager(gridLayoutManager);
        adaptadorNotas = new NoteListAdapter(Notas.this, listarNotas);
        listNotes.setAdapter(adaptadorNotas);


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        if (user != null) {
            databaseReference = FirebaseDatabase.getInstance().getReference("Notas").child(uid);

            eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    listarNotas.clear();
                    for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                        Notes notes1 = itemSnapshot.getValue(Notes.class);
                        listarNotas.add(notes1);
                    }
                    adaptadorNotas.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });


            // Establecer el listener para manejar el evento de clic


            searchNotes.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    buscarList(newText);
                    return true;
                }
            });
        }

        buttonBackN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Notas.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    public void buscarList(String text) {
        ArrayList<Notes> buscarList = new ArrayList<>();
        for (Notes notes1: listarNotas) {
            if (notes1.getTittle().toLowerCase().contains(text.toLowerCase())) {
                buscarList.add(notes1);
            }
        }
        adaptadorNotas.buscarNotas(buscarList);
    }
}