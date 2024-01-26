package com.example.main_page_scuer;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    private static final int PICK_PHOTO_REQUEST = 1;
    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSelectPhotos = findViewById(R.id.btnSelectPhotos);
        Button btnSelectVideos = findViewById(R.id.btnSelectVideos);
        Button btnEncrypt = findViewById(R.id.btnEncrypt);

        // Check if the READ_EXTERNAL_STORAGE permission is not granted
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Request the permission
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
        }

        btnSelectPhotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle photo selection
                openGalleryForPhotos();
            }
        });

        btnSelectVideos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle video selection
                // You can implement a similar method for video selection
            }
        });

        btnEncrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle encryption logic
                // You may want to start a new activity for the encryption process
            }
        });
    }

    private void openGalleryForPhotos() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, PICK_PHOTO_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_PHOTO_REQUEST && resultCode == RESULT_OK) {
            // Handle the selected photo URI
            // The URI is in data.getData()
            Uri selectedPhotoUri = data.getData();

            // Display the selected photo in the ImageView
            ImageView selectedPhotoImageView = findViewById(R.id.selectedPhotoImageView);
            selectedPhotoImageView.setVisibility(View.VISIBLE);
            selectedPhotoImageView.setImageURI(selectedPhotoUri);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE) {
            // Check if the permission was granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, you can now proceed with accessing the gallery
            } else {
                // Permission denied, handle accordingly
            }
        }
    }
}
