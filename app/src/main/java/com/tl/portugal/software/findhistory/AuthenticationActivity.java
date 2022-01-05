package com.tl.portugal.software.findhistory;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Atividade Inicial para verificar se o utilizador já está logado
 * Se o utilizador não estiver logado, permitir fazer o login ou registo
 * Caso o utilizador esteja logado mudar para a pagina principal onde é apresentado o mapa
 */
public class AuthenticationActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        /*
            Obter a referencia do botao de login
            Se o botao de login for clicado mudar de página o login
         */
        Button btn_login = findViewById(R.id.btn_login_activity);
        btn_login.setOnClickListener(v -> {
            Intent intent = new Intent(AuthenticationActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });

        Button btn_regist = findViewById(R.id.btn_signup_activity);
        btn_regist.setOnClickListener(v -> {
            Intent intent = new Intent(AuthenticationActivity.this, RegistActivity.class);
            startActivity(intent);
            finish();
        });
    }

    protected void onStart() {
        super.onStart();
        //Obter o utilizador que esteja activo no equipamento
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        /*
            Se o utilizador for diferente de null significa que ja esta autenticado
            Se ja estiver autenticado muda para a pagina principal
            Caso o currentUser seja null significa que o utilizador nao esta logado no equipamento
        */
        if(currentUser != null) {
            Intent intent = new Intent(AuthenticationActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

    }
}