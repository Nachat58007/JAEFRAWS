package com.jaefraws.jaefraws;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Adminlogin extends AppCompatActivity {

    private EditText Adminname;
    private EditText AdminPassword;
    private Button Adminloginne;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminlogin);

        Adminname = (EditText) findViewById(R.id.adname);
        AdminPassword = (EditText) findViewById(R.id.adPwdlogin);
        Adminloginne = (Button) findViewById(R.id.adloginbtn);

        Adminloginne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Validate(Adminname.getText().toString(), AdminPassword.getText().toString());
            }
        });


    }

    private void Validate(String Adminnameni, String AdminPass) {
        if ((Adminnameni.equals("Jaefa")) && (AdminPass.equals("888888889"))){
            Intent intent = new Intent(Adminlogin.this, Adminpage.class);
            startActivity(intent);
        } else {
            Toast.makeText(Adminlogin.this, "Nani! Your're not admin get out!", Toast.LENGTH_SHORT).show();

        }

    }
}





