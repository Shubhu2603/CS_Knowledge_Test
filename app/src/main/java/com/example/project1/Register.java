package com.example.project1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    public EditText reg_user,reg_pass,reg_cpass,reg_email;
    public Button reg_reg;
    public RadioGroup reg_gender;
    TextView click;
    FirebaseAuth fAuth;
    ProgressBar progressbar;
    FirebaseFirestore fStore;
    String UserID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        click=(TextView)findViewById(R.id.click_to_log);
        reg_user=(EditText)findViewById(R.id.reg_user);
        reg_pass=(EditText)findViewById(R.id.reg_pass);
        reg_cpass=(EditText)findViewById(R.id.reg_cpass);
        reg_email=(EditText)findViewById(R.id.reg_Email);
        reg_reg=(Button)findViewById(R.id.reg_reg);
        reg_gender=(RadioGroup) findViewById(R.id.Gender);
        fAuth=FirebaseAuth.getInstance();
        fStore=FirebaseFirestore.getInstance();
        progressbar=(ProgressBar)findViewById(R.id.progressBar2);

       /* if(fAuth.getCurrentUser()!=null)
        {
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }*/
       click.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(getApplicationContext(),MainActivity.class));
           }
       });

        reg_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email=reg_email.getText().toString().trim();
                String password=reg_pass.getText().toString().trim();
                String cpassword=reg_cpass.getText().toString().trim();
                final String name=reg_user.getText().toString();

                final String a="0";

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
                if(!password.equals(cpassword))
                {
                    reg_cpass.setError("Password does not match");
                    return;
                }
                progressbar.setVisibility(View.VISIBLE);

                final String gender_val=((RadioButton)findViewById(reg_gender.getCheckedRadioButtonId())).getText().toString();
                //register the user in firebase
                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(Register.this, "User Created!", Toast.LENGTH_SHORT).show();
                            UserID=fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference=fStore.collection("Users").document(UserID);
                            Map<String,Object> user=new HashMap<>();
                            user.put("Name",name);
                            user.put("Email",email);
                            user.put("Gender",gender_val);
                            user.put("c",a);
                            user.put("cpp",a);
                            user.put("java",a);
                            user.put("python",a);
                            user.put("html",a);
                            user.put("css",a);
                            user.put("js",a);
                            user.put("networking",a);
                            user.put("security",a);
                            user.put("sqlite",a);
                            user.put("mysql",a);
                            user.put("mongodb",a);
                            user.put("oracle",a);
                            user.put("aws",a);
                            user.put("gcloud",a);
                            user.put("ibmcloud",a);
                            user.put("azure",a);
                            user.put("AI",a);
                            user.put("IOT",a);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d("TAG", "onSuccess:User profile is created for "+UserID);
                                }
                            });
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
