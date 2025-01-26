package com.example.wedhall_reservationsystem.model;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface BookingService {
    @POST("store_booking.php")
    Call<ResponseBody> storeBooking(@Body Booking booking);
}
