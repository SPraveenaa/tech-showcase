package com.example.wedhall_reservationsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wedhall_reservationsystem.model.LoginRequest;
import com.example.wedhall_reservationsystem.model.RetrofitClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPage extends AppCompatActivity {

    private EditText usernameInput, passwordInput;
    private Button okButton, clearButton;
    private Button forgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        // Initialize UI elements
        usernameInput = findViewById(R.id.usernameInput);
        passwordInput = findViewById(R.id.passwordInput);
        okButton = findViewById(R.id.ok);
        clearButton = findViewById(R.id.clear);
        forgotPassword = findViewById(R.id.forgotPassword);

        // Set onClickListener for the OK button (Login)
        okButton.setOnClickListener(v -> {
            String username = usernameInput.getText().toString();
            String password = passwordInput.getText().toString();

            // Validate inputs
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(LoginPage.this, "Please enter both username and password", Toast.LENGTH_SHORT).show();
            } else {
                System.out.println(username);
                System.out.println(password);

                loginUser(username, password);
            }
        });

        // Set onClickListener for the Clear button to clear input fields
        clearButton.setOnClickListener(v -> {
            usernameInput.setText("");
            passwordInput.setText("");
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginPage.this, ForgotPassword.class);
                startActivity(intent);
            }
        });
    }

    private void loginUser(String username, String password) {
        // Create Retrofit instance and API service
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Call<ResponseBody> call = apiService.loginUser(new LoginRequest(username, password));

        // Make the API call to validate login
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        // Convert the response to a string and then to a JSON object
                        String responseString = response.body().string();
                        JSONObject jsonResponse = new JSONObject(responseString);

                        boolean success = jsonResponse.getBoolean("success");
                        String message = jsonResponse.getString("message");

                        // Show success or failure message based on server response
                        Toast.makeText(LoginPage.this, message, Toast.LENGTH_SHORT).show();

                        // Navigate to HomeActivity if login is successful
                        if (success) {
                            Intent intent = new Intent(LoginPage.this, Home.class);
                            startActivity(intent);
                            finish(); // Close the LoginPage to prevent going back
                        }

                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    // Invalid credentials or server error
                    Toast.makeText(LoginPage.this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                // Network or server error
                Toast.makeText(LoginPage.this, "Login Failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
