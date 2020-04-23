package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class category_result extends AppCompatActivity {

    Button pl,fe,ns,dbs,cc,tog;
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_result);
        pl=(Button)findViewById(R.id.programming_languages2);
        fe=(Button)findViewById(R.id.front_end2);
        ns=(Button)findViewById(R.id.networking_and_security2);
        dbs=(Button)findViewById(R.id.database2);
        cc=(Button)findViewById(R.id.cloud_computing2);
        tog=(Button)findViewById(R.id.trend_of_the_generation2);
        back=(ImageView)findViewById(R.id.backc2);

        pl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(new Intent(getApplicationContext(),programming_languages.class));
                        i1.putExtra("result","result");
                        startActivity(i1);
            }
        });

        fe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(new Intent(getApplicationContext(),front_end.class));
                i1.putExtra("result","result");
                startActivity(i1);
            }
        });

        ns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(new Intent(getApplicationContext(),networking_and_security.class));
                i1.putExtra("result","result");
                startActivity(i1);
            }
        });

        dbs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(new Intent(getApplicationContext(),Databases.class));
                i1.putExtra("result","result");
                startActivity(i1);
            }
        });

        cc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(new Intent(getApplicationContext(),cloud_computing.class));
                i1.putExtra("result","result");
                startActivity(i1);
            }
        });

        tog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(new Intent(getApplicationContext(),trend_of_the_generation.class));
                i1.putExtra("result","result");
                startActivity(i1);
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
