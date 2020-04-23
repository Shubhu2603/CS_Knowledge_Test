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

public class front_end extends AppCompatActivity {

    ImageView css,html,js;
    FirebaseFirestore fStore;
    FirebaseAuth fAuth;
    String UID;
    int cssval,htmlval,jsval;
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front_end);

        css= (ImageView) findViewById(R.id.css);
        html = (ImageView) findViewById(R.id.html);
        js = (ImageView) findViewById(R.id.js);
        back=(ImageView)findViewById(R.id.backc5);
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

            css.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i1 = new Intent(getApplicationContext(), Question.class);
                    i1.putExtra("act","css");
                    startActivity(i1);

                }
            });

            js.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i2 = new Intent(getApplicationContext(), Question.class);
                    i2.putExtra("act","js");
                    startActivity(i2);
                }
            });

            html.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i3 = new Intent(getApplicationContext(), Question.class);
                    i3.putExtra("act","html");
                    startActivity(i3);

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
                        cssval=Integer.parseInt(documentSnapshot.getString("css"));
                        jsval=Integer.parseInt(documentSnapshot.getString("js"));
                        htmlval=Integer.parseInt(documentSnapshot.getString("html"));

                        if(cssval==0)
                        {
                            css.setEnabled(false);
                        }
                        if(htmlval==0)
                        {
                            html.setEnabled(false);
                        }
                        if(jsval==0)
                        {
                            js.setEnabled(false);
                        }

                        css.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent i1 = new Intent(getApplicationContext(), Final_Result.class);
                                i1.putExtra("act","css");
                                startActivity(i1);

                            }
                        });

                        html.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent i2 = new Intent(getApplicationContext(), Final_Result.class);
                                i2.putExtra("act","html");
                                startActivity(i2);
                            }
                        });

                        js.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent i3 = new Intent(getApplicationContext(), Final_Result.class);
                                i3.putExtra("act","js");
                                startActivity(i3);

                            }
                        });

                    }
                }
            });


        }
    }
}
