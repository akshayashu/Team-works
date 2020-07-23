package com.example.teamworkus.Extras;

import com.example.teamworkus.Extras.ContactDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RequestInterface {
    @GET("photos")
    Call<List<ContactDetails>> getContacts();
}
