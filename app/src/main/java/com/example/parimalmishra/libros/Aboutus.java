package com.example.parimalmishra.libros;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Aboutus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);
    }

    public void contactus(View view) {
        switch (view.getId())
        {
            case R.id.button3:
                Intent i1 = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:0123456789"));
                startActivity(i1);
                break;
            case R.id.button4:
                Intent i2 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://mail.google.com"));
                startActivity(i2);
                break;
            case R.id.button5:
                Intent i3 = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:20.5937,78.9629"));
                startActivity(i3);
                break;
        }

    }


}
