package com.example.wedhall_reservationsystem;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wedhall_reservationsystem.model.User;
import com.example.wedhall_reservationsystem.utils.HashUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterPage extends AppCompatActivity {

    private EditText usernameInput, passwordInput, contactNumberInput, emailAddressInput;
    private Button okButton;
    private Button clearButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        usernameInput = findViewById(R.id.usernameInput);
        passwordInput = findViewById(R.id.passwordInput);
        contactNumberInput = findViewById(R.id.contactNumberInput);
        emailAddressInput = findViewById(R.id.emailAddress);
        okButton = findViewById(R.id.ok);
        clearButton = findViewById(R.id.clear);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
                Intent intent = new Intent(RegisterPage.this, MainActivity.class); // Redirect to MainActivity
                startActivity(intent);
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearInputFields(); // Clear fields when "Clear" is clicked
            }
        });
    }

    private void registerUser() {
        String username = usernameInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();
        String contactNo = contactNumberInput.getText().toString().trim();
        String emailAddress = emailAddressInput.getText().toString().trim();

        // Validate inputs
        if (!validateUsername(username) || !validatePassword(password) ||
                !validateContactNumber(contactNo) || !validateEmail(emailAddress)) {
            return;  // Stop if validation fails
        }

        // Hash the password using SHA-256
        String hashedPassword = HashUtil.hashPassword(password);

        // Create a User object with the hashed password
        User user = new User(username, hashedPassword, contactNo, emailAddress);

        // Retrofit to call the API
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2/wedding_hall/")  // Adjust this URL to your server's URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        Call<Void> call = apiService.registerUser(user);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, retrofit2.Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(RegisterPage.this, "Registration successful", Toast.LENGTH_SHORT).show();
                    clearInputFields(); // Clear fields after successful registration
                } else {
                    Toast.makeText(RegisterPage.this, "Registration failed: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(RegisterPage.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Method to validate username
    private boolean validateUsername(String username) {
        if (username.length() >= 15 || !username.matches("[a-zA-Z]+")) {
            Toast.makeText(this, "Username must be less than 15 letters, no numbers or symbols allowed", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    // Method to validate password
    private boolean validatePassword(String password) {
        if (password.length() >= 15 || !password.matches("[a-zA-Z0-9]+")) {
            Toast.makeText(this, "Password must be less than 15 characters and contain only letters and numbers", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    // Method to validate contact number
    private boolean validateContactNumber(String contactNo) {
        if (contactNo.length() != 10 || !contactNo.matches("[0-9]+")) {
            Toast.makeText(this, "Contact number must be exactly 10 digits", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    // Method to validate email
    private boolean validateEmail(String emailAddress) {
        if (!Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()) {
            Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    // Clear all input fields
    private void clearInputFields() {
        usernameInput.setText("");
        passwordInput.setText("");
        contactNumberInput.setText("");
        emailAddressInput.setText("");
    }
}
