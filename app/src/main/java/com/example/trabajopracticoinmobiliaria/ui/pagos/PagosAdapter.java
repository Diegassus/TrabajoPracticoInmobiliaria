package com.example.trabajopracticoinmobiliaria.ui.pagos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabajopracticoinmobiliaria.Models.Pago;
import com.example.trabajopracticoinmobiliaria.R;

import java.util.List;

public class PagosAdapter extends RecyclerView.Adapter<PagosAdapter.ViewHolder> {
    private Context context;
    private List<Pago> pagos;
    private LayoutInflater li;

    public PagosAdapter(Context context, List<Pago> pagos, LayoutInflater li) {
        this.context = context;
        this.pagos = pagos;
        this.li = li;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = li.inflate(R.layout.item_pago, parent, false);
        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.codigo.setText(pagos.get(position).getIdPago()+"");
        holder.contrato.setText(pagos.get(position).getContrato().getIdContrato()+"");
        holder.fechaPago.setText(pagos.get(position).getFechaDePago());
        holder.importe.setText(pagos.get(position).getImporte()+"");
        holder.numero.setText(pagos.get(position).getNumero()+"");
    }

    @Override
    public int getItemCount() {
        return pagos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView importe;
        TextView codigo;
        TextView numero;
        TextView contrato;
        TextView fechaPago;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            importe = itemView.findViewById(R.id.etImporte);
            codigo = itemView.findViewById(R.id.etCodigo);
            numero = itemView.findViewById(R.id.etNumeroPago);
            contrato = itemView.findViewById(R.id.etCodigoContrato);
            fechaPago = itemView.findViewById(R.id.etFechaPago);
        }
    }
}
