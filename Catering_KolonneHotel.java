package com.example.wedhall_reservationsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Catering_KolonneHotel extends AppCompatActivity {

    private Button search;
    private Button skip;

    private String event; // Variable to hold the event
    private String hall;  // Variable to hold the hall
    private int priceHallPac; // Variable to hold price
    private int advanceHallPac; // Variable to hold advance
    private int RemainingHallPac; // Variable to hold remaining amount

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catering_kolonne_hotel);

        // Retrieve the event, hall, and pricing details passed from KolWedPacSelect activity
        Intent intent = getIntent();
        event = intent.getStringExtra("event"); // Get the event data
        hall = intent.getStringExtra("hall");   // Get the hall data
        priceHallPac = intent.getIntExtra("priceHallPac", 0); // Get the price data
        advanceHallPac = intent.getIntExtra("advanceHallPac", 0); // Get the advance data
        RemainingHallPac = intent.getIntExtra("RemainingHallPac", 0); // Get the remaining data

        search = findViewById(R.id.search);
        skip = findViewById(R.id.skip);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Pass the values to CatKol activity
                Intent intent = new Intent(Catering_KolonneHotel.this, CatKol.class);
                intent.putExtra("event", event); // Pass the event variable
                intent.putExtra("hall", hall);   // Pass the hall variable
                intent.putExtra("priceHallPac", priceHallPac); // Pass the price
                intent.putExtra("advanceHallPac", advanceHallPac); // Pass the advance
                intent.putExtra("RemainingHallPac", RemainingHallPac); // Pass the remaining
                startActivity(intent);
            }
        });

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Pass the values to Decoration activity
                Intent intent = new Intent(Catering_KolonneHotel.this, Decoration.class);
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
