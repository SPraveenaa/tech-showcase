package com.example.wedhall_reservationsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class SelectedDecorationDetails extends AppCompatActivity {

    private ImageButton morepic;
    private Button select;

    private int totalPrice = 0;
    private int advancePayment = 0;

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
    private String menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_decoration_details);

        // Retrieve the values passed from Decoration activity
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
        decorationTheme = intent.getStringExtra("decorationTheme"); // Retrieve the theme
        menu = intent.getStringExtra("menu");

        morepic = findViewById(R.id.morepic);
        select = findViewById(R.id.select);

        morepic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectedDecorationDetails.this, DecorationImages.class);
                startActivity(intent);
            }
        });

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                totalPrice = 120000; // Example value for total decoration price
                advancePayment = 20000; // Example value for advance payment

                Intent intent = new Intent(SelectedDecorationDetails.this, ThreeButtonSelection.class);

                // Pass all necessary values to the next activity
                intent.putExtra("totalPriceDeco", totalPrice);
                intent.putExtra("advancePaymentDeco", advancePayment);
                intent.putExtra("event", event);
                intent.putExtra("hall", hall);
                intent.putExtra("priceHallPac", priceHallPac);
                intent.putExtra("advanceHallPac", advanceHallPac);
                intent.putExtra("remainingHallPac", remainingHallPac);
                intent.putExtra("plateCount", plateCount);
                intent.putExtra("totalAmount", totalAmount);
                intent.putExtra("advanceAmount", advanceAmount);
                intent.putExtra("remainingAmount", remainingAmount);
                intent.putExtra("decorationTheme", decorationTheme);
                intent.putExtra("menu", menu);


                startActivity(intent);
            }
        });
    }
}
