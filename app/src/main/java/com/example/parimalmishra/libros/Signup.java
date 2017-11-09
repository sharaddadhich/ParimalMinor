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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {
    private EditText fname;
    private EditText lname;
    private EditText uage;
    private EditText uphone;
    private EditText uemail;
    private EditText upass;
    private EditText ucpass;
    private DatabaseReference databaseReference;
    Button signup;

    private ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        progressDialog = new ProgressDialog(this);
        fname = (EditText) findViewById(R.id.editText);
        lname = (EditText) findViewById(R.id.editText2);
        uage = (EditText) findViewById(R.id.editText8);
        uphone = (EditText) findViewById(R.id.editText4);
        uemail = (EditText) findViewById(R.id.editText3);
        upass = (EditText) findViewById(R.id.editText5);
        ucpass = (EditText) findViewById(R.id.editText6);
        signup = (Button) findViewById(R.id.button);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ConfirmPass())
                {
                    firebaseAuth.createUserWithEmailAndPassword(uemail.getText().toString(),upass.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    saveUserinfo();
                                    progressDialog.dismiss();
                                }
                            });

                    Toast.makeText(Signup.this, "You have registered successfully.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public boolean ConfirmPass()
    {
        if(TextUtils.isEmpty(fname.getText().toString()))
        {
            Toast.makeText(this, "Please provide your first name", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(TextUtils.isEmpty(lname.getText().toString()))
        {
            Toast.makeText(this, "Please provide your last name", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(TextUtils.isEmpty(uage.getText().toString()))
        {
            Toast.makeText(this, "Please provide your age", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(TextUtils.isEmpty(uemail.getText().toString()))
        {
            Toast.makeText(this, "Please provide your contact number", Toast.LENGTH_SHORT).show();
            return false;
        }

        else if(TextUtils.isEmpty(uemail.getText().toString()))
        {
            Toast.makeText(this, "Please provide a Mail", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(TextUtils.isEmpty(upass.getText().toString()))
        {
            Toast.makeText(this, "Please Provide a Password", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(TextUtils.isEmpty(ucpass.getText().toString()))
        {
            Toast.makeText(this, "Please Provide the Confirm Password", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(!upass.getText().toString().equals(ucpass.getText().toString()))
        {
            return false;
        }
        progressDialog.setMessage("Please wait, You are getting registered...");
        progressDialog.show();
        return true;
    }
    private void saveUserinfo(){
        String FirstName = fname.getText().toString().trim();
        String LastName = lname.getText().toString().trim();
        String Age = uage.getText().toString().trim();
        String ContactNumber = uphone.getText().toString().trim();
        String Email = uemail.getText().toString().trim();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        Userinfo userinfo = new Userinfo(FirstName,LastName,Age,ContactNumber,Email);
        databaseReference.child("Users").child(firebaseAuth.getCurrentUser().getUid()).setValue(userinfo);
    }
    public void gotologinpage(View view) {
        Intent i1 = new Intent(this,login.class);
        startActivity(i1);
    }
}
