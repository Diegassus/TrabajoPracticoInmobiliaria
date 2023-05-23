package com.example.trabajopracticoinmobiliaria.ui.inmuebles;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.trabajopracticoinmobiliaria.Models.Inmueble;
import com.example.trabajopracticoinmobiliaria.R;
import com.example.trabajopracticoinmobiliaria.ui.inmuebles.detalle.DetalleFragment;

import java.util.ArrayList;
import java.util.List;

public class InmuebleAdapter extends RecyclerView.Adapter<InmuebleAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Inmueble> inmuebles;
    private LayoutInflater li;

    public InmuebleAdapter(Context context, ArrayList<Inmueble> inmuebles, LayoutInflater li) {
        this.context = context;
        this.inmuebles = inmuebles;
        this.li = li;
    }

    @NonNull
    @Override
    public InmuebleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = li.inflate(R.layout.item_inmueble, parent, false);
        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull InmuebleAdapter.ViewHolder holder, int position) {
        holder.calle.setText(inmuebles.get(position).getDireccion());
        holder.precio.setText("$"+inmuebles.get(position).getPrecio());
        Glide.with(context)
                .load(inmuebles.get(position).getFoto())
                .into(holder.imagen);
    }

    @Override
    public int getItemCount() {
        return inmuebles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imagen;
        TextView calle;
        TextView precio;
        CardView card;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imagen = itemView.findViewById(R.id.ivFoto);
            calle = itemView.findViewById(R.id.tvCalle);
            precio = itemView.findViewById(R.id.tvPrecio);

            card = itemView.findViewById(R.id.cartaInmueble);
            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("inmueble",inmuebles.get(getAdapterPosition()).getId());
                    Navigation.findNavController(v).navigate(R.id.detalleFragment,bundle);
                 }
            });
        }
    }
}
