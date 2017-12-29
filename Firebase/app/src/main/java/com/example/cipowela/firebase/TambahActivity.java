package com.example.cipowela.firebase;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TambahActivity extends AppCompatActivity {

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    EditText judul, deskripsi, cerita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        getSupportActionBar().setTitle("Tambah Cerita");

        judul = findViewById(R.id.tambah_judul);
        deskripsi = findViewById(R.id.tambah_deskripsi);
        cerita = findViewById(R.id.tambah_isi);
    }

    public void tambah(View view) {
        String mjudul, mdeskripsi, mcerita, musername;

        SharedPreferences preferences = getSharedPreferences(LoginActivity.loginsPref, 0);

        mjudul = judul.getText().toString();
        mdeskripsi = deskripsi.getText().toString();
        mcerita = cerita.getText().toString();
        musername = preferences.getString("user", "");

        if (mjudul.isEmpty() && mdeskripsi.isEmpty() && mcerita.isEmpty()) {
            Toast.makeText(this, "Ada field kosong", Toast.LENGTH_SHORT).show();
        } else {
            DatabaseReference ceritaRef = ref.child("cerita");

            ceritaRef.push().setValue(new Cerita(mjudul, mdeskripsi, mcerita, musername));
            Toast.makeText(this, "Cerita di tambahkan", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
