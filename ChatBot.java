package com.example.wedhall_reservationsystem;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ChatBot extends AppCompatActivity {

    private TextView answerDisplay;
    private EditText inputQuestionNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_bot);

        // Initialize UI elements
        answerDisplay = findViewById(R.id.answerDisplay);
        inputQuestionNumber = findViewById(R.id.inputQuestionNumber);
        Button okButton = findViewById(R.id.okButton);

        // Set click listener for OK button
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = inputQuestionNumber.getText().toString().trim();
                showAnswer(input);
                inputQuestionNumber.setText(""); // Clear input after clicking OK
            }
        });
    }

    // Function to display the answer based on question number
    private void showAnswer(String questionNumber) {
        String answer;

        switch (questionNumber) {
            case "1":
                answer = "011 255 7788";
                break;
            case "2":
                answer = "077 122 7788";
                break;
            case "3":
                answer = "Please contact 011 255 7788 / 077 122 7788 to more details.";
                break;
            case "4":
                answer = "Yeah! You should click the cancel button once you login into booking history page. Within 7 days you will get back your payment with a small tax reduction.";
                break;
            case "5":
                answer = "Sorry, You can't create.";
                break;
            default:
                answer = "Invalid question number. Please enter a number between 1 and 5.";
                break;
        }

        answerDisplay.setText(answer);
    }
}
