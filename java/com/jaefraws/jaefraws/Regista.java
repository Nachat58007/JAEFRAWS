package com.jaefraws.jaefraws;

import android.content.Intent;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jaefraws.jaefraws.Model.User;

import org.w3c.dom.Text;

public class Regista extends AppCompatActivity {

    private static final String TAG = "Regista";
    private EditText userName, userPassword, userEmail, userPhone;
    private TextView Login;
    private Button Register;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;



    String email, name, phone, password;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regista);
        setupUIViews();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getReference();
        FirebaseUser user = firebaseAuth.getCurrentUser();


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Regista.this, MainActivity.class));
            }
        });

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    //Upload data to the database
                    String user_email = userEmail.getText().toString().trim();
                    String user_password = userPassword.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(user_email, user_password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        sendEmailVerification();
                                        sendUserData();
                                        //Toast.makeText(Regista.this, "Registration Complete!",Toast.LENGTH_SHORT).show();
                                        finish();
                                        startActivity(new Intent(Regista.this, MainActivity.class));
                                    } else {
                                        Toast.makeText(Regista.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                                    }


                                }
                            });
                }
            }
        });
    }


    private void setupUIViews() {
        userName = (EditText) findViewById(R.id.Usernamem);
        userPassword = (EditText) findViewById(R.id.Pwd);
        userEmail = (EditText) findViewById(R.id.emailmain);
        userPhone = (EditText) findViewById(R.id.Tell);
        Login = (TextView) findViewById(R.id.btntoLogin);
        Register = (Button) findViewById(R.id.btnregister);

    }

    private Boolean validate() {
        Boolean result = false;
        name = userName.getText().toString();
        password = userPassword.getText().toString();
        email = userEmail.getText().toString();
        phone = userPhone.getText().toString();

        if (name.isEmpty() || password.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            Toast.makeText(this, "Please enter all the details", Toast.LENGTH_SHORT).show();
        } else {
            result = true;
        }
        return result;

    }

    private void sendEmailVerification() {
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser != null) {

            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        sendUserData();
                        Toast.makeText(Regista.this, "Successfully Registered, Verification Email sent!", Toast.LENGTH_SHORT).show();
                        firebaseAuth.signOut();
                        finish();
                        //startActivity(new Intent(Regista.this,MainActivity.class));
                    } else {
                        Toast.makeText(Regista.this, "Verfication Email has'nt been sent!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
//s5830213007@phuket.psu.ac.th


    private void sendUserData() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myref = firebaseDatabase.getReference().child("Users").child(firebaseAuth.getUid());
        User user = new User(name, email, phone);
        myref.setValue(user);

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

































/*Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent l = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(l);
            }
        });




    }



    @Override
    protected void onStart() {
        super.onStart();

        if(mAuth.getCurrentUser() != null){

        }
    }

    private void Register(){
            final String mail = Email.getText().toString().trim();
            final String name = Username.getText().toString().trim();
            final String word = Password.getText().toString().trim();
            final String tphone = phone.getText().toString().trim();

    if(name.isEmpty()){
       Username.setError("Name Required");
       Username.requestFocus();
       return;
    }

    if(mail.isEmpty()){
        Email.setError("Email Required");
        Email.requestFocus();
        return;
    }

    if(!Patterns.EMAIL_ADDRESS.matcher(mail).matches()){
        Email.setError("Enater a valid email");
        Email.requestFocus();
        return;
    }

    if(TextUtils.isEmpty(word)){
        Toast.makeText(getApplicationContext(),"Enter Password!",Toast.LENGTH_SHORT).show();
        return;
    }

        if (word.length() < 6) {
            Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
            return;
        }

    if(tphone.isEmpty()){
        phone.setError("Phone number Required");
        phone.requestFocus();
        return;
    }

    if(tphone.length() != 10){
        phone.setError("Enter a Valid phone number");
        phone.requestFocus();
        return;
        }

        mAuth.createUserWithEmailAndPassword(mail, word) 5555555555555555555555555555555555555555555555555555555555555555
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                User user = new User(name,mail,tphone,word);
                                FirebaseDatabase.getInstance().getReference("Users")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(Regista.this, getString(R.string.complete), Toast.LENGTH_LONG).show();

                                        } else {
                                            //failure
                                        }
                                    }
                                });


                                } else {
                                    Toast.makeText(Regista.this, task.getException()
                                            .getMessage(), Toast.LENGTH_LONG).show();

                            }
                        }
                    });

        }*/ //Last version


         /*Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent l = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(l);
            }
        });


       /* Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final User user = new User(Username.getText().toString(),
                        Email.getText().toString().trim(),
                        Password.getText().toString().trim(),
                        phone.getText().toString().trim());
                users.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child(user.getUsernames()).exists())
                            Toast.makeText(Regista.this, "This username is already exits !", Toast.LENGTH_SHORT).show();
                        else {
                            users.child(user.getUsernames()).setValue(user);
                            Toast.makeText(Regista.this, "Register Comlete!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //add custom code

                    }
                });
            }
        });*/


    /*@Override
    public void onClick(View v){
     switch (v.getId()){
         case R.id.btnregister:
             Register();

      }
    }*/ // last Ver.



