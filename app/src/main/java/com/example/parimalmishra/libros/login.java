package com.example.parimalmishra.libros;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class login extends AppCompatActivity implements View.OnClickListener {
    private EditText email;
    private EditText pass;
    private Button loginbutton;
    private TextView newuser;
    private ProgressDialog progressdialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = (EditText) findViewById(R.id.logineditText1);
        pass = (EditText) findViewById(R.id.logineditText2);
        progressdialog = new ProgressDialog(this);
        newuser = (TextView) findViewById(R.id.newusernavigate);
        loginbutton = (Button) findViewById(R.id.loginbutton1);
        loginbutton.setOnClickListener(this);
        newuser.setOnClickListener(this);
        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!= null)
        {
            finish();
            startActivity(new Intent(getApplicationContext(), loginactivity.class));
        }
    }
    private void userlogin()
    {
        String uemail = email.getText().toString().trim();
        String upass = pass.getText().toString().trim();
        if(TextUtils.isEmpty(uemail)){
            Toast.makeText(this, "Please enter your email..", Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(upass)){
            Toast.makeText(this, "Please enter your password..", Toast.LENGTH_SHORT).show();
        }
        progressdialog.setMessage("Signing In...");
        progressdialog.show();
        firebaseAuth.signInWithEmailAndPassword(uemail, upass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressdialog.dismiss();
                        if(task.isSuccessful()){
                            finish();
                            startActivity(new Intent(getApplicationContext(), loginactivity.class));
                        }
                    }
                });
    }

   // public void forgotpassword(View view) {

    //}


    @Override
    public void onClick(View v) {
        if (v == loginbutton)
        {
            userlogin();
        }
        if(v == newuser)
        {
            finish();
            startActivity(new Intent(this,Signup.class));
        }
    }
}
