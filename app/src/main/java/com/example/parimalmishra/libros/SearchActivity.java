package com.example.parimalmishra.libros;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class SearchActivity extends AppCompatActivity {

    EditText etSearch;
    Button btnSearch;

    FirebaseAuth firebaseAuth;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        MenuItem logout = menu.findItem(R.id.mu_SignOut);
        MenuItem signup = menu.findItem(R.id.mu_SignUpLogin);
        if(firebaseAuth.getCurrentUser()==null)
        {
            logout.setVisible(false);
        }
        else
        {
            signup.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.mu_SignUpLogin:
                Intent gotoLogin = new Intent(SearchActivity.this,logsign.class);
                startActivity(gotoLogin);
                return true;

            case R.id.mu_ContactUs:
                Intent thisIntent = new Intent(SearchActivity.this,Contactus.class);
                startActivity(thisIntent);
                return true;


            case R.id.mu_SignOut:
                firebaseAuth.signOut();
                return true;

            case R.id.mu_AboutUs:
                Intent go = new Intent(SearchActivity.this,AboutUs.class);
                startActivity(go);
                return true;


            default:
                return super.onOptionsItemSelected(item);

        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        firebaseAuth = FirebaseAuth.getInstance();




    }
}
