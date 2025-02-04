package com.example.wedhall_reservationsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wedhall_reservationsystem.model.Booking;
import com.example.wedhall_reservationsystem.model.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FinalCal extends AppCompatActivity {

    private TextView totalPriceDeco, advancePaymentDeco, event, hall, priceHallPac, advanceHallPac,
            remainingHallPac, plateCount, totalAmount, advanceAmount, remainingAmount,
            decorationTheme, menu;

    private Button processbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_cal);

        processbtn = findViewById(R.id.processbtn);

        processbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FinalCal.this, Pay.class);
                startActivity(intent);
            }
        });

        // Initialize the TextViews
        totalPriceDeco = findViewById(R.id.totalPriceDeco);
        advancePaymentDeco = findViewById(R.id.advancePaymentDeco);
        event = findViewById(R.id.event);
        hall = findViewById(R.id.hall);
        priceHallPac = findViewById(R.id.priceHallPac);
        advanceHallPac = findViewById(R.id.advanceHallPac);
        remainingHallPac = findViewById(R.id.remainingHallPac);
        plateCount = findViewById(R.id.plateCount);
        totalAmount = findViewById(R.id.totalAmount);
        advanceAmount = findViewById(R.id.advanceAmount);
        remainingAmount = findViewById(R.id.remainingAmount);
        decorationTheme = findViewById(R.id.decorationTheme);
        menu = findViewById(R.id.menu);

        // Create an instance of Retrofit
        ApiService apiInterface = RetrofitClient.getRetrofitInstance().create(ApiService.class);

        // Call the API to get the last booking
        Call<Booking> call = apiInterface.getLastBooking();
        call.enqueue(new Callback<Booking>() {
            @Override
            public void onResponse(Call<Booking> call, Response<Booking> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Booking booking = response.body();

                    // Display the data
                    totalPriceDeco.setText("Total Price Deco: " + booking.getTotalPriceDeco());
                    advancePaymentDeco.setText("Advance Payment Deco: " + booking.getAdvancePaymentDeco());
                    event.setText("Event: " + booking.getEvent());
                    hall.setText("Hall: " + booking.getHall());
                    priceHallPac.setText("Price Hall Package: " + booking.getPriceHallPac());
                    advanceHallPac.setText("Advance Hall Package: " + booking.getAdvanceHallPac());
                    remainingHallPac.setText("Remaining Hall Package: " + booking.getRemainingHallPac());
                    plateCount.setText("Plate Count: " + booking.getPlateCount());
                    totalAmount.setText("Total Amount: " + booking.getTotalAmount());
                    advanceAmount.setText("Advance Amount: " + booking.getAdvanceAmount());
                    remainingAmount.setText("Remaining Amount: " + booking.getRemainingAmount());
                    decorationTheme.setText("Decoration Theme: " + booking.getDecorationTheme());
                    menu.setText("Menu: " + booking.getMenu());
                }
            }

            @Override
            public void onFailure(Call<Booking> call, Throwable t) {
                // Handle failure (e.g., show a toast or a message to the user)
            }
        });
    }
}
