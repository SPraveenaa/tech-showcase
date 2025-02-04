package com.example.wedhall_reservationsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class KolWedPackages extends AppCompatActivity {

    private Button select01;
    private Button morepic01;
    private String event; // Variable to hold the event
    private String hall;  // Variable to hold the hall
    private int maxPlateCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kol_wed_packages);

        maxPlateCount = 200;

        // Retrieve the event and hall passed from AviWed activity
        Intent intent = getIntent();
        event = intent.getStringExtra("event"); // Get the event data
        hall = intent.getStringExtra("hall");   // Get the hall data

        select01 = findViewById(R.id.select01);
        morepic01 = findViewById(R.id.morepic01);

        select01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(KolWedPackages.this, KolWedPacSelect.class);
                // Pass the event and hall to KolWedPacSelect
                intent.putExtra("event", event);
                intent.putExtra("hall", hall);
                startActivity(intent);
            }
        });

        morepic01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(KolWedPackages.this, PackagePictures.class);
                // Pass the event and hall to PackagePictures if needed
                intent.putExtra("event", event);
                intent.putExtra("hall", hall);
                startActivity(intent);
            }
        });
    }
}
