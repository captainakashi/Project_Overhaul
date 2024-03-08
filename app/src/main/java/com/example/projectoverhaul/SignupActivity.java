package com.example.projectoverhaul;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignupActivity extends AppCompatActivity {

    EditText email, password, retype;
    Button signup;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        TextView textLogin = (TextView) findViewById(R.id.textLogin);

        textLogin.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
            }
        });

        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        retype=findViewById(R.id.retype);
        signup=findViewById(R.id.signup);
        progressDialog=new ProgressDialog(this);
        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PerformAuth();
            }
        });





    }

    private void PerformAuth() {
        String Email=email.getText().toString();
        String Password=password.getText().toString();
        String Retype=retype.getText().toString();

        if (TextUtils.isEmpty(Email)){
            email.setError("Email cannot be empty");
            email.requestFocus();
        }
        else if (TextUtils.isEmpty(Password)){
            password.setError("Password cannot be empty");
            password.requestFocus();
        }
        else if(!Password.equals(Retype)){
            Toast.makeText(SignupActivity.this,"Password Not matching",Toast.LENGTH_SHORT).show();


        return;
        }else





        {
            progressDialog.setMessage("Registration...");
            progressDialog.setTitle("Registration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                     if (task.isSuccessful())
                     {
                         progressDialog.dismiss();
                         sendUserToNextActivity();
                         Toast.makeText(SignupActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                     }else
                     {
                         progressDialog.dismiss();
                         Toast.makeText(SignupActivity.this,""+task.getException(), Toast.LENGTH_SHORT).show();
                     }
                }
            });


        }


    }

    private void sendUserToNextActivity() {
        Intent intent = new Intent(SignupActivity.this,LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}