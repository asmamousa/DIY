package examples.android.example.com.diy;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignInActivity extends AppCompatActivity {

    @BindView(R.id.sign_in_button)
    SignInButton signInButton;

    @BindView(R.id.TXT_EMAIL)
    EditText txtEmail;

    @BindView(R.id.TXT_PASS)
    EditText txtPassword;

    @BindView(R.id.sign_Up)
    Button signUp;

    @BindView(R.id.adView)
    AdView mAdView;

    private FirebaseAuth firebaseAuth;
    private String email="";
    private String password="";
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);

        firebaseAuth = FirebaseAuth.getInstance();

        mDatabase = FirebaseDatabase.getInstance().getReference();


        MobileAds.initialize(this,"ca-app-pub-3179158580945836~3286239202");

                AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);



        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = txtEmail.getText().toString();
                password = txtPassword.getText().toString();

                if (!email.isEmpty() && !password.isEmpty())  {

                        firebaseAuth.signInWithEmailAndPassword(email, password)
                                .addOnCompleteListener(SignInActivity.this, new OnCompleteListener<AuthResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<AuthResult> task) {
                                                if (task.isSuccessful()) {


                                                    Intent intent = new Intent(getApplicationContext(), videosActivity.class);
                                                    startActivity(intent);


                                                } else {

                                                    Toast.makeText(getApplicationContext(), R.string.error_log_in, Toast.LENGTH_LONG).show();
                                                }
                                            }

                                        }
                                );


                } else
                    Toast.makeText(getApplicationContext(), R.string.emptyField, Toast.LENGTH_LONG).show();


            }
        });


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = txtEmail.getText().toString();
                password = txtPassword.getText().toString();

                if (!email.isEmpty() && !password.isEmpty()) {

                        firebaseAuth.createUserWithEmailAndPassword(email, password)
                                .addOnCompleteListener(SignInActivity.this, new OnCompleteListener<AuthResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<AuthResult> task) {
                                                if (task.isSuccessful()) {


                                                    firebaseAuth.signInWithEmailAndPassword(email, password)
                                                            .addOnCompleteListener(SignInActivity.this, new OnCompleteListener<AuthResult>() {
                                                                        @Override
                                                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                                                            if (task.isSuccessful()) {

                                                                                mDatabase.child("users").push().setValue(email);




                                                                                Intent intent = new Intent(getApplicationContext(), videosActivity.class);
                                                                                startActivity(intent);


                                                                            } else {

                                                                                Toast.makeText(getApplicationContext(), R.string.error_log_in, Toast.LENGTH_LONG).show();
                                                                            }
                                                                        }

                                                                    }
                                                            );

                                                } else {

                                                    Toast.makeText(getApplicationContext(), R.string.regiter_error, Toast.LENGTH_LONG).show();
                                                }
                                            }

                                        }
                                );


                } else
                    Toast.makeText(getApplicationContext(), R.string.emptyField, Toast.LENGTH_LONG).show();




            }
        });


    }





}


