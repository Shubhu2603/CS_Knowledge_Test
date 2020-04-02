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
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    public EditText reg_user,reg_pass,reg_cpass,reg_email;
    public Button reg_reg;
    public RadioGroup reg_gender;
    FirebaseAuth fAuth;
    ProgressBar progressbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        reg_user=(EditText)findViewById(R.id.reg_user);
        reg_pass=(EditText)findViewById(R.id.reg_pass);
        reg_cpass=(EditText)findViewById(R.id.reg_cpass);
        reg_email=(EditText)findViewById(R.id.reg_Email);
        reg_reg=(Button)findViewById(R.id.reg_reg);
        reg_gender=(RadioGroup) findViewById(R.id.Gender);
        fAuth=FirebaseAuth.getInstance();
        progressbar=(ProgressBar)findViewById(R.id.progressBar2);

        if(fAuth.getCurrentUser()!=null)
        {
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }

        reg_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=reg_email.getText().toString().trim();
                String password=reg_pass.getText().toString().trim();
                String cpassword=reg_cpass.getText().toString().trim();

                if(TextUtils.isEmpty(email))
                {
                    reg_email.setError("Enter Email");
                    return;
                }
                if(TextUtils.isEmpty(password))
                {
                    reg_pass.setError("Enter Password");
                    return;
                }
                if(password.length()<6)
                {
                    reg_pass.setError("Password must be more than 6 characters");
                    return;
                }
                if(password.equals(cpassword)==false)
                {
                    reg_cpass.setError("Password does not match");
                    return;
                }
                progressbar.setVisibility(View.VISIBLE);

                //register the user in firebase
                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(Register.this, "User Created!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }
                        else
                        {
                            Toast.makeText(Register.this, "Error!"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressbar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });

    }
}
