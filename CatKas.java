package com.example.wedhall_reservationsystem;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class CatKas extends AppCompatActivity {

    private RadioGroup menuSetOptions;
    private RadioButton radioSetMenu01;
    private Button doneButton;
    private Button skip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_kas);

        // Find the views by their IDs
        menuSetOptions = findViewById(R.id.menuSetOptions);
        radioSetMenu01 = findViewById(R.id.radio_setMenu01);
        doneButton = findViewById(R.id.DONE);
        skip = findViewById(R.id.skip);

        // Set OnClickListener for the DONE button
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if radio_setMenu01 is selected
                if (radioSetMenu01.isChecked()) {
                    // Start Menuset01 activity
                    Intent intent = new Intent(CatKas.this, MenuSet01.class);
                    startActivity(intent);
                }
            }
        });

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CatKas.this, Decoration.class);
                startActivity(intent);
            }
        });
    }
}
