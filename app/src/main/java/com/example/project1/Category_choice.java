package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Category_choice extends AppCompatActivity {

    Button pl,fe,ns,dbs,cc,tog;
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_choice);

        pl=(Button)findViewById(R.id.programming_languages);
        fe=(Button)findViewById(R.id.front_end);
        ns=(Button)findViewById(R.id.networking_and_security);
        dbs=(Button)findViewById(R.id.database);
        cc=(Button)findViewById(R.id.cloud_computing);
        tog=(Button)findViewById(R.id.trend_of_the_generation);
        back=(ImageView)findViewById(R.id.backc);

        pl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),programming_languages.class);
                i.putExtra("result","no");
                startActivity(i);
            }
        });

        fe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),front_end.class);
                i.putExtra("result","no");
                startActivity(i);
            }
        });

        ns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),networking_and_security.class);
                i.putExtra("result","no");
                startActivity(i);
            }
        });

        dbs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),Databases.class);
                i.putExtra("result","no");
                startActivity(i);
            }
        });

        cc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),cloud_computing.class);
                i.putExtra("result","no");
                startActivity(i);
            }
        });

        tog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),trend_of_the_generation.class);
                i.putExtra("result","no");
                startActivity(i);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Welcome.class));
            }
        });
    }
}
