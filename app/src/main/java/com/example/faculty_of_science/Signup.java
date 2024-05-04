package com.example.faculty_of_science;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Signup extends AppCompatActivity {
    EditText username,email,password;
    Button sign_up;
    DataBase DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        username=findViewById(R.id.username);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        sign_up=(Button) findViewById(R.id.sign_up);
        DB =new DataBase(this);
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user =username.getText().toString();
                String email_input= email.getText().toString();
                String pass =password.getText().toString();
                if(user.equals("")||email_input.equals("")||pass.equals("")){
                    Toast.makeText(Signup.this,"please enter all fields",Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean check =DB.check_email(email_input);
                    if(check == false){
                        Toast.makeText(Signup.this,"insert start",Toast.LENGTH_SHORT).show();
                        Boolean check_insert =DB.insert_data(user,email_input,pass);
                        if(check_insert == true){
                            Toast.makeText(Signup.this,"insert success",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Signup.this, MainActivity2.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(Signup.this,"insert failed",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(Signup.this,"email exist please insert anthor email",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}