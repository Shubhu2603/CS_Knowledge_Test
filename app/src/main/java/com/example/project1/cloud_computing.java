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

public class cloud_computing extends AppCompatActivity {

    ImageView aws,azure,gcloud,ibmcloud;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String UID;
    int awsval,azureval,gcloudval,ibmcloudval;
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloud_computing);

        back=(ImageView)findViewById(R.id.backc3);
        aws = (ImageView) findViewById(R.id.aws);
        azure = (ImageView) findViewById(R.id.azure);
        gcloud = (ImageView) findViewById(R.id.google_cloud);
        ibmcloud = (ImageView) findViewById(R.id.ibm_cloud);

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

            aws.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i1 = new Intent(getApplicationContext(), Question.class);
                    i1.putExtra("act","aws");
                    startActivity(i1);

                }
            });

            azure.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i2 = new Intent(getApplicationContext(), Question.class);
                    i2.putExtra("act","azure");
                    startActivity(i2);
                }
            });

            gcloud.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i3 = new Intent(getApplicationContext(), Question.class);
                    i3.putExtra("act","gcloud");
                    startActivity(i3);

                }
            });

            ibmcloud.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i4 = new Intent(getApplicationContext(), Question.class);
                    i4.putExtra("act","ibmcloud");
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
                        awsval=Integer.parseInt(documentSnapshot.getString("aws"));
                        azureval=Integer.parseInt(documentSnapshot.getString("azure"));
                        gcloudval=Integer.parseInt(documentSnapshot.getString("gcloud"));
                        ibmcloudval=Integer.parseInt(documentSnapshot.getString("ibmcloud"));
                        if(awsval==0)
                        {
                            aws.setEnabled(false);
                        }
                        if(azureval==0)
                        {
                            azure.setEnabled(false);
                        }
                        if(gcloudval==0)
                        {
                            gcloud.setEnabled(false);
                        }
                        if(ibmcloudval==0)
                        {
                            ibmcloud.setEnabled(false);
                        }
                        aws.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent i1 = new Intent(getApplicationContext(), Final_Result.class);
                                i1.putExtra("act","aws");
                                startActivity(i1);

                            }
                        });

                        azure.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent i2 = new Intent(getApplicationContext(), Final_Result.class);
                                i2.putExtra("act","azure");
                                startActivity(i2);
                            }
                        });

                        gcloud.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent i3 = new Intent(getApplicationContext(), Final_Result.class);
                                i3.putExtra("act","gcloud");
                                startActivity(i3);

                            }
                        });

                        ibmcloud.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent i4 = new Intent(getApplicationContext(), Final_Result.class);
                                i4.putExtra("act", "ibmcloud");
                                startActivity(i4);
                            }
                        });


                    }
                }
            });


        }

    }
}
