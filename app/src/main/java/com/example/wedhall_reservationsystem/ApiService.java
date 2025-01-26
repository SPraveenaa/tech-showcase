package com.example.wedhall_reservationsystem;

import com.example.wedhall_reservationsystem.model.Booking;
import com.example.wedhall_reservationsystem.model.Date;
import com.example.wedhall_reservationsystem.model.LoginRequest;
import com.example.wedhall_reservationsystem.model.User;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    // This method registers a new user by calling the "register_user.php" file
    @POST("register_user.php")
    Call<Void> registerUser(@Body User user);

    // This method is for logging in the user by calling the "login_user.php" file
    @POST("login_user.php")
    Call<ResponseBody> loginUser(@Body LoginRequest loginRequest);

    @GET("get_booking.php")
    Call<Booking> getLastBooking();

    @POST("save_date.php")
    Call<Void> saveDate(@Body Date date);

}
