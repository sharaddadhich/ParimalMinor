package com.example.parimalmishra.libros;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class SearchActivity extends AppCompatActivity {

    EditText etSearch;
    Button btnSearch;
    ProgressDialog progressDialog;

    RecyclerView rvResults;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    FirebaseAuth firebaseAuth;

    ArrayList<com.example.parimalmishra.libros.Items> EItems = new ArrayList<>();
    ArrayList<Items> SItems = new ArrayList<>();

    RvAdapter rvadapter;


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
        progressDialog = new ProgressDialog(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("Main");
        firebaseAuth.signInAnonymously();
        rvadapter = new RvAdapter(new ArrayList<Items>(), this, new onItemClick() {
            @Override
            public void onItemClickListner(String Url) {
                if(Url.equals("Libros"))
                {
                    Intent i = new Intent(SearchActivity.this,ThankYouActivity.class);
                    startActivity(i);
                }
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(Url));
                startActivity(i);
            }
        });
        progressDialog.setTitle("Loading Data....");
        progressDialog.show();
        etSearch = (EditText) findViewById(R.id.et_Search);
        btnSearch = (Button) findViewById(R.id.btn_Search);
        rvResults = (RecyclerView) findViewById(R.id.rv_DisplayResults);
        rvResults.setLayoutManager(new LinearLayoutManager(this));
        rvResults.setAdapter(rvadapter);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(etSearch.getText().toString())){
                    Toast.makeText(SearchActivity.this, "Blank Search", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    SItems=new ArrayList<Items>();
                    for(int i = 0;i<EItems.size();i++)
                    {
                        if(EItems.get(i).getExtract_data().toLowerCase().contains(etSearch.getText().toString().toLowerCase()))
                        {
                            SItems.add(EItems.get(i));
                        }
                    }

                }
                rvadapter.UpdateData(SItems);

            }
        });




        databaseReference.child("Ebay").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Items thisItem = dataSnapshot.getValue(Items.class);
                thisItem.setFrom("Ebay");
                Log.d("123123", "onChildAdded: " + thisItem.getExtract_data());
                EItems.add(thisItem);
                progressDialog.dismiss();
                rvadapter.UpdateData(EItems);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        databaseReference.child("Flipkart").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Items thisItem = dataSnapshot.getValue(Items.class);
                thisItem.setFrom("Flipkart");
                Log.d("123123", "onChildAdded: " + thisItem.getExtract_data());
                EItems.add(thisItem);
                rvadapter.UpdateData(EItems);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        databaseReference.child("Libros").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Items thisItem = dataSnapshot.getValue(Items.class);
                String url = "Libros";
                thisItem.setUrl(url);
                thisItem.setFrom("Libros");
                Log.d("123123", "onChildAdded: " + thisItem.getExtract_data());
                EItems.add(thisItem);
                rvadapter.UpdateData(EItems);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
