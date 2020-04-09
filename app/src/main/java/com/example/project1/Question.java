package com.example.project1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
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

public class Question extends AppCompatActivity {
    Button o1,o2,o3,o4;
    TextView question;
    int total=0,correct=0,wrong=0;
    DatabaseReference reference;
    Long endat=100L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        o1=(Button)findViewById(R.id.option1);
        o2=(Button)findViewById(R.id.option2);
        o3=(Button)findViewById(R.id.option3);
        o4=(Button)findViewById(R.id.option4);
        question=(TextView)findViewById(R.id.question);
        updatequestion(endat);
    }

    private void updatequestion(final long l_endat) {
        total++;
        if(total>2)
        {
            //Open the result activity
            startActivity(new Intent(getApplicationContext(),Welcome.class));

        }
        else
        {
            FirebaseDatabase.getInstance();//.setPersistenceEnabled(true);
            reference= FirebaseDatabase.getInstance().getReference().child("Questions").child(String.valueOf(total));//.endAt(l_endat).limitToLast(1);
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
                            o1.setBackgroundColor(Color.GREEN);
                            Handler handler=new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    correct++;
                                    o1.setBackgroundColor(Color.parseColor("#03A9F4"));
                                    updatequestion(endat);
                                }
                            },1500);
                        }
                        else
                        {
                            //answer is wrong
                            Toast.makeText(getApplicationContext(), "Incorrect!", Toast.LENGTH_SHORT).show();
                            wrong++;
                            o1.setBackgroundColor(Color.RED);
                            if(o2.getText().toString().equals(quest.getanswer()))
                            {
                                o2.setBackgroundColor(Color.GREEN);
                            }
                            else if(o3.getText().toString().equals(quest.getanswer()))
                            {
                                o3.setBackgroundColor(Color.GREEN);
                            }
                            else if(o4.getText().toString().equals(quest.getanswer()))
                            {
                                o4.setBackgroundColor(Color.GREEN);
                            }

                            Handler handler=new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    o1.setBackgroundColor(Color.parseColor("#03A9F4"));
                                    o2.setBackgroundColor(Color.parseColor("#03A9F4"));
                                    o3.setBackgroundColor(Color.parseColor("#03A9F4"));
                                    o4.setBackgroundColor(Color.parseColor("#03A9F4"));
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
                                o2.setBackgroundColor(Color.GREEN);
                                Handler handler=new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct++;
                                        o2.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        updatequestion(endat);
                                    }
                                },1500);
                            }
                            else
                            {
                                //answer is wrong
                                Toast.makeText(getApplicationContext(), "Incorrect!", Toast.LENGTH_SHORT).show();
                                wrong++;
                                o2.setBackgroundColor(Color.RED);
                                if(o1.getText().toString().equals(quest.getanswer()))
                                {
                                    o1.setBackgroundColor(Color.GREEN);
                                }
                                else if(o3.getText().toString().equals(quest.getanswer()))
                                {
                                    o3.setBackgroundColor(Color.GREEN);
                                }
                                else if(o4.getText().toString().equals(quest.getanswer()))
                                {
                                    o4.setBackgroundColor(Color.GREEN);
                                }

                                Handler handler=new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        o1.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        o2.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        o3.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        o4.setBackgroundColor(Color.parseColor("#03A9F4"));
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
                                o3.setBackgroundColor(Color.GREEN);
                                Handler handler=new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct++;
                                        o3.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        updatequestion(endat);
                                    }
                                },1500);
                            }
                            else
                            {
                                //answer is wrong
                                Toast.makeText(getApplicationContext(), "Incorrect!", Toast.LENGTH_SHORT).show();
                                wrong++;
                                o3.setBackgroundColor(Color.RED);
                                if(o2.getText().toString().equals(quest.getanswer()))
                                {
                                    o2.setBackgroundColor(Color.GREEN);
                                }
                                else if(o3.getText().toString().equals(quest.getanswer()))
                                {
                                    o1.setBackgroundColor(Color.GREEN);
                                }
                                else if(o4.getText().toString().equals(quest.getanswer()))
                                {
                                    o4.setBackgroundColor(Color.GREEN);
                                }

                                Handler handler=new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        o1.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        o2.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        o3.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        o4.setBackgroundColor(Color.parseColor("#03A9F4"));
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
                                o4.setBackgroundColor(Color.GREEN);
                                Handler handler=new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct++;
                                        o4.setBackgroundColor(Color.parseColor("03A9F4"));
                                        updatequestion(endat);
                                    }
                                },1500);
                            }
                            else
                            {
                                //answer is wrong
                                Toast.makeText(getApplicationContext(), "Incorrect!", Toast.LENGTH_SHORT).show();
                                wrong++;
                                o4.setBackgroundColor(Color.RED);
                                if(o2.getText().toString().equals(quest.getanswer()))
                                {
                                    o2.setBackgroundColor(Color.GREEN);
                                }
                                else if(o3.getText().toString().equals(quest.getanswer()))
                                {
                                    o3.setBackgroundColor(Color.GREEN);
                                }
                                else if(o4.getText().toString().equals(quest.getanswer()))
                                {
                                    o1.setBackgroundColor(Color.GREEN);
                                }

                                Handler handler=new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        o1.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        o2.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        o3.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        o4.setBackgroundColor(Color.parseColor("#03A9F4"));
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
