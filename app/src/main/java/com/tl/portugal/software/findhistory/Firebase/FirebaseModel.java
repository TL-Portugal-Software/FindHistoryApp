package com.tl.portugal.software.findhistory.Firebase;

import android.nfc.Tag;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tl.portugal.software.findhistory.Models.User;
import com.tl.portugal.software.findhistory.R;

public class FirebaseModel {
    private static final String TAG = "ReadAndWriteSnippets";

    private DatabaseReference reference;

    public FirebaseModel() {
        this.reference = FirebaseDatabase.getInstance("https://findhistoryapp-bb81e-default-rtdb.europe-west1.firebasedatabase.app").getReference();
    }

    public void addUser(User user) {
        reference.child("users").child(user.getUid()).setValue(user);

    }

    public void getUserName(String id, TextView usernameView) {
        DatabaseReference referenceUsers = this.reference.child("users").child(id);

        referenceUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String username = dataSnapshot.getValue(User.class).getUsername();
                usernameView.setText(username);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
