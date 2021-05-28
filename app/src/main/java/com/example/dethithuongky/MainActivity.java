package com.example.dethithuongky;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {

    private EditText edtUser,edtPassword;
    private Button btnLogin, btnRegister;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connectView();
        auth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String User = edtUser.getText().toString();
                final String Password = edtPassword.getText().toString();


                if (TextUtils.isEmpty(User)) {
                    Toast.makeText(MainActivity.this, "Enter User Login !", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(Password)){
                    Toast.makeText(MainActivity.this, "Enter Password !", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Authenticate user
                auth.signInWithEmailAndPassword(User,Password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                        if (!task.isSuccessful())
                        {
                            if(Password.length()<6){
                                edtPassword.setError("Password too short, enter minimum 6 characters!");
                            } else {
                                Toast.makeText(MainActivity.this, "Authentication failed, check your email and password or sign up", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Intent intent = new Intent(MainActivity.this, Home.class);
                            startActivity(intent);

                        }

                    }
                });
            }
        });

    }
    void connectView(){
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        edtUser = (EditText) findViewById(R.id.edtUser);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnRegister = (Button) findViewById(R.id.btnRegister);
    }
}