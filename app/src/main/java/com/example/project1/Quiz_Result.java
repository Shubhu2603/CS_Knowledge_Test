package com.example.project1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.model.value.IntegerValue;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Nullable;

public class Quiz_Result extends AppCompatActivity {

    TextView q,r,w;
    Button end;
    FirebaseFirestore fStore;
    FirebaseAuth fAuth;
    String UID;
    int q1;
    String quiza;
    String qu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz__result);
        q=(TextView)findViewById(R.id.total_questions);
        r=(TextView)findViewById(R.id.correct_answers);
        w=(TextView)findViewById(R.id.wrong_answers);
        end=(Button)findViewById(R.id.end_quiz);

        fStore=FirebaseFirestore.getInstance();
        fAuth=FirebaseAuth.getInstance();

        Intent i=getIntent();
        final String ques=i.getStringExtra("total");
        final String corr=i.getStringExtra("correct");
        final String wron=i.getStringExtra("wrong");



        q.setText(ques);
        r.setText(corr);
        w.setText(wron);
        final String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        final String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());


        UID=fAuth.getCurrentUser().getUid();
        final DocumentReference dr=fStore.collection("Users").document(UID);
        dr.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful())
                {
                    DocumentSnapshot document=task.getResult();
                    quiza=document.getString("QuizTaken");


                    //Toast.makeText(Quiz_Result.this, quiza, Toast.LENGTH_SHORT).show();;
                     dr.collection("Users").document(UID);
                    if(quiza ==null)
                    {
                        q1=1;
                    }
                    else {
                        q1 = Integer.parseInt(quiza);
                        q1 += 1;
                    }
                    ;
                    qu=String.valueOf(q1);
                    DocumentReference d=fStore.collection("Users").document(UID);
                    d.update("QuizTaken",qu).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()) {
                            //    Toast.makeText(Quiz_Result.this, "Value updated!", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(Quiz_Result.this, "Failed!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    qu=String.valueOf(q1);
                    DocumentReference documentReference=fStore.collection("Users").document(UID).collection("Quiz_History").document(qu);
                    Map<String,Object> quizr=new HashMap<>();
                    quizr.put("Date",currentDate);
                    quizr.put("Time",currentTime);
                    quizr.put("Total",ques);
                    quizr.put("Correct",corr);
                    quizr.put("Wrong",wron);

                    documentReference.set(quizr).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(Quiz_Result.this, "Record Updated on Server!", Toast.LENGTH_SHORT).show();
                        }
                    });
                    end.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Toast.makeText(Quiz_Result.this, "Quiz Ended!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),Welcome.class));
                        }
                    });

                }
            }
        });




    }
}
