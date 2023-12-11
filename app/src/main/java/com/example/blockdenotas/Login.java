package com.example.blockdenotas;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private FirebaseAuth Auth;
    TextView txtRegisterL;
    EditText editEmailL, editPassL;
    Button buttonLoginL;
    ImageButton buttonBackL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Auth = FirebaseAuth.getInstance();

        buttonBackL = findViewById(R.id.buttonBackL);
        editEmailL = findViewById(R.id.editEmailL);
        editPassL = findViewById(R.id.editPassL);
        txtRegisterL = findViewById(R.id.txtRegisterL);
        buttonLoginL = findViewById(R.id.buttonLoginL);

        buttonLoginL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editEmailL.getText().toString();
                String pass = editPassL.getText().toString();

                if(!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    if(!pass.isEmpty()) {
                        Auth.signInWithEmailAndPassword(email, pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Toast.makeText(Login.this, "Bienvenido.", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(Login.this, MainActivity.class));
                                finish();
                            }
                        });
                    } else {
                        Toast.makeText(Login.this, "Coloque su contraseña.", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });


        txtRegisterL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, SignUp.class);
                startActivity(intent);
            }
        });

        buttonBackL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}