package com.example.wedhall_reservationsystem;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class FeedbackPage extends AppCompatActivity {

    private LinearLayout dynamicLayout;
    private int editTextCount = 1; // Start from 1 as there's already one EditText
    private static final int MAX_FIELDS = 5; // Limit to 5 EditText fields
    private EditText focusedEditText; // Holds the currently focused EditText
    private List<EditText> editTextList = new ArrayList<>(); // List to keep track of all EditTexts
    private List<String> feedbacks = new ArrayList<>(); // List to store feedback strings

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_page);

        dynamicLayout = findViewById(R.id.dynamicLayout);
        Button addButton = findViewById(R.id.add);
        Button clearButton = findViewById(R.id.clear);
        Button okButton = findViewById(R.id.ok); // Assuming there's an OK button in the layout

        // Initialize the first EditText and add to the list
        EditText initialEditText = findViewById(R.id.usernameInput);
        editTextList.add(initialEditText);

        // Add listener for Add button
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextCount < MAX_FIELDS) {
                    addNewEditText();
                    editTextCount++;
                } else {
                    Toast.makeText(FeedbackPage.this, "You can only add up to 5 feedback fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Add listener for Clear button
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (focusedEditText != null) {
                    focusedEditText.setText(""); // Clear only the EditText with focus
                } else {
                    Toast.makeText(FeedbackPage.this, "No field is selected for clearing.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Add listener for OK button to save feedbacks
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveFeedbacks();
                Toast.makeText(FeedbackPage.this, "Feedbacks saved", Toast.LENGTH_SHORT).show();
            }
        });

        // Set focus listener on the initial EditText field
        initialEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    focusedEditText = (EditText) v;
                }
            }
        });
    }

    // Method to dynamically add a new EditText
    private void addNewEditText() {
        EditText newEditText = new EditText(this);
        newEditText.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        newEditText.setHint("Enter additional feedback");
        newEditText.setPadding(20, 20, 20, 20);
        newEditText.setBackground(getResources().getDrawable(android.R.drawable.edit_text));

        // Add the new EditText to the list
        editTextList.add(newEditText);

        // Set focus listener on dynamically added EditText fields
        newEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    focusedEditText = (EditText) v;
                }
            }
        });

        dynamicLayout.addView(newEditText);
    }

    // Method to save feedbacks from all EditText fields to the feedbacks list
    private void saveFeedbacks() {
        feedbacks.clear(); // Clear previous entries
        for (EditText editText : editTextList) {
            String feedback = editText.getText().toString().trim();
            if (!feedback.isEmpty()) {
                feedbacks.add(feedback); // Add non-empty feedback to the list
            }
        }
    }
}
