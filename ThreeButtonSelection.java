package com.example.wedhall_reservationsystem;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wedhall_reservationsystem.model.Booking;
import com.example.wedhall_reservationsystem.model.BookingService;
import com.example.wedhall_reservationsystem.model.RetrofitClient;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ThreeButtonSelection extends AppCompatActivity {

    private Button feedback, chatbot, FinalCal;
    private int totalPriceDeco;
    private int advancePaymentDeco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_button_selection);

        // Retrieve values passed from SelectedDecorationDetails
        Intent intent = getIntent();
        totalPriceDeco = intent.getIntExtra("totalPriceDeco", 0);
        advancePaymentDeco = intent.getIntExtra("advancePaymentDeco", 0);
        String event = intent.getStringExtra("event");
        String hall = intent.getStringExtra("hall");
        int priceHallPac = intent.getIntExtra("priceHallPac", 0);
        int advanceHallPac = intent.getIntExtra("advanceHallPac", 0);
        int remainingHallPac = intent.getIntExtra("remainingHallPac", 0);
        int plateCount = intent.getIntExtra("plateCount", 0);
        double totalAmount = intent.getDoubleExtra("totalAmount", 0.0);
        double advanceAmount = intent.getDoubleExtra("advanceAmount", 0.0);
        double remainingAmount = intent.getDoubleExtra("remainingAmount", 0.0);
        String decorationTheme = intent.getStringExtra("decorationTheme");
        String menu = intent.getStringExtra("menu");

        feedback = findViewById(R.id.feedback);
        chatbot = findViewById(R.id.chatbot);
        FinalCal = findViewById(R.id.finalCal);

        // Set up click listeners for buttons
        FinalCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create Booking object
                Booking booking = new Booking(
                        event, hall, String.valueOf(priceHallPac), String.valueOf(advanceHallPac),
                        String.valueOf(remainingHallPac), menu, String.valueOf(plateCount),
                        String.valueOf(totalAmount), String.valueOf(advanceAmount),
                        String.valueOf(remainingAmount), decorationTheme,
                        String.valueOf(totalPriceDeco), String.valueOf(advancePaymentDeco)
                );

                // Store data in database using Retrofit
                storeBookingData(booking);

                // Navigate to FinalCal activity after storing data
                Intent intent1 = new Intent(ThreeButtonSelection.this, FinalCal.class);
                startActivity(intent1);
            }
        });

        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(ThreeButtonSelection.this, FeedbackPage.class);
                startActivity(intent1);
            }
        });

        chatbot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(ThreeButtonSelection.this, ChatBot.class);
                startActivity(intent1);
            }
        });

    }

    private void storeBookingData(Booking booking) {
        Retrofit retrofit = RetrofitClient.getRetrofitInstance();
        BookingService service = retrofit.create(BookingService.class);

        Call<ResponseBody> call = service.storeBooking(booking);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        String responseString = response.body().string();
                        Log.d("Booking", "Booking data saved successfully: " + responseString);
                    } catch (Exception e) {
                        Log.e("Booking", "Error reading response", e);
                    }
                } else {
                    Log.e("Booking", "Failed to save data: " + response.code() + " " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Booking", "Error: " + t.getMessage());
            }
        });
    }

}
