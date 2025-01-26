package com.example.wedhall_reservationsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class AviWed extends AppCompatActivity {

    private String hall = "";
    private String event = ""; // Store the event received from Home activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avi_wed);

        // Retrieve the event passed from Home activity
        Intent intent = getIntent();
        event = intent.getStringExtra("event"); // Get the event data

        // Find the ImageButtons by their IDs
        ImageButton kolonneIMG = findViewById(R.id.KolonneIMG);
        ImageButton kashyapaIMG = findViewById(R.id.KashyapaIMG);

        // Set OnClickListener for KolonneIMG
        kolonneIMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hall = "KolonneRiverSideHotel";
                // Start KolWedPackages activity
                Intent intent = new Intent(AviWed.this, KolWedPackages.class);
                // Pass both event and hall variables to the next activity
                intent.putExtra("event", event);
                intent.putExtra("hall", hall);
                startActivity(intent);
            }
        });

        // Set OnClickListener for KashyapaIMG
        kashyapaIMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hall = "KashyapaHotel";
                // Start KolWedPackages activity
                Intent intent = new Intent(AviWed.this, KolWedPackages.class);
                // Pass both event and hall variables to the next activity
                intent.putExtra("event", event);
                intent.putExtra("hall", hall);
                startActivity(intent);
            }
        });
    }
}
