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

public class Databases extends AppCompatActivity {

    ImageView sqlite,mongodb,mysql,oracle_database;
    String UID;
    FirebaseFirestore fStore;
    FirebaseAuth fAuth;
    int sqlval,mongoval,mysqlval,oracleval;
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_databases);

        sqlite = (ImageView) findViewById(R.id.sqlite);
        mongodb = (ImageView) findViewById(R.id.mongodb);
        mysql = (ImageView) findViewById(R.id.mysql);
        oracle_database= (ImageView) findViewById(R.id.oracle);
        back=(ImageView)findViewById(R.id.backc4);
        fAuth= FirebaseAuth.getInstance();
        fStore= FirebaseFirestore.getInstance();
        UID=fAuth.getCurrentUser().getUid();


        Intent i = getIntent();
        if (i.getStringExtra("result").equals("no")) {

            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(),Category_choice.class));
                }
            });

            sqlite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i1 = new Intent(getApplicationContext(), Question.class);
                    i1.putExtra("act", "sqlite");
                    startActivity(i1);

                }
            });

            mongodb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i2 = new Intent(getApplicationContext(), Question.class);
                    i2.putExtra("act","mongodb");
                    startActivity(i2);
                }
            });

            mysql.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i3 = new Intent(getApplicationContext(), Question.class);
                    i3.putExtra("act","mysql");
                    startActivity(i3);

                }
            });

            oracle_database.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i4 = new Intent(getApplicationContext(), Question.class);
                    i4.putExtra("act","oracle");
                    startActivity(i4);
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
                        sqlval=Integer.parseInt(documentSnapshot.getString("sqlite"));
                        mysqlval=Integer.parseInt(documentSnapshot.getString("mysql"));
                        mongoval=Integer.parseInt(documentSnapshot.getString("mongodb"));
                        oracleval=Integer.parseInt(documentSnapshot.getString("oracle"));
                        if(sqlval==0)
                        {
                            sqlite.setEnabled(false);
                        }
                        if(mysqlval==0)
                        {
                            mysql.setEnabled(false);
                        }
                        if(mongoval==0)
                        {
                            mongodb.setEnabled(false);
                        }
                        if(oracleval==0)
                        {
                            oracle_database.setEnabled(false);
                        }
                        sqlite.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent i1 = new Intent(getApplicationContext(), Final_Result.class);
                                i1.putExtra("act","sqlite");
                                startActivity(i1);

                            }
                        });

                        mysql.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent i2 = new Intent(getApplicationContext(), Final_Result.class);
                                i2.putExtra("act","mysql");
                                startActivity(i2);
                            }
                        });

                        mongodb.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent i3 = new Intent(getApplicationContext(), Final_Result.class);
                                i3.putExtra("act","mongodb");
                                startActivity(i3);

                            }
                        });

                        oracle_database.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent i4 = new Intent(getApplicationContext(), Final_Result.class);
                                i4.putExtra("act","oracle");
                                startActivity(i4);
                            }
                        });


                    }
                }
            });


        }
    }
}
