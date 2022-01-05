package com.tl.portugal.software.findhistory;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tl.portugal.software.findhistory.Firebase.FirebaseModel;
import com.tl.portugal.software.findhistory.Models.User;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private Button btn_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        btn_logout = findViewById(R.id.btn_logout);

        btn_logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Intent intent = new Intent(MainActivity.this, AuthenticationActivity.class);
                startActivity(intent);
                finish();
            }
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
            TextView textView = findViewById(R.id.textView2);

            FirebaseModel fbModel = new FirebaseModel();

            fbModel.getUserName(currentUser.getUid(), textView);

        }
    }
}
