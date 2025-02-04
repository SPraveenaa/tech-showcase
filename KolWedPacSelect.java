package com.example.wedhall_reservationsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class KolWedPacSelect extends AppCompatActivity {

    private Button Done;
    private int priceHallPac, advanceHallPac, RemainingHallPac;
    private String event; // Variable to hold the event
    private String hall;  // Variable to hold the hall

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kol_wed_pac_select);

        // Retrieve the event and hall passed from KolWedPackages activity
        Intent intent = getIntent();
        event = intent.getStringExtra("event"); // Get the event data
        hall = intent.getStringExtra("hall");   // Get the hall data

        Done = findViewById(R.id.Done);

        Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                priceHallPac = 400000;
                advanceHallPac = 40000;
                RemainingHallPac = 360000;

                // Pass the event, hall, and pricing details to Catering_KolonneHotel
                Intent intent = new Intent(KolWedPacSelect.this, Catering_KolonneHotel.class);
                intent.putExtra("event", event); // Pass the event variable
                intent.putExtra("hall", hall);   // Pass the hall variable
                intent.putExtra("priceHallPac", priceHallPac); // Pass the price
                intent.putExtra("advanceHallPac", advanceHallPac); // Pass the advance
                intent.putExtra("RemainingHallPac", RemainingHallPac); // Pass the remaining
                startActivity(intent);
            }
        });
    }
}
