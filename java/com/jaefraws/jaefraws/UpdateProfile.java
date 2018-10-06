package com.jaefraws.jaefraws;

import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jaefraws.jaefraws.Model.User;

public class UpdateProfile extends AppCompatActivity {

    private EditText Newname, Newemail , Newphone;
    private Button Save;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseUser userni;
    String uid;

    //String name, email, phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        Newname = findViewById(R.id.upname);
        Newemail = findViewById(R.id.upmail);
        Newphone = findViewById(R.id.upphone);
        Save = findViewById(R.id.saveni);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        userni = FirebaseAuth.getInstance().getCurrentUser();
        //firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        uid = userni.getUid();

        final DatabaseReference databaseReference = firebaseDatabase.getReference().child("Users");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String user_name = dataSnapshot.child(uid).child("name").getValue(String.class);
                String user_email = dataSnapshot.child(uid).child("email").getValue(String.class);
                String user_phone = dataSnapshot.child(uid).child("phone").getValue(String.class);
                Newname.setText(user_name);
                Newemail.setText(user_email);
                Newphone.setText(user_phone);
        }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(UpdateProfile.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = Newname.getText().toString();
                String email = Newemail.getText().toString();
                String phone = Newphone.getText().toString();
                User userProfile = new User(name, email, phone);
                databaseReference.child(uid).setValue(userProfile);
                finish();
                startActivity(new Intent(UpdateProfile.this, Uprofile.class));
                Toast.makeText(UpdateProfile.this,"Profile Updated",Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}