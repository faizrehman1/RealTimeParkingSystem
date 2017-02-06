package com.example.faizrehman.online_parking_system;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.faizrehman.online_parking_system.Admin.AdminActivity;
import com.example.faizrehman.online_parking_system.User.UserActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainThread";
    private EditText email,pass;
    private Button loginbtn;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference firebase;
    private   TextView signupText;
    private android.support.v4.app.FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        firebase = FirebaseDatabase.getInstance().getReference();
        email = (EditText)findViewById(R.id.editText_Loginemail);
        pass = (EditText)findViewById(R.id.editText_loginpass);
        loginbtn = (Button)findViewById(R.id.login_btn);
        signupText = (TextView)findViewById(R.id.sign_up);
        fragmentManager = getSupportFragmentManager();






        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    if(user.getEmail().toString().matches("admin@gmail.com")){
                        Toast.makeText(MainActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, AdminActivity.class);
                        startActivity(intent);
                        finish();
                    }else {
                        Toast.makeText(MainActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, UserActivity.class);
                        startActivity(intent);
                        finish();
                    }
                } else {
                    // User is signed out
                    AppLogs.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };


        signupText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.beginTransaction().add(R.id.container, new signup_fragment()).addToBackStack(null).commit();
            }
        });


        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String emails = email.getText().toString();
                String passo = pass.getText().toString();
                if (emails.length() == 0) {
                    email.setError("This is Required Field");
                } else if (passo.length() == 0 && passo.length() <= 6) {
                    pass.setError("This is Required Field");
                } else {
                    try {
                        final ProgressDialog progressDialog = ProgressDialog.show(MainActivity.this, "Sign In", "Connecting...", true, false);
                        mAuth.signInWithEmailAndPassword(emails, passo).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull final Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    AppLogs.logd("signInWithEmail:onComplete:" + task.isSuccessful());
                                    progressDialog.dismiss();
                                    if(emails.toString().matches("admin@gmail.com")){
                                        Toast.makeText(MainActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(MainActivity.this, AdminActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }else {
                                        Toast.makeText(MainActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(MainActivity.this, UserActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                } else if (!task.isSuccessful()) {
                                    AppLogs.logw("signInWithEmail" + task.getException());
                                    Toast.makeText(MainActivity.this, "" + task.getException(),
                                            Toast.LENGTH_LONG).show();
                                    progressDialog.dismiss();

                                }
                            }
                        }).addOnFailureListener(MainActivity.this, new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                AppLogs.d("FailureSignin", e.getMessage());
                            }
                        });


                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

}
