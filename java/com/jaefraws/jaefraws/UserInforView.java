package com.jaefraws.jaefraws;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

//import com.firebase.ui.database.FirebaseListAdapter;
//import com.firebase.ui.database.FirebaseListOptions;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.jaefraws.jaefraws.Model.User;

import java.util.ArrayList;

public class UserInforView extends AppCompatActivity {

    private RecyclerView Userlist;
    FirebaseDatabase database;
    DatabaseReference ref;
    ArrayList<User> list;
    ArrayAdapter<User> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_infor_view);

       //Recyclerview
        Userlist = (RecyclerView)findViewById(R.id.UserList);
        Userlist.setHasFixedSize(true);
        Userlist.setLayoutManager(new LinearLayoutManager(this));

        //Send Query to database
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Users");

    }

    //_____________________________________________________________________________________________________________________\\

    //Adapter rrrrrrrrrrrrrrrrrrrrrr

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<User, UserlistHolder>firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<User, UserlistHolder>
                (User.class,R.layout.activity_user, UserlistHolder.class,ref) {


            @Override
            protected void populateViewHolder(UserlistHolder viewHolder, User model, int position) {
                viewHolder.setName(model.getName());
                viewHolder.setEmail("Email : " + model.getEmail());
                viewHolder.setPhone("Phone : " + model.getPhone());

            }
        };

        Userlist.setAdapter(firebaseRecyclerAdapter);

    }
}







