package com.example.faculty_of_science;



import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    TextView signupText;
    EditText email,password;
    Button login;
    DataBase DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        signupText =findViewById(R.id.signupText);
        email =findViewById(R.id.email);
        password =findViewById(R.id.password);
        login =(Button)findViewById(R.id.login);
        DB =new DataBase(this);
        signupText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, Signup.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email_input =email.getText().toString();
                String pass =password.getText().toString();
                if(email_input.equals("")||pass.equals("")){
                    Toast.makeText(MainActivity2.this,"please enter all fields",Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean check_email_pass =DB.check_email_password(email_input,pass);
                    if(check_email_pass == true){
                        Toast.makeText(MainActivity2.this,"there exist sign up , login success",Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(MainActivity2.this, home_screen.class);
                       int x=DB.getUserId(email_input,pass);
                        intent.putExtra("id",x);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(MainActivity2.this," login failed ,please signup(create account)",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }
}