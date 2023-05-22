package com.example.trabajopracticoinmobiliaria.ui.contratos;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.trabajopracticoinmobiliaria.Models.Inmueble;
import com.example.trabajopracticoinmobiliaria.R;
import com.example.trabajopracticoinmobiliaria.request.ApiClient;

import java.util.List;

public class ContratosAdapter extends RecyclerView.Adapter<ContratosAdapter.ViewHolder> {
    private Context context;
    private List<Inmueble> inmuebles;
    private LayoutInflater li;

    public ContratosAdapter(Context context, List<Inmueble> inquilinos, LayoutInflater li) {
        this.context = context;
        this.inmuebles = inquilinos;
        this.li = li;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = li.inflate(R.layout.item_inquilino, parent, false);
        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context)
                .load(inmuebles.get(position).getImagen())
                .into(holder.imagen);
        holder.direccion.setText(inmuebles.get(position).getDireccion());
    }

    @Override
    public int getItemCount() {
        return inmuebles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imagen;
        TextView direccion;
        Button boton;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imagen = itemView.findViewById(R.id.ivFoto);
            boton = itemView.findViewById(R.id.btnAvanzar);
            direccion = itemView.findViewById(R.id.tvCalle);
            boton.setText("Ver");
            boton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("inmueble", inmuebles.get(getAdapterPosition()));
                    Navigation.findNavController(v).navigate(R.id.contratoFragment, bundle);
                }
            });
        }
    }
}
