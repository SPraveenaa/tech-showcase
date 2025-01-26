package com.example.wedhall_reservationsystem;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wedhall_reservationsystem.model.Date;
import com.example.wedhall_reservationsystem.model.RetrofitClient;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DateSelection extends AppCompatActivity {

    private TextView selectDate;
    private String selectedDate;
    private Button saveDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_selection);

        selectDate = findViewById(R.id.selectDate);
        saveDate = findViewById(R.id.savedate);

        saveDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedDate != null) {
                    saveDateToDatabase(selectedDate);
                    Intent intent = new Intent(DateSelection.this, AviWed.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(DateSelection.this, "Please select a date first", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        // Format the date as YYYY-MM-DD
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, monthOfYear, dayOfMonth);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        selectedDate = dateFormat.format(calendar.getTime());

        // Display the selected date
        selectDate.setText("Selected date: " + selectedDate);
    }

    public void showDatePicker(View view) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // Format the date as YYYY-MM-DD
                Calendar selectedCalendar = Calendar.getInstance();
                selectedCalendar.set(year, monthOfYear, dayOfMonth);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                selectedDate = dateFormat.format(selectedCalendar.getTime());

                // Display the selected date
                selectDate.setText("Selected date: " + selectedDate);
            }
        }, year, month, day);

        datePickerDialog.show();
    }


    private void saveDateToDatabase(String date) {
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Date dateObject = new Date(0, date);  // bookId set to 0 if not used

        Call<Void> call = apiService.saveDate(dateObject);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(DateSelection.this, "Date saved successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(DateSelection.this, "Failed to save date", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(DateSelection.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
