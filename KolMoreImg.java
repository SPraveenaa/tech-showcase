package com.example.wedhall_reservationsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class KolMoreImg extends AppCompatActivity {

    private Button select_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kol_more_img);

        select_button = findViewById(R.id.select_button);

        select_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(KolMoreImg.this, KolWedPacSelect.class);
                startActivity(intent);
            }
        });
    }
}