package com.example.madfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

//Insert details
public class AddActivity extends AppCompatActivity {

    EditText fname,mobile,email,menu,date;
    Button btnAdd, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        fname=(EditText)findViewById(R.id.txtName);
        mobile=(EditText)findViewById(R.id.txtMobile);
        email=(EditText)findViewById(R.id.txtEmail);
        menu=(EditText)findViewById(R.id.txtMenu);
        date=(EditText)findViewById(R.id.txtDate);
        btnAdd=(Button)findViewById(R.id.Add);
        btnBack=(Button)findViewById(R.id.Back);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inserdata();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void inserdata(){
        Map<String,Object> map = new HashMap<>();
        map.put("fname",fname.getText().toString());
        map.put("mobile",mobile.getText().toString());
        map.put("email",email.getText().toString());
        map.put("date",date.getText().toString());
        map.put("menu",menu.getText().toString());
        //Database
        FirebaseDatabase.getInstance().getReference().child("ResDetails").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AddActivity.this,"Data inserted successfully",Toast.LENGTH_SHORT).show();
                    }
                })

                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {

                        Toast.makeText(AddActivity.this,"Error",Toast.LENGTH_SHORT).show();
                    }
                });
    }
}