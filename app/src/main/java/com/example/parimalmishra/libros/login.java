package com.example.parimalmishra.libros;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
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

import org.w3c.dom.Text;

public class login extends AppCompatActivity {
    private EditText email;
    private EditText pass;
    private Button loginbutton;
    private TextView newuser;
    private ProgressDialog progressdialog;
    private FirebaseAuth firebaseAuth;

    TextView forgotpassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = (EditText) findViewById(R.id.logineditText1);
        pass = (EditText) findViewById(R.id.logineditText2);
        progressdialog = new ProgressDialog(this);
        newuser = (TextView) findViewById(R.id.newusernavigate);
        loginbutton = (Button) findViewById(R.id.loginbutton1);
        forgotpassword = (TextView) findViewById(R.id.forgotpassword);
        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!= null)
        {
            finish();
            startActivity(new Intent(getApplicationContext(), loginactivity.class));
        }

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userlogin();
            }
        });

        newuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(login.this,Signup.class));
            }
        });

        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LayoutInflater li = getLayoutInflater();
                View alertLayout = li.inflate(R.layout.forgot_password_layout,null);
                final ProgressDialog Dialog = new ProgressDialog(login.this);
                final EditText etForgotPassEmail = (EditText) alertLayout.findViewById(R.id.et_forgotPassEmail);
                Button btnForgotPass = (Button) alertLayout.findViewById(R.id.btn_forgotPassSubmit);
                final AlertDialog.Builder alert = new AlertDialog.Builder(login.this);
                alert.setTitle("Reset Password");
                alert.setView(alertLayout);

                final AlertDialog alertDialog = alert.create();
                alertDialog.show();

                btnForgotPass.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(TextUtils.isEmpty(etForgotPassEmail.getText().toString()))
                        {
                            etForgotPassEmail.setError("Please provide your Email");
                        }
                        else if(!etForgotPassEmail.getText().toString().contains("@"))
                        {
                            etForgotPassEmail.setError("Please provide a valid Email");
                        }
                        else
                        {
                            Dialog.setMessage("Sending Reset Mail");
                            Dialog.setCanceledOnTouchOutside(false);
                            Dialog.show();
                            FirebaseAuth.getInstance().sendPasswordResetEmail(etForgotPassEmail.getText().toString())
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Dialog.dismiss();
                                            if(task.isSuccessful())
                                            {
                                                Toast.makeText(login.this, "Reset mail sent.. Please Check your Mail id", Toast.LENGTH_SHORT).show();
                                                alertDialog.dismiss();
                                            }
                                            else {
                                                Toast.makeText(login.this, "Mail id not Registered", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        }
                    }
                });
//                String Email = email.getText().toString();
//                if(TextUtils.isEmpty(Email))
//                {
//                    Toast.makeText(login.this, "Please enter your email", Toast.LENGTH_SHORT).show();
//                }
//                else
//                {
//                    final ProgressDialog progress = new ProgressDialog(login.this);
//                    progress.setTitle("Sending password reset email..");
//                    progress.show();
//                    FirebaseAuth.getInstance().sendPasswordResetEmail(Email)
//                            .addOnCompleteListener(new OnCompleteListener<Void>() {
//                                @Override
//                                public void onComplete(@NonNull Task<Void> task) {
//                                    progress.dismiss();
//                                    if(task.isSuccessful())
//                                    {
//                                        Toast.makeText(login.this, "Email Sent", Toast.LENGTH_SHORT).show();
//                                    }
//                                    else
//                                    {
//                                        Toast.makeText(login.this, "Email id does not exist", Toast.LENGTH_SHORT).show();
//                                    }
//                                }
//                            });
//                }
            }
        });

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
                            Intent newIntent = new Intent(login.this,SearchActivity.class);
                            startActivity(newIntent);
                        }
                        else
                        {
                            Toast.makeText(login.this, "Username/Password is invalid", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }





}
