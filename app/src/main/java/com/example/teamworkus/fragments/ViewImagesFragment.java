package com.example.teamworkus.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.teamworkus.Extras.ContactAdapter;
import com.example.teamworkus.Extras.ContactDetails;
import com.example.teamworkus.R;
import com.example.teamworkus.Extras.RequestInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ViewImagesFragment extends Fragment {

    ArrayList<ContactDetails> contactDetails = new ArrayList<>();
    ContactAdapter contactAdapter;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_images, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        getContactResponse();

        return view;
    }

    private void getContactResponse() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface requestInterface = retrofit.create(RequestInterface.class);

        Call<List<ContactDetails>> call = requestInterface.getContacts();

        call.enqueue(new Callback<List<ContactDetails>>() {
            @Override
            public void onResponse(Call<List<ContactDetails>> call, Response<List<ContactDetails>> response) {
                contactDetails = new ArrayList<>(response.body());
                contactAdapter = new ContactAdapter(contactDetails, getContext());
                recyclerView.setAdapter(contactAdapter);
            }

            @Override
            public void onFailure(Call<List<ContactDetails>> call, Throwable t) {
                Toast.makeText(getContext(), "Failure!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}