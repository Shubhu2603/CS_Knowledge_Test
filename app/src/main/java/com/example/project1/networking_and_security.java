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

public class networking_and_security extends AppCompatActivity {

    ImageView networking,security;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    int sec,net;
    String UID;
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_networking_and_security);

        fStore=FirebaseFirestore.getInstance();
        fAuth=FirebaseAuth.getInstance();
        security = (ImageView) findViewById(R.id.security);
        networking = (ImageView) findViewById(R.id.networking);
        back=(ImageView)findViewById(R.id.backc6);
        UID=fAuth.getCurrentUser().getUid();

        Intent i = getIntent();
        if (i.getStringExtra("result").equals("no")) {

            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(),Category_choice.class));
                }
            });

            networking.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i1 = new Intent(getApplicationContext(), Question.class);
                    i1.putExtra("act","networking");
                    startActivity(i1);

                }
            });

            security.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i2 = new Intent(getApplicationContext(), Question.class);
                    i2.putExtra("act","security");
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
                        sec=Integer.parseInt(documentSnapshot.getString("security"));
                        net=Integer.parseInt(documentSnapshot.getString("networking"));

                        if(sec==0)
                        {
                            security.setEnabled(false);
                        }
                        if(net==0)
                        {
                            networking.setEnabled(false);
                        }

                        networking.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent i1 = new Intent(getApplicationContext(), Final_Result.class);
                                i1.putExtra("act","networking");
                                startActivity(i1);

                            }
                        });

                        security.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent i2 = new Intent(getApplicationContext(), Final_Result.class);
                                i2.putExtra("act","security");
                                startActivity(i2);
                            }
                        });
                    }
                }
            });


        }
    }
}
