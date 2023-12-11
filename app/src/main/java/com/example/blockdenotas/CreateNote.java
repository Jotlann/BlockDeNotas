package com.example.blockdenotas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateNote extends AppCompatActivity {

    private ImageButton backButton;
    private EditText editTitle, editContent;
    private Button buttonSaveC;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        auth = FirebaseAuth.getInstance();
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
                FirebaseUser user = auth.getCurrentUser();
                String uid = user.getUid();

                String title = editTitle.getText().toString();
                String content = editContent.getText().toString();

                // Crea un objeto Note con el título y el contenido de la nota
                Notes note = new Notes(title, content);

                // Guarda la nota en la base de datos Firebase
                FirebaseDatabase.getInstance().getReference("Notas").child(uid).child(title)
                        .setValue(note).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(CreateNote.this, "Nota creada", Toast.LENGTH_LONG).show();
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(CreateNote.this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });

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