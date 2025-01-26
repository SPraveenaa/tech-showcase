package com.example.wedhall_reservationsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class AviBir extends AppCompatActivity {

    private ImageButton kolonneImageButton;
    private ImageButton kashyapaImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avi_bir); // Ensure this matches your XML file

        // Initialize the ImageButtons
        kolonneImageButton = findViewById(R.id.KolonneIMG);
        kashyapaImageButton = findViewById(R.id.KashyapaIMG);

        // Set click listener for Kolonne ImageButton to redirect to CatKol.class
        kolonneImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AviBir.this, Catering_KolonneHotel.class);
                startActivity(intent);
            }
        });

        // Set click listener for Kashyapa ImageButton to redirect to CatKas.class
        kashyapaImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AviBir.this, Catering_KashyapaHotel.class);
                startActivity(intent);
            }
        });
    }
}
