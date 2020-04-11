package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.SnapshotMetadata;

import javax.annotation.Nullable;

public class Profile extends AppCompatActivity {

    TextView profile_name1,profile_email1;
    ImageView pic_male,pic_female,back;
    FirebaseFirestore fStore;
    FirebaseAuth fAuth;
    String UserID,gen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profile_email1=(TextView)findViewById(R.id.profile_email);
        profile_name1=(TextView)findViewById(R.id.profile_name);
        pic_male=(ImageView)findViewById(R.id.pic_male);
        pic_female=(ImageView)findViewById(R.id.pic_female);
        fAuth=FirebaseAuth.getInstance();
        back=(ImageView)findViewById(R.id.backp);
        fStore=FirebaseFirestore.getInstance();
        UserID=fAuth.getCurrentUser().getUid();
        DocumentReference documentReference=fStore.collection("Users").document(UserID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                profile_email1.setText(documentSnapshot.getString("Email"));
                profile_name1.setText(documentSnapshot.getString("Name"));
                gen=documentSnapshot.getString("Gender");
                if(gen.equals("Male"))
                {
                    pic_male.setVisibility(View.VISIBLE);
                }
                if(gen.equals("Female"))
                {
                    pic_female.setVisibility(View.VISIBLE);
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Welcome.class));
            }
        });
    }
}
