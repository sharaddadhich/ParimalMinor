package com.example.parimalmishra.libros;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class loginactivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth firebaseAuth;
    private Button logoutbutton;
    private TextView text1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginactivity);
        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() == null)
        {
            finish();
            startActivity(new Intent(this,loginactivity.class));
        }
        FirebaseUser user = firebaseAuth.getCurrentUser();
        logoutbutton = (Button) findViewById(R.id.buttonlogout);
        logoutbutton.setOnClickListener(this);
        text1 = (TextView) findViewById(R.id.loginactivitytextview1);
        text1.setText("Welcome");
    }

    @Override
    public void onClick(View v) {
        if(v == logoutbutton)
        {
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this,login.class));
        }

    }
}
