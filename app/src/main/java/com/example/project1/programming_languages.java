package com.example.project1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class programming_languages extends AppCompatActivity {

    ImageView c,cpp,java,python;
    ImageView back;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String UID;
    int cval,cppval,javaval,pythonval;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programming_languages);
        c = (ImageView) findViewById(R.id.c);
        cpp = (ImageView) findViewById(R.id.cpp);
        java = (ImageView) findViewById(R.id.java);
        python = (ImageView) findViewById(R.id.python);
        back = (ImageView) findViewById(R.id.backp2);
        fAuth=FirebaseAuth.getInstance();
        fStore=FirebaseFirestore.getInstance();
        UID=fAuth.getCurrentUser().getUid();


        Intent i = getIntent();
        if (i.getStringExtra("result").equals("no")) {

            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(),Category_choice.class));
                }
            });


            c.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i1 = new Intent(getApplicationContext(), Question.class);
                    i1.putExtra("act","c");
                    startActivity(i1);

                }
            });

            cpp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i2 = new Intent(getApplicationContext(), Question.class);
                    i2.putExtra("act","cpp");
                    startActivity(i2);
                }
            });

            java.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i3 = new Intent(getApplicationContext(), Question.class);
                    i3.putExtra("act","java");
                    startActivity(i3);

                }
            });

            python.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i4 = new Intent(getApplicationContext(), Question.class);
                    i4.putExtra("act","python");
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
                        cval=Integer.parseInt(documentSnapshot.getString("c"));
                        cppval=Integer.parseInt(documentSnapshot.getString("cpp"));
                        javaval=Integer.parseInt(documentSnapshot.getString("java"));
                        pythonval=Integer.parseInt(documentSnapshot.getString("python"));
                        if(cval==0)
                        {
                            c.setEnabled(false);
                        }
                        if(cppval==0)
                        {
                            cpp.setEnabled(false);
                        }
                        if(javaval==0)
                        {
                            java.setEnabled(false);
                        }
                        if(pythonval==0)
                        {
                            python.setEnabled(false);
                        }
                        c.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent i1 = new Intent(getApplicationContext(), Final_Result.class);
                                i1.putExtra("act","c");
                                startActivity(i1);

                            }
                        });

                        cpp.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent i2 = new Intent(getApplicationContext(), Final_Result.class);
                                i2.putExtra("act","cpp");
                                startActivity(i2);
                            }
                        });

                        java.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent i3 = new Intent(getApplicationContext(), Final_Result.class);
                                i3.putExtra("act","java");
                                startActivity(i3);

                            }
                        });

                        python.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent i4 = new Intent(getApplicationContext(), Final_Result.class);
                                i4.putExtra("act","python");
                                startActivity(i4);
                            }
                        });


                    }
                }
            });


        }
    }

}
