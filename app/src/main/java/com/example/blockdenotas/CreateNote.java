package com.example.blockdenotas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class CreateNote extends AppCompatActivity {

    private ImageButton backButton;
    private EditText editTitle, editContent;
    private Button buttonSaveC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        backButton = findViewById(R.id.buttonBackC);
        buttonSaveC = findViewById(R.id.buttonSaveC);
        editTitle = findViewById(R.id.editTitle);
        editContent = findViewById(R.id.editContent);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aquí puedes agregar el código para manejar la acción de retroceder
            }
        });

        buttonSaveC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editTitle.getText().toString();
                String content = editContent.getText().toString();

                // Crea un objeto Note con el título y el contenido de la nota
                Notes note = new Notes(title, content);

                // Guarda la nota en la base de datos Firebase
                NoteListAdapter.child(title).setValue(note);

                Toast.makeText(CreateNote.this, "Note saved successfully", Toast.LENGTH_SHORT).show();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateNote.this, Notas.class);
                startActivity(intent);
            }
        });

    }
}