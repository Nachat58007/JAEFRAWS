package com.jaefraws.jaefraws;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jaefraws.jaefraws.Model.User;

import java.util.UUID;

public class Uprofile extends AppCompatActivity {

    private ImageView Profilepic;
    private TextView Profilename, Profilemail, Profilephone;
    private Button Profileupdate ,changepass;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseUser userr;
    String uid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uprofile);


        Profilename = findViewById(R.id.namepro);
        Profilemail = findViewById(R.id.promail);
        Profilephone = findViewById(R.id.prophone);
        Profileupdate = findViewById(R.id.btnedit);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        userr = FirebaseAuth.getInstance().getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        uid = userr.getUid();


        final DatabaseReference databaseReference = firebaseDatabase.getReference().child("Users");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String user_name = dataSnapshot.child(uid).child("name").getValue(String.class);
                String user_email = dataSnapshot.child(uid).child("email").getValue(String.class);
                String user_phone = dataSnapshot.child(uid).child("phone").getValue(String.class);

                //DatabaseReference childer = FirebaseDatabase.getInstance().getReference("Users");
                //User user = dataSnapshot.getValue(User.class);

                //hilder.setValue(user);
                Profilename.setText("Name:" + user_name);
                Profilemail.setText("Email:" + user_email);
                Profilephone.setText("Phone:" + user_phone);

            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Uprofile.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();

            }
            });


        Profileupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Uprofile.this, UpdateProfile.class));
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
