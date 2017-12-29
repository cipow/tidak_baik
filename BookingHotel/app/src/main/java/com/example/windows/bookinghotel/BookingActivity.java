package com.example.windows.bookinghotel;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class BookingActivity extends AppCompatActivity implements View.OnClickListener {

    public static String prefsBooking = "file.prefs.booking";

    EditText nama_booking;
    Spinner jenis_kamar;
    Button tgl_in, tgl_out, wkt_in, wkt_out;

    ArrayAdapter<CharSequence> list_jenis_kamar;

    private int mYear_in, mMonth_in, mDay_in, mHour_in, mMinute_in;
    private int mYear_out, mMonth_out, mDay_out, mHour_out, mMinute_out;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        sharedPreferences = getSharedPreferences(prefsBooking, 0);

        nama_booking = (EditText) findViewById(R.id.form_nama);
        jenis_kamar = (Spinner) findViewById(R.id.jenis_kamar);
        tgl_in = (Button) findViewById(R.id.tanggal_in);
        tgl_out = (Button) findViewById(R.id.tanggal_out);
        wkt_in = (Button) findViewById(R.id.waktu_in);
        wkt_out = (Button) findViewById(R.id.waktu_out);

        list_jenis_kamar = ArrayAdapter.createFromResource(this, R.array.jenis_kamar,
                android.R.layout.simple_spinner_item);
        list_jenis_kamar.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jenis_kamar.setAdapter(list_jenis_kamar);

        tgl_in.setOnClickListener(this);
        wkt_in.setOnClickListener(this);
        tgl_out.setOnClickListener(this);
        wkt_out.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == tgl_in) {
            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear_in = c.get(Calendar.YEAR);
            mMonth_in = c.get(Calendar.MONTH);
            mDay_in = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            tgl_in.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear_in, mMonth_in, mDay_in);
            datePickerDialog.show();
        }
        if (v == wkt_in) {
            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour_in = c.get(Calendar.HOUR_OF_DAY);
            mMinute_in = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            wkt_in.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour_in, mMinute_in, false);
            timePickerDialog.show();
        }

        if (v == tgl_out) {
            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear_out = c.get(Calendar.YEAR);
            mMonth_out = c.get(Calendar.MONTH);
            mDay_out = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            tgl_out.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear_out, mMonth_out, mDay_out);
            datePickerDialog.show();
        }
        if (v == wkt_out) {
            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour_out = c.get(Calendar.HOUR_OF_DAY);
            mMinute_out = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            wkt_out.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour_out, mMinute_out, false);
            timePickerDialog.show();
        }
    }

    public void booking_now(View view) {
        if (nama_booking.equals("") || tgl_in.getText().equals("tanggal") || wkt_in.getText().equals("waktu")
                || tgl_out.getText().equals("tanggal") || wkt_out.getText().equals("waktu")) {
            Toast.makeText(this, "Semua Form Harus Terisi", Toast.LENGTH_LONG).show();
        } else {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("nama", nama_booking.getText().toString());
                jsonObject.put("jenis", jenis_kamar.getSelectedItem().toString());
                jsonObject.put("waktu_in", tgl_in.getText() + " " + wkt_in.getText());
                jsonObject.put("waktu_out", tgl_out.getText() + " " + wkt_out.getText());
            } catch (JSONException e) {
                e.printStackTrace();
            }

            if (sharedPreferences.contains("booking")){
                String dataBooking = sharedPreferences.getString("booking","");
                try {
                    JSONArray jsonArray = new JSONArray(dataBooking);
                    jsonArray.put(jsonObject);
                    sharedPreferences.edit().putString("booking",jsonArray.toString());
                    sharedPreferences.edit().commit();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                JSONArray jsonArray = new JSONArray();
                jsonArray.put(jsonObject);
                sharedPreferences.edit().putString("booking",jsonArray.toString());
                sharedPreferences.edit().commit();
            }

            finish();
            Log.d("bookings", sharedPreferences.getString("booking",""));
        }
    }
}
