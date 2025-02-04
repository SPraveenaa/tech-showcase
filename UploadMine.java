package com.example.wedhall_reservationsystem;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class UploadMine extends AppCompatActivity {

    private EditText wtsapNumberInput;
    private Button okButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_mine);

        wtsapNumberInput = findViewById(R.id.wtsapNumberInput);
        okButton = findViewById(R.id.ok);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = wtsapNumberInput.getText().toString().trim();

                if (!phoneNumber.isEmpty()) {
                    openWhatsApp(phoneNumber);
                } else {
                    Toast.makeText(UploadMine.this, "Please enter a WhatsApp number", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void openWhatsApp(String phoneNumber) {
        String url = "https://wa.me/" + phoneNumber;
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this, "WhatsApp is not installed on your device", Toast.LENGTH_SHORT).show();
        }
    }
}
