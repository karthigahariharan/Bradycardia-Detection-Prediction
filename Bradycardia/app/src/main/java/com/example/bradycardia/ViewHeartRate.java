package com.example.bradycardia;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;



public class ViewHeartRate extends AppCompatActivity {
    Boolean nameFlag=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Button karthiga_btn = (Button) findViewById(R.id.Karthiga);
        Button mandar_btn = (Button) findViewById(R.id.Mandar);
        Button adhithiya_btn = (Button) findViewById(R.id.Adhithiya);
        Button aikya_btn = (Button) findViewById(R.id.Aikya);
        Button redirect=(Button) findViewById(R.id.redirect);


        validatePermissions();


        final ImageView imageView = findViewById(R.id.heartRate_Image);


        karthiga_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setImageResource(R.drawable.karti);
                imageView.setVisibility(View.VISIBLE);
            }
        });
        mandar_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setImageResource(R.drawable.mand);
                imageView.setVisibility(View.VISIBLE);
            }
        });
        adhithiya_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setImageResource(R.drawable.adithi);
                imageView.setVisibility(View.VISIBLE);
            }
        });
        aikya_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setImageResource(R.drawable.aik);
                imageView.setVisibility(View.VISIBLE);
            }
        });
        redirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewHeartRate.this, BCardiaDetectionActivity.class);
                ViewHeartRate.this.startActivity(intent);

            }
        });

    }

    private void validatePermissions() {

        if (ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_EXTERNAL_STORAGE)) {

                Toast.makeText(this, "External storage Read permission denied", Toast.LENGTH_SHORT).show();

            } else {

                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

            }
        }

        if (ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                Toast.makeText(this, "External Storage Write Permission Denied", Toast.LENGTH_SHORT).show();

            } else {

                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},2);

            }
        }
    }


}

