package com.example.parimalmishra.libros;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class logsign extends AppCompatActivity {

    Button b1;
    Button b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logsign);
        b1 = (Button) findViewById(R.id.b2);
        b2 = (Button) findViewById(R.id.b3);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(logsign.this,login.class);
                startActivity(i);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(logsign.this,Signup.class);
                startActivity(j);
            }
        });
    }



    //public void loginpage(View view) {
      //  Intent i1 = new Intent(this,login.class);
    //    startActivity(i1);
    //}

    //public void signup(View view) {
    //    Intent i1 = new Intent(this,Signup.class);
    //    startActivity(i1);
    //}
}
