package com.example.parimalmishra.libros;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Log(View view) {
        Intent i1 = new Intent(this,logsign.class);
        startActivity(i1);
    }

    public void aboutus(View view) {
        Intent i2 = new Intent(this,Aboutus.class);
        startActivity(i2);
    }
}
