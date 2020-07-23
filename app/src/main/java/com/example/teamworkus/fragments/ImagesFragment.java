package com.example.teamworkus.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.teamworkus.R;

import static android.app.Activity.RESULT_OK;

public class ImagesFragment extends Fragment {

    Button gallaryBtn;
    ImageView imageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_images, container, false);

        gallaryBtn = view.findViewById(R.id.gallaryBtn);
        imageView = view.findViewById(R.id.image);

        gallaryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upload();
            }
        });

        return view;
    }

    private void upload() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent,1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == RESULT_OK){
            Uri imageUri = data.getData();
            imageView.setImageURI(imageUri);

            gallaryBtn.setVisibility(View.GONE);
            imageView.setVisibility(View.VISIBLE);
        }
    }
}