package com.tl.portugal.software.findhistory;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText email_field;
    private TextInputEditText password_field;
    private Button btn_login;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        email_field = findViewById(R.id.email_login_input);
        password_field = findViewById(R.id.password_login_input);
        btn_login = findViewById(R.id.btn_login);

        /**
         * Botão de voltar atras
         */
        ImageButton back_btn_login = findViewById(R.id.back_btn_login);
        back_btn_login.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, AuthenticationActivity.class);
            startActivity(intent);
            finish();
        });

        /**
         * Ações apos clicar no botão de login
         */
        btn_login.setOnClickListener(view -> {
            String email = email_field.getText().toString();
            String pwd = password_field.getText().toString();

            if(!TextUtils.isEmpty(email) || !TextUtils.isEmpty(pwd)) {
                mAuth.signInWithEmailAndPassword(email, pwd)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()) {
                                    openHomeActivity();
                                } else {
                                    String error = task.getException().getMessage();
                                    Toast.makeText(LoginActivity.this, ""+error, Toast.LENGTH_LONG).show();

                                }
                            }
                        });
            } else {
                Toast.makeText(LoginActivity.this, "EMPTY FIELDS", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void openHomeActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
