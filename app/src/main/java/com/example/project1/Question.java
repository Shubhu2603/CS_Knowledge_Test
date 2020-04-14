package com.example.project1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class Question extends AppCompatActivity {
    Button o1,o2,o3,o4;
    TextView question;
    int total=0,correct=0,wrong=0;
    DatabaseReference reference;
    Long endat=100L;
    String cat;
    int[] arr=new int[20];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        o1=(Button)findViewById(R.id.option1);
        o2=(Button)findViewById(R.id.option2);
        o3=(Button)findViewById(R.id.option3);
        o4=(Button)findViewById(R.id.option4);
        for(int i=0;i<20;i++)
        {
            arr[i]=i+1;
        }
        arr=RandomizeArray(arr);
        question=(TextView)findViewById(R.id.question);
        updatequestion(endat);
    }

    public static int[] RandomizeArray(int[] array){
        Random rgen = new Random();  // Random number generator

        for (int i=0; i<array.length; i++) {
            int randomPosition = rgen.nextInt(array.length);
            int temp = array[i];
            array[i] = array[randomPosition];
            array[randomPosition] = temp;
        }

        return array;
    }

    private void updatequestion(final long l_endat) {
        Intent i=getIntent();
        cat=i.getStringExtra("act");
        final Drawable red=getResources().getDrawable(R.drawable.button_red);
        final Drawable green=getResources().getDrawable(R.drawable.button_green);
        final Drawable white=getResources().getDrawable(R.drawable.button);
        total++;
        if(total>20)
        {
            //Open the result activity
            Intent myintent=new Intent(Question.this,Quiz_Result.class);
            total--;
            myintent.putExtra("total",String.valueOf(total));
            myintent.putExtra("correct",String.valueOf(correct));
            myintent.putExtra("wrong",String.valueOf(wrong));
            myintent.putExtra("quiz",String.valueOf(cat));
            startActivity(myintent);
        }
        else
        {
            FirebaseDatabase.getInstance();//.setPersistenceEnabled(true);

            reference= FirebaseDatabase.getInstance().getReference().child(cat).child(String.valueOf(arr[total-1]));//.endAt(l_endat).limitToLast(1);
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final Question_db quest=dataSnapshot.getValue(Question_db.class);
                question.setText(quest.getquestion());
                o1.setText(quest.getoption1());
                o2.setText(quest.getoption2());
                o3.setText(quest.getoption3());
                o4.setText(quest.getoption4());

                o1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(o1.getText().toString().equals(quest.getanswer()))
                        {
                            Toast.makeText(getApplicationContext(), "Correct!", Toast.LENGTH_SHORT).show();
                            o1.setBackground(green);
                            Handler handler=new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    correct++;
                                    o1.setBackground(white);
                                    updatequestion(endat);
                                }
                            },1500);
                        }
                        else
                        {
                            //answer is wrong
                            Toast.makeText(getApplicationContext(), "Incorrect!", Toast.LENGTH_SHORT).show();
                            wrong++;
                            o1.setBackground(red);
                            if(o2.getText().toString().equals(quest.getanswer()))
                            {
                                o2.setBackground(green);
                            }
                            else if(o3.getText().toString().equals(quest.getanswer()))
                            {
                                o3.setBackground(green);
                            }
                            else if(o4.getText().toString().equals(quest.getanswer()))
                            {
                                o4.setBackground(green);
                            }

                            Handler handler=new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    o1.setBackground(white);
                                    o2.setBackground(white);
                                    o3.setBackground(white);
                                    o4.setBackground(white);
                                    updatequestion(endat);

                                }
                            },1500);

                        }
                    }
                });


                o2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(o2.getText().toString().equals(quest.getanswer()))
                            {
                                Toast.makeText(getApplicationContext(), "Correct!", Toast.LENGTH_SHORT).show();
                                o2.setBackground(green);
                                Handler handler=new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct++;
                                        o2.setBackground(white);
                                        updatequestion(endat);
                                    }
                                },1500);
                            }
                            else
                            {
                                //answer is wrong
                                Toast.makeText(getApplicationContext(), "Incorrect!", Toast.LENGTH_SHORT).show();
                                wrong++;
                                o2.setBackground(red);
                                if(o1.getText().toString().equals(quest.getanswer()))
                                {
                                    o1.setBackground(green);
                                }
                                else if(o3.getText().toString().equals(quest.getanswer()))
                                {
                                    o3.setBackground(green);
                                }
                                else if(o4.getText().toString().equals(quest.getanswer()))
                                {
                                    o4.setBackground(green);
                                }

                                Handler handler=new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        o1.setBackground(white);
                                        o2.setBackground(white);
                                        o3.setBackground(white);
                                        o4.setBackground(white);
                                        updatequestion(endat);

                                    }
                                },1500);

                            }
                        }
                    });

                    o3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(o3.getText().toString().equals(quest.getanswer()))
                            {
                                Toast.makeText(getApplicationContext(), "Correct!", Toast.LENGTH_SHORT).show();
                                o3.setBackground(green);
                                Handler handler=new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct++;
                                        o3.setBackground(white);
                                        updatequestion(endat);
                                    }
                                },1500);
                            }
                            else
                            {
                                //answer is wrong
                                Toast.makeText(getApplicationContext(), "Incorrect!", Toast.LENGTH_SHORT).show();
                                wrong++;
                                o3.setBackground(red);
                                if(o2.getText().toString().equals(quest.getanswer()))
                                {
                                    o2.setBackground(green);
                                }
                                else if(o1.getText().toString().equals(quest.getanswer()))
                                {
                                    o1.setBackground(green);
                                }
                                else if(o4.getText().toString().equals(quest.getanswer()))
                                {
                                    o4.setBackground(green);
                                }

                                Handler handler=new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        o1.setBackground(white);
                                        o2.setBackground(white);
                                        o3.setBackground(white);
                                        o4.setBackground(white);
                                        updatequestion(endat);

                                    }
                                },1500);

                            }
                        }
                    });

                    o4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(o4.getText().toString().equals(quest.getanswer()))
                            {
                                Toast.makeText(getApplicationContext(), "Correct!", Toast.LENGTH_SHORT).show();
                                o4.setBackground(green);
                                Handler handler=new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct++;
                                        o4.setBackground(white);
                                        updatequestion(endat);
                                    }
                                },1500);
                            }
                            else
                            {
                                //answer is wrong
                                Toast.makeText(getApplicationContext(), "Incorrect!", Toast.LENGTH_SHORT).show();
                                wrong++;
                                o4.setBackground(red);
                                if(o2.getText().toString().equals(quest.getanswer()))
                                {
                                    o2.setBackground(green);
                                }
                                else if(o3.getText().toString().equals(quest.getanswer()))
                                {
                                    o3.setBackground(green);
                                }
                                else if(o1.getText().toString().equals(quest.getanswer()))
                                {
                                    o1.setBackground(green);
                                }

                                Handler handler=new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        o1.setBackground(white);
                                        o2.setBackground(white);
                                        o3.setBackground(white);
                                        o4.setBackground(white);
                                        updatequestion(endat);

                                    }
                                },1500);

                            }
                        }
                    });

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }
}
