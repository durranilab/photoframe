package com.burhanrashid52.photoeditor;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    Button btn_gallery, btn_camera;
    //public static final int PICK_GALLERY = 100;
    private int PiCK_CAMERA_REQUEST = 100;
    private int PICK_IMAGE_REQUEST = 101;
    Uri selectedphoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_gallery = findViewById(R.id.btn_gallery);

        btn_camera = findViewById(R.id.btn_camera);

        btn_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                galleryClick();
            }
        });

        btn_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cameraClick();
            }
        });
    }

    //Gallery
    private void galleryClick() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    private void cameraClick() {
        Intent cameraIntent = new
                Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, PiCK_CAMERA_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == PiCK_CAMERA_REQUEST) {
                Bitmap photo = (Bitmap) data.getExtras().get("data");

                //Starting activity (ImageViewActivity in my code) to preview image
                Intent intent = new Intent(this, EditImageActivity.class);
                intent.putExtra("BitmapImage", photo);
                startActivity(intent);

            } else if (requestCode == PICK_IMAGE_REQUEST) {
                if (data.getData() != null) {
                    Uri imageUri = data.getData();

                    //Starting activity (ImageViewActivity in my code) to preview image
                    Intent intent = new Intent(this, EditImageActivity.class);
                    intent.putExtra("ImageUri", imageUri.toString());
                    startActivity(intent);
                }
            }
        }
    }
}


