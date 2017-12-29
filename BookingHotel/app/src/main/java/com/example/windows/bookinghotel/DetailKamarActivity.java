package com.example.windows.bookinghotel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class DetailKamarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kamar);
        Intent intent = getIntent();
        ImageView imageView = (ImageView) findViewById(R.id.gambar_kamar);

        imageView.setImageResource(intent.getIntExtra("gambar.kamar",0));
    }

    public void booking(View view) {
        startActivity(new Intent(this, DaftarKamarActivity.class));
        finish();
    }
}
