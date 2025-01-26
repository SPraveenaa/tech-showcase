package com.example.wedhall_reservationsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class CatKol extends AppCompatActivity {

    private RadioGroup menuSetOptions;
    private RadioButton radioSetMenu01;
    private Button doneButton;
    private Button skip;

    private String event; // Variable to hold the event
    private String hall;  // Variable to hold the hall
    private int priceHallPac; // Variable to hold price
    private int advanceHallPac; // Variable to hold advance
    private int RemainingHallPac; // Variable to hold remaining amount

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_kol);

        // Find the views by their IDs
        menuSetOptions = findViewById(R.id.menuSetOptions);
        radioSetMenu01 = findViewById(R.id.radio_setMenu01);
        doneButton = findViewById(R.id.DONE);
        skip = findViewById(R.id.skip);

        // Retrieve the event, hall, and pricing details passed from Catering_KolonneHotel activity
        Intent intent = getIntent();
        event = intent.getStringExtra("event");
        hall = intent.getStringExtra("hall");
        priceHallPac = intent.getIntExtra("priceHallPac", 0);
        advanceHallPac = intent.getIntExtra("advanceHallPac", 0);
        RemainingHallPac = intent.getIntExtra("RemainingHallPac", 0);

        // Set OnClickListener for the DONE button
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if radio_setMenu01 is selected
                if (radioSetMenu01.isChecked()) {
                    // Start Menuset01 activity and pass the values
                    Intent intent = new Intent(CatKol.this, MenuSet01.class);
                    intent.putExtra("event", event);
                    intent.putExtra("hall", hall);
                    intent.putExtra("priceHallPac", priceHallPac);
                    intent.putExtra("advanceHallPac", advanceHallPac);
                    intent.putExtra("RemainingHallPac", RemainingHallPac);
                    startActivity(intent);
                }
            }
        });

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CatKol.this, Decoration.class);
                // Pass the values to Decoration activity
                intent.putExtra("event", event);
                intent.putExtra("hall", hall);
                intent.putExtra("priceHallPac", priceHallPac);
                intent.putExtra("advanceHallPac", advanceHallPac);
                intent.putExtra("RemainingHallPac", RemainingHallPac);
                startActivity(intent);
            }
        });
    }
}
