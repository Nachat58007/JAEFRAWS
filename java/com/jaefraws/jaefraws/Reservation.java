package com.jaefraws.jaefraws;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.ColorSpace;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jaefraws.jaefraws.Model.User;
import java.util.ArrayList;

public class Reservation extends AppCompatActivity{

    private FirebaseAuth firebaseAuth;
    private Button logout;
    private ImageView Toprofile;



    //washer
  private RecyclerView Washerlist;
    FirebaseDatabase database;
    DatabaseReference ref;
    ArrayList<User> list;
    ArrayAdapter<User> adapter;
    Context mcontext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        firebaseAuth = FirebaseAuth.getInstance();
        logout = (Button)findViewById(R.id.logout);
        Toprofile = (ImageView) findViewById(R.id.topro);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Logout();
            }
        });
        //Recyclerview

        Washerlist = (RecyclerView)findViewById(R.id.Washerlist);
        Washerlist.setHasFixedSize(true);
        Washerlist.setLayoutManager(new LinearLayoutManager(this));

        //Send Query to database
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Washer");

        Toprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Reservation.this, Uprofile.class));
            }
        });


    }
//___________________________________________________________________________________________________________________________________________________________________\\


 //Adapter rrrrrrrrrrrrrrrrrrrrrr
    @Override
    protected void onStart() {

        super.onStart();
        FirebaseRecyclerAdapter<User,WahserlistHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<User, WahserlistHolder>
                (User.class,R.layout.washerlist, WahserlistHolder.class,ref ) {


            @Override
            protected void populateViewHolder(WahserlistHolder viewHolderr, User model, int position) {
                viewHolderr.setWname(model.getWname());
                viewHolderr.setStatus(model.getStatus());

            }


            @Override
            public WahserlistHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                WahserlistHolder wahserlistHolder = super.onCreateViewHolder(parent , viewType);
                wahserlistHolder.setOnClickListener(new WahserlistHolder.Clicklisten() {
                    @Override
                    public void onItemClick(View view, int postion) {

                        TextView wn,ws;

                        //Views
                         wn = (TextView) view.findViewById(R.id.Wno);
                         ws = (TextView) view.findViewById(R.id.wstatus);

                        //get data from views
                        String wnameview = wn.getText().toString();
                        String wsttview = ws.getText().toString();

                        //pass this data to new activity
                        Intent intent = new Intent(view.getContext(),Reservewasher.class);
                        intent.putExtra("Washerno",wnameview ); //put washer no
                        intent.putExtra("Status",wsttview); // put washer status*/
                        startActivity(intent); // starto

                    }

                    @Override
                    public void onItemlongClick(View view, int postion) {
                        //TODO do my own inplementation on long item Click

                    }
                });

                return wahserlistHolder;
            }
        };

        Washerlist.setAdapter(firebaseRecyclerAdapter);
    }


    //button Logout
    private void Logout(){
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(Reservation.this, MainActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.profileMenu:
                startActivity(new Intent(Reservation.this, Uprofile.class));
        }
        return super.onOptionsItemSelected(item);
    }
//_____________________________________________________________________________________________________________________________________________________________\\


//VieeeeeeeeeeeeeeeeeeHHHHHHHollllllllllllllllllllllllddddddddddderrrrrrrr

    public static class WahserlistHolder extends RecyclerView.ViewHolder {

        View Weview;

        public WahserlistHolder(View Wview) {

            super(Wview);
            Weview = Wview;

            //item click
            Wview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mClickListener.onItemClick(v , getAdapterPosition());
                }
            });
            //item long click
            Wview.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mClickListener.onItemlongClick(v , getAdapterPosition());
                    return true;
                }
            });



        }

//_______________________________________________________________________________________________________________________\\


        public void setWname(String wno) {
            TextView wwname = (TextView) Weview.findViewById(R.id.Wno);
             wwname.setText(wno);

        }

       public void setStatus(String status) {
            TextView statusni = (TextView) Weview.findViewById(R.id.wstatus);
            statusni.setText(status);
        }

        private  WahserlistHolder.Clicklisten mClickListener;

     //interface to send callbacks
        public interface Clicklisten{
            void onItemClick(View view, int postion);
            void onItemlongClick(View view, int postion);
        }

        public void  setOnClickListener(WahserlistHolder.Clicklisten clickListener){
            mClickListener = clickListener;
        }
    }

}
