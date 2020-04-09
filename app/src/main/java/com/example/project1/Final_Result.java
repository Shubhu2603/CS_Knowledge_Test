package com.example.project1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import javax.annotation.Nullable;

public class Final_Result extends AppCompatActivity {

    TextView date,time,correct,wrong,total,averg;
    String Uid;
    int t,t1;
    float avg,total1=0;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final__result);
        date=(TextView)findViewById(R.id.Date);
        correct=(TextView)findViewById(R.id.Correct_Answers);
        wrong=(TextView)findViewById(R.id.Wrong_Answers);
        total=(TextView)findViewById(R.id.Total);
        time=(TextView)findViewById(R.id.Time);
        averg=(TextView)findViewById(R.id.Average);

        fAuth=FirebaseAuth.getInstance();
        fStore=FirebaseFirestore.getInstance();

        Uid=fAuth.getCurrentUser().getUid();
        final DocumentReference dr=fStore.collection("Users").document(Uid);
        dr.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    t = Integer.parseInt(document.getString("QuizTaken"));
                         //  Toast.makeText(Final_Result.this, String.valueOf(t), Toast.LENGTH_SHORT).show();

                    for (int i = 1; i <= t; i++) {
                        Toast.makeText(Final_Result.this,"Report Generated!", Toast.LENGTH_SHORT).show();
                        String win=String.valueOf(i);
                        DocumentReference documentReference = fStore.collection("Users").document(Uid).collection("Quiz_History").document(win);
                        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    DocumentSnapshot doc = task.getResult();
                                    date.append("\n" + doc.getString("Date"));
                                    time.append("\n" + doc.getString("Time"));
                                    correct.append("\n" + doc.getString("Correct"));
                                    wrong.append("\n" + doc.getString("Wrong"));
                                    total1 += Integer.parseInt(doc.getString("Correct"));
                                    total.append("\n"+String.valueOf((Float.parseFloat(doc.getString("Correct"))/ 2) * 100) + "%");

                                    avg = ((total1 / t)/2)*100;
                                    averg.setText(String.valueOf(avg)+"%");
                                }
                            }

                        });
                    }



                }
            }
        });


        }
    }


