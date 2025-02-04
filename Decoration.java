package com.example.wedhall_reservationsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class Decoration extends AppCompatActivity {

    private Button moreDetailsButton;
    private Button uploadButton;
    private Button skipButton;

    private String event;
    private String hall;
    private int priceHallPac;
    private int advanceHallPac;
    private int remainingHallPac;
    private int plateCount;
    private double totalAmount;
    private double advanceAmount;
    private double remainingAmount;
    private String decorationTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decoration);

        // Initialize the views
        initializeViews();

        // Retrieve data from Intent
        retrieveDataFromIntent();

        // Setup listeners for buttons
        setupButtonListeners();
    }

    /**
     * Initializes the view elements.
     */
    private void initializeViews() {
        moreDetailsButton = findViewById(R.id.MoreDetails);
        uploadButton = findViewById(R.id.upload);
        skipButton = findViewById(R.id.skip);
    }

    /**
     * Retrieves data passed through the Intent from the previous activity.
     */
    private void retrieveDataFromIntent() {
        Intent intent = getIntent();
        event = intent.getStringExtra("event");
        hall = intent.getStringExtra("hall");
        priceHallPac = intent.getIntExtra("priceHallPac", 0);
        advanceHallPac = intent.getIntExtra("advanceHallPac", 0);
        remainingHallPac = intent.getIntExtra("RemainingHallPac", 0);
        plateCount = intent.getIntExtra("plateCount", 0);
        totalAmount = intent.getDoubleExtra("totalAmount", 0.0);
        advanceAmount = intent.getDoubleExtra("advanceAmount", 0.0);
        remainingAmount = intent.getDoubleExtra("remainingAmount", 0.0);
        decorationTheme = intent.getStringExtra("decorationTheme"); // This should also be passed
        String menu = intent.getStringExtra("menu"); // Retrieve menu if needed

        // Log to verify data is passed correctly
        logData();
    }

    /**
     * Logs the data to the console for debugging.
     */
    private void logData() {
        System.out.println("Event: " + event);
        System.out.println("Hall: " + hall);
        System.out.println("Total Amount: " + totalAmount);
        System.out.println("Advance Amount: " + advanceAmount);
        System.out.println("Remaining Amount: " + remainingAmount);
        System.out.println("Decoration Theme: " + decorationTheme);
    }

    /**
     * Sets up listeners for the buttons.
     */
    private void setupButtonListeners() {
        moreDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Set decoration theme if needed
                decorationTheme = "theme01"; // Change as per your requirement

                // Pass data to the next activity
                Intent intent = new Intent(Decoration.this, SelectedDecorationDetails.class);
                passDataToNextActivity(intent);
                startActivity(intent);
            }
        });

        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Skip button click listener
                Intent intent = new Intent(Decoration.this, ThreeButtonSelection.class);
                startActivity(intent);
            }
        });

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to UploadMine activity
                Intent intent = new Intent(Decoration.this, UploadMine.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Passes the collected data to the next activity.
     *
     * @param intent The intent to the next activity.
     */
    private void passDataToNextActivity(Intent intent) {
        intent.putExtra("event", event);
        intent.putExtra("hall", hall);
        intent.putExtra("priceHallPac", priceHallPac);
        intent.putExtra("advanceHallPac", advanceHallPac);
        intent.putExtra("RemainingHallPac", remainingHallPac);
        intent.putExtra("plateCount", plateCount);
        intent.putExtra("totalAmount", totalAmount);
        intent.putExtra("advanceAmount", advanceAmount);
        intent.putExtra("remainingAmount", remainingAmount);
        intent.putExtra("decorationTheme", decorationTheme); // Pass the theme
        intent.putExtra("menu", getIntent().getStringExtra("menu"));
    }
}
