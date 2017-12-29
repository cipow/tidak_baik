package com.example.windows.bookinghotel;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class About extends Fragment {

    TextView call, alamat;
    Button maps;

    public About() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        call = (TextView) view.findViewById(R.id.call_hotel);
        alamat = (TextView) view.findViewById(R.id.alamat_hotel);
        maps = (Button) view.findViewById(R.id.maps_hotel);

        String alamat_string = "<b>Alamat: </b>" +
                "Jl. KH. Wahid Hasyim No. 125-127, Kranggan, Bangunharjo, Semarang, Jawa Tengah, 50138";
        alamat.setText(Html.fromHtml(alamat_string));

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = "tel:" + "0243576000";
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse(uri));
                startActivity(intent);
            }
        });

        maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = "geo:" + "-6.974981,110.421502?q=hotel+semesta";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(uri));
                intent.setPackage("com.google.android.apps.maps");
                if (intent.resolveActivity(getContext().getPackageManager())!= null){
                    startActivity(intent);
                }
            }
        });

        return view;
    }

}
