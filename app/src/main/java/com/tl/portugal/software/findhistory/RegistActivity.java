package com.tl.portugal.software.findhistory;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.tl.portugal.software.findhistory.Firebase.FirebaseModel;
import com.tl.portugal.software.findhistory.Models.User;

public class RegistActivity extends AppCompatActivity {

    private TextInputEditText email_field;
    private TextInputEditText password_field;
    private TextInputEditText password_confirm_field;
    private TextInputEditText username_field;
    private TextInputEditText phone_field;
    private Button btn_regist;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);

        mAuth = FirebaseAuth.getInstance();
        email_field = findViewById(R.id.email_regist_input);
        password_field = findViewById(R.id.password_regist_input);
        password_confirm_field = findViewById(R.id.password_regist_repit_input);
        username_field = findViewById(R.id.username_regist_input);
        phone_field = findViewById(R.id.phone_regist_input);
        btn_regist = findViewById(R.id.btn_regist);

        /**
         * Botão de voltar atras
         */
        ImageButton back_btn_regist = findViewById(R.id.back_btn_regist);
        back_btn_regist.setOnClickListener(v -> {
            Intent intent = new Intent(RegistActivity.this, AuthenticationActivity.class);
            startActivity(intent);
            finish();
        });

        /**
         * Ações apos clicar no botão de login
         */
        btn_regist.setOnClickListener(view -> {
            User user = new User();

            user.setEmail(email_field.getText().toString());
            user.setName("");
            user.setUsername(username_field.getText().toString());
            user.setPhone(phone_field.getText().toString());
            String pwd = password_field.getText().toString();
            String pwd_confirm = password_confirm_field.getText().toString();

            if(!TextUtils.isEmpty(user.getEmail()) || !TextUtils.isEmpty(pwd) || !TextUtils.isEmpty(pwd_confirm) || !TextUtils.isEmpty(user.getUsername())) {
                if(TextUtils.equals(pwd, pwd_confirm)) {
                    mAuth.createUserWithEmailAndPassword(user.getEmail(), pwd)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()) {
                                        user.setUid(mAuth.getUid());

                                        FirebaseModel fbModel = new FirebaseModel();

                                        fbModel.addUser(user);

                                        openHomeActivity();
                                    } else {
                                        String error = task.getException().getMessage();
                                        Toast.makeText(RegistActivity.this, ""+error, Toast.LENGTH_LONG).show();

                                    }
                                }
                            });
                } else {
                    Toast.makeText(RegistActivity.this, "Password dont match", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void openHomeActivity() {
        Intent intent = new Intent(RegistActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
