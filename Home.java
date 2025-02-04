package com.example.wedhall_reservationsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {

    private Spinner eventSpinner;
    private Button goButton;
    private String selectedEvent = ""; // Store the selected event
    private String event = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initialize the Spinner and Button
        eventSpinner = findViewById(R.id.eventSpinner);
        goButton = findViewById(R.id.GO); // Assuming there's a button with id 'goButton' in your XML layout

        // Create an ArrayAdapter with event options
        String[] events = {"Select events", "Wedding", "Birthday"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, events);

        // Set the adapter to the Spinner
        eventSpinner.setAdapter(adapter);

        // Set the onItemSelectedListener to handle user selection
        eventSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedEvent = parent.getItemAtPosition(position).toString();

                // Show a Toast only if the user selects a valid event
                if (!selectedEvent.equals("Select events")) {
                    Toast.makeText(Home.this, "Selected Event: " + selectedEvent, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        // Set the OnClickListener for the Go button
        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedEvent.equals("Birthday")) {
                    event = selectedEvent;
                    // Redirect to AviBir.class
                    Intent intent = new Intent(Home.this, DateSelection.class);
                    startActivity(intent);
                } else if (selectedEvent.equals("Wedding")) {
                    // Redirect to AviWed.class
                    event = selectedEvent;
                    Intent intent = new Intent(Home.this, DateSelection.class);
                    // Pass the selected event to AviWed activity
                    intent.putExtra("event", event);
                    startActivity(intent);
                } else {
                    Toast.makeText(Home.this, "Please select an event", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
