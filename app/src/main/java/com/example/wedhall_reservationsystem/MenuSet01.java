package com.example.wedhall_reservationsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MenuSet01 extends AppCompatActivity {

    private Button select;
    private String menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_set01);

        select = findViewById(R.id.select);

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Set the menu value to be passed
                menu = "set01";

                // Retrieve the values passed from CatKol activity
                Intent intent = getIntent();
                String event = intent.getStringExtra("event");
                String hall = intent.getStringExtra("hall");
                int priceHallPac = intent.getIntExtra("priceHallPac", 0);
                int advanceHallPac = intent.getIntExtra("advanceHallPac", 0);
                int RemainingHallPac = intent.getIntExtra("RemainingHallPac", 0);

                // Create the intent for the next activity and pass the values
                Intent nextIntent = new Intent(MenuSet01.this, CateringCount.class);
                nextIntent.putExtra("event", event);
                nextIntent.putExtra("hall", hall);
                nextIntent.putExtra("priceHallPac", priceHallPac);
                nextIntent.putExtra("advanceHallPac", advanceHallPac);
                nextIntent.putExtra("RemainingHallPac", RemainingHallPac);
                nextIntent.putExtra("menu", menu); // Pass the menu variable
                startActivity(nextIntent);
            }
        });
    }
}
