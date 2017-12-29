package com.example.cipowela.firebase;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by cipowela on 28/12/17.
 */

public class CeritaAdapter extends RecyclerView.Adapter<CeritaAdapter.Holder> {

    private Context c;
    private List<Cerita> ceritaList;

    public CeritaAdapter(Context c, List<Cerita> ceritaList) {
        this.c = c;
        this.ceritaList = ceritaList;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_layout, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        final Cerita now = ceritaList.get(position);
        holder.judul.setText(now.judul);
        holder.deskripsi.setText(now.deskripsi);
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(c, DetailCeritaActivity.class);
                intent.putExtra("judul", now.judul);
                intent.putExtra("deskripsi", now.deskripsi);
                intent.putExtra("isi", now.isi);
                intent.putExtra("username", now.username);

                c.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ceritaList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        TextView judul, deskripsi;
        LinearLayout layout;
        public Holder(View itemView) {
            super(itemView);
            judul = itemView.findViewById(R.id.judul);
            deskripsi = itemView.findViewById(R.id.deskripsi);
            layout = itemView.findViewById(R.id.layout_item);
        }
    }
}
