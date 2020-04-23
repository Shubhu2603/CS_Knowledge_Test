package com.example.project1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class trend_of_the_generation extends AppCompatActivity {

    ImageView AI;
    ImageView IOT;
    FirebaseFirestore fStore;
    FirebaseAuth fAuth;
    String UID;
    int ai,iot;
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trend_of_the_generation);

        AI = (ImageView) findViewById(R.id.AI);
        IOT = (ImageView) findViewById(R.id.IOT);
        back=(ImageView) findViewById(R.id.backp3);

        Intent i = getIntent();
        if (i.getStringExtra("result").equals("no")) {

            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(),Category_choice.class));
                }
            });

            AI.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i1 = new Intent(getApplicationContext(), Question.class);
                    i1.putExtra("act", "AI");
                    startActivity(i1);

                }
            });

            IOT.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i2 = new Intent(getApplicationContext(), Question.class);
                    i2.putExtra("act", "IOT");
                    startActivity(i2);
                }
            });

        }
        else
        {
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(),category_result.class));
                }
            });

            DocumentReference dr=fStore.collection("Users").document(UID);
            dr.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if(task.isSuccessful())
                    {
                        DocumentSnapshot documentSnapshot=task.getResult();
                        ai=Integer.parseInt(documentSnapshot.getString("AI"));
                        iot=Integer.parseInt(documentSnapshot.getString("IOT"));

                        if(ai==0)
                        {
                            AI.setEnabled(false);
                        }
                        if(iot==0)
                        {
                            IOT.setEnabled(false);
                        }

                        AI.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent i1 = new Intent(getApplicationContext(), Final_Result.class);
                                i1.putExtra("act", "AI");
                                startActivity(i1);

                            }
                        });

                        IOT.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent i2 = new Intent(getApplicationContext(), Final_Result.class);
                                i2.putExtra("act", "IOT");
                                startActivity(i2);
                            }
                        });
                    }
                }
            });


        }
    }
}
