package com.example.project1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    public EditText main_email,main_pass;
    public Button main_login,main_register;
    ProgressBar progressBar;
    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main_email=(EditText)findViewById(R.id.main_email);
        main_pass=(EditText)findViewById(R.id.Main_password);;
        main_login=(Button)findViewById(R.id.Main_login);
        main_register=(Button)findViewById(R.id.Main_Register);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        fAuth=FirebaseAuth.getInstance();
        main_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email=main_email.getText().toString().trim();
                String password=main_pass.getText().toString().trim();

                if(TextUtils.isEmpty(email))
                {
                    main_email.setError("Enter Email");
                    return;
                }
                if(TextUtils.isEmpty(password))
                {
                    main_pass.setError("Enter Password");
                    return;
                }
                if(password.length()<6)
                {
                    main_pass.setError("Password must be more than 6 characters");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);


                //register the user in firebase
                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(MainActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),Welcome.class));
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this, "Error!"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);

                        }
                    }
                });
            }
        });
        main_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go_to_reg=new Intent(MainActivity.this,Register.class);
                startActivity(go_to_reg);
            }

        });

    }
}
