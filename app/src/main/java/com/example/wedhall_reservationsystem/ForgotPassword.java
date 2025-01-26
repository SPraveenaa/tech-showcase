package com.example.wedhall_reservationsystem;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ForgotPassword extends AppCompatActivity {

    private EditText emailAddress;
    private Button okButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        emailAddress = findViewById(R.id.emailAddress);
        okButton = findViewById(R.id.ok);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailAddress.getText().toString().trim();
                if (!email.isEmpty()) {
                    if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        // Call the EmailSender AsyncTask to send the reset email
                        new EmailSender(ForgotPassword.this, email).execute();
                        Intent intent = new Intent(ForgotPassword.this, LoginPage.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(ForgotPassword.this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ForgotPassword.this, "Please enter your email address", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
