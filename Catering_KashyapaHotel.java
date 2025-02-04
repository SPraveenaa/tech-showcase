package com.example.wedhall_reservationsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Catering_KashyapaHotel extends AppCompatActivity {

    private Button search;
    private Button skip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catering_kashyapa_hotel);

        search = findViewById(R.id.search);
        skip = findViewById(R.id.skip);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Catering_KashyapaHotel.this, CatKas.class);
                startActivity(intent);
            }
        });

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Catering_KashyapaHotel.this, Decoration.class);
                startActivity(intent);
            }
        });
    }
}