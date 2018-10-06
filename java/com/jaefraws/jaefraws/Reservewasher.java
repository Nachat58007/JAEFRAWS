package com.jaefraws.jaefraws;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Locale;

public class Reservewasher extends AppCompatActivity {
   public TextView waswashername,wasttuss,timerr;
   Button reserveni;
   private CountDownTimer countDownTimer;
   private long timleftInmilisecond = 600000;
   private boolean timerRunning;

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseUser userr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservewasher);

        reserveni = (Button)findViewById(R.id.reservebtn);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Reserving a Washer");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        //initialize
        waswashername = (TextView)findViewById(R.id.wnameni);
        wasttuss = (TextView)findViewById(R.id.wstatusni);

        //get data drom intent
        String wsname =  getIntent().getStringExtra("Washerno");
       String wsstt = getIntent().getStringExtra("Status");

        //set data to views
        waswashername.setText(wsname);
        wasttuss.setText(wsstt);


        //Timmmmmmmmeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeerrrrrrrrrrrrrr
      timerr = (TextView)findViewById(R.id.timer);
      reserveni = (Button)findViewById(R.id.reservebtn);

      reserveni.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              FirebaseDatabase database = FirebaseDatabase.getInstance();
              DatabaseReference myRef = database.getReference("LED_STATUS");
              myRef.setValue(1);
              statstop();

          }
      });
    }

    private void statstop() {
        if(timerRunning){
            stopTimer();

        }else {
            startTimer();
        }
    }

    public void startTimer(){

        countDownTimer = new CountDownTimer(timleftInmilisecond, 1000) {
            @Override
            public void onTick(long l) {
                timleftInmilisecond = l;
                updateTimer();


            }

            @Override
            public void onFinish() {
                countDownTimer.cancel();
                Intent i = new Intent(Reservewasher.this, Reservation.class);
                startActivity(i);
            }
        }.start();

        reserveni.setText("Cancel");
        timerRunning = true;

    }


    public void  stopTimer(){
        countDownTimer.cancel();
        timerRunning = false;
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("LED_STATUS");
        Intent i = new Intent(Reservewasher.this, Reservation.class);
        startActivity(i);
        myRef.setValue(0);
        }


    public void updateTimer(){
        int minute = (int)(timleftInmilisecond / 1000) / 60; //upper
        int second = (int)(timleftInmilisecond / 1000 )% 60;
        String timeLeftext;
        timeLeftext = "" + minute;
        timeLeftext += ":";
        if(second<10)timeLeftext +="0";
        timeLeftext += second;
        timerr.setText(timeLeftext);
        String timeLeftFormatt = String.format(Locale.getDefault(),"%02d:%02d",minute,second);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    /*public void check (){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("LED_STATUS");
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists())
                    countDownTimer.cancel();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }*/
}
