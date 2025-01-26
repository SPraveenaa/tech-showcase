package com.example.wedhall_reservationsystem;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;
import java.util.Locale;

public class CateringCount extends AppCompatActivity {

    private EditText plateCountInput;
    private TextView totalAmountText;
    private TextView advanceText;
    private TextView remainingText;
    private Button done;

    private String event;
    private String hall;
    private String menu;
    private int priceHallPac;
    private int advanceHallPac;
    private int RemainingHallPac;

    private int plateCount;
    private double totalAmount;
    private double advanceAmount;
    private double remainingAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catering_count);

        // Retrieve the values passed from the previous activity
        Intent intent = getIntent();
        event = intent.getStringExtra("event");
        hall = intent.getStringExtra("hall");
        priceHallPac = intent.getIntExtra("priceHallPac", 0);
        advanceHallPac = intent.getIntExtra("advanceHallPac", 0);
        RemainingHallPac = intent.getIntExtra("RemainingHallPac", 0);
        menu = intent.getStringExtra("menu");

        // Initialize the UI components
        plateCountInput = findViewById(R.id.plateCountInput);
        totalAmountText = findViewById(R.id.totalcount);
        advanceText = findViewById(R.id.advance);
        remainingText = findViewById(R.id.remaining);
        done = findViewById(R.id.Done);

        done.setEnabled(false); // Initially disable the Done button

        // Add a TextWatcher to update total amount as the user enters the plate count
        plateCountInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                validatePlateCount();
            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateTotal();

                // Log to check values before passing to the next activity
                Log.d("CateringCount", "Total Amount: " + totalAmount);
                Log.d("CateringCount", "Advance Amount: " + advanceAmount);
                Log.d("CateringCount", "Remaining Amount: " + remainingAmount);

                // Pass the calculated values to the next activity
                Intent intent = new Intent(CateringCount.this, Decoration.class);
                intent.putExtra("event", event);
                intent.putExtra("hall", hall);
                intent.putExtra("priceHallPac", priceHallPac);
                intent.putExtra("advanceHallPac", advanceHallPac);
                intent.putExtra("RemainingHallPac", RemainingHallPac);
                intent.putExtra("plateCount", plateCount);
                intent.putExtra("totalAmount", totalAmount);
                intent.putExtra("advanceAmount", advanceAmount);
                intent.putExtra("remainingAmount", remainingAmount);
                intent.putExtra("menu", menu);
                startActivity(intent);

                Toast.makeText(CateringCount.this, "Successfully noted", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Method to validate plate count
    private void validatePlateCount() {
        String plateCountStr = plateCountInput.getText().toString();

        if (!plateCountStr.isEmpty()) {
            try {
                plateCount = Integer.parseInt(plateCountStr);

                if (plateCount < 100 || plateCount > 1000) {
                    Toast.makeText(CateringCount.this, "Please enter a plate count between 100 and 1000.", Toast.LENGTH_SHORT).show();
                    done.setEnabled(false); // Disable the Done button
                } else {
                    done.setEnabled(true); // Enable the Done button if within range
                    calculateTotal(); // Call calculateTotal when valid input is entered
                }
            } catch (NumberFormatException e) {
                Toast.makeText(CateringCount.this, "Invalid input. Please enter a valid number.", Toast.LENGTH_SHORT).show();
                done.setEnabled(false);
            }
        } else {
            done.setEnabled(false); // Disable if input is empty
            resetAmounts();
        }
    }

    // Method to calculate the total amount, advance payment, and remaining balance
    private void calculateTotal() {
        String plateCountStr = plateCountInput.getText().toString();

        if (!plateCountStr.isEmpty()) {
            try {
                plateCount = Integer.parseInt(plateCountStr);
                totalAmount = plateCount * 3000; // Assuming Rs. 3000 per plate

                Log.d("CateringCount", "Plate Count: " + plateCount);
                Log.d("CateringCount", "Total Amount Calculated: " + totalAmount);

                advanceAmount = totalAmount * 0.10; // 10% advance
                remainingAmount = totalAmount - advanceAmount;

                // Display the calculated amounts
                totalAmountText.setText("Total Amount for Catering: Rs. " + NumberFormat.getNumberInstance(Locale.US).format(totalAmount));
                advanceText.setText("Advance payment: Rs. " + NumberFormat.getNumberInstance(Locale.US).format(advanceAmount));
                remainingText.setText("Remaining payment: Rs. " + NumberFormat.getNumberInstance(Locale.US).format(remainingAmount));
            } catch (NumberFormatException e) {
                Log.e("CateringCount", "Error parsing plate count input", e);
            }
        } else {
            resetAmounts();
        }
    }

    // Reset all amounts to zero if input is empty
    private void resetAmounts() {
        plateCount = 0;
        totalAmount = 0;
        advanceAmount = 0;
        remainingAmount = 0;

        totalAmountText.setText("Total Amount for Catering: Rs. 0");
        advanceText.setText("Advance payment: Rs. 0");
        remainingText.setText("Remaining payment: Rs. 0");
    }
}
