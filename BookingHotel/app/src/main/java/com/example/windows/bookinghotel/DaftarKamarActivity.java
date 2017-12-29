package com.example.windows.bookinghotel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class DaftarKamarActivity extends AppCompatActivity {
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_kamar);
        intent = new Intent(this, DetailKamarActivity.class);
    }

    public void kamar_superior(View view) {
        intent.putExtra("gambar.kamar",R.drawable.superior);
        startActivity(intent);
    }

    public void kamar_executive(View view) {
        intent.putExtra("gambar.kamar",R.drawable.excecutive);
        startActivity(intent);
    }

    public void kamar_suite(View view) {
        intent.putExtra("gambar.kamar",R.drawable.suite);
        startActivity(intent);
    }
}
