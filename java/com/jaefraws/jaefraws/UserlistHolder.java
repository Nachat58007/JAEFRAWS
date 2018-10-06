package com.jaefraws.jaefraws;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class UserlistHolder extends RecyclerView.ViewHolder {

        View uview;

        public UserlistHolder(View itemView) {

            super(itemView);
            uview = itemView;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });

        }

        public void setName(String name) {
            TextView Username = (TextView) uview.findViewById(R.id.Usernamee);
            Username.setText(name);
        }

        public void setEmail(String email) {
            TextView Usermail = (TextView) uview.findViewById(R.id.Emailll);
            Usermail.setText(email);
        }
        public void setPhone(String phone){
            TextView UserPhone = (TextView)uview.findViewById(R.id.Phonii);
            UserPhone.setText(phone);
        }
}
