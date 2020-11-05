package com.example.phonecall;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText phn;
    Button call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        phn = findViewById(R.id.phn);
        call = findViewById(R.id.call);

        call.setOnClickListener(v -> {
            String num = phn.getText().toString();
            if(num.isEmpty()){
                Toast.makeText(MainActivity.this, "Enter your number first", Toast.LENGTH_SHORT).show();
            }else{
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if(checkSelfPermission(Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED){
                        String s = "tel:" + num;
                        Intent intent = new Intent (Intent.ACTION_CALL);
                        intent.setData(Uri.parse(s));
                        startActivity(intent);
                    }else {
                        requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 1);
                    }
                }
            }
        });
    }
}