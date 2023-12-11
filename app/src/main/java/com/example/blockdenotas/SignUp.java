package com.example.blockdenotas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    private FirebaseAuth Auth;
    FirebaseDatabase baseDatos;
    DatabaseReference referencia;
    EditText editEmailR, editPassR, editPassCR;
    Button buttonSignR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Auth = FirebaseAuth.getInstance();

        editEmailR = findViewById(R.id.editEmailR);
        editPassR = findViewById(R.id.editPassR);
        editPassCR = findViewById(R.id.editPassCR);
        buttonSignR = findViewById(R.id.buttonSignR);

        buttonSignR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                baseDatos = FirebaseDatabase.getInstance();
                referencia = baseDatos.getReference("users");

                String email = editEmailR.getText().toString();
                String pass = editPassR.getText().toString();
                String passrc = editPassCR.getText().toString();

                if (email.equals("") || pass.equals("") || passrc.equals("")) {
                    Toast.makeText(SignUp.this, "Todos los campos deben ser rellenados", Toast.LENGTH_SHORT).show();
                } else if (pass.length() >= 8) {
                    if (pass.equals(passrc)) {

                        Auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    Users users = new Users(email, pass);
                                    referencia.child(Auth.getCurrentUser().getUid()).setValue(users);

                                    Toast.makeText(SignUp.this, "Creo su cuenta con exito.", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(SignUp.this, Login.class);
                                    startActivity(intent);
                                }
                            }
                        });
                    } else {
                        Toast.makeText(SignUp.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(SignUp.this, "La contraseña no puede tener menos de 6 caracteres", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}