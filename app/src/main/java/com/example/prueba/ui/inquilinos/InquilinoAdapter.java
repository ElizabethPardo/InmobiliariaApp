package com.example.prueba.ui.inquilinos;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.prueba.R;
import com.example.prueba.modelo.Contrato;
import com.example.prueba.modelo.Inmueble;
import com.example.prueba.modelo.Inquilino;
import com.example.prueba.request.ApiClient;

import java.util.List;

public class InquilinoAdapter extends RecyclerView.Adapter<InquilinoAdapter.ViewHolder>{

    private List<Inmueble> lista ;
    private Context context;
    private LayoutInflater layoutInflater;

    public InquilinoAdapter(List<Inmueble> lista, Context context, LayoutInflater layoutInflater) {
        this.lista = lista;
        this.context = context;
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public InquilinoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_inquilino, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InquilinoAdapter.ViewHolder holder, int position) {
        Inmueble inmueble = lista.get(position);

        holder.tvDireccionInquilino.setText(lista.get(position).getDireccion());
        Glide.with(context)
                .load("http://192.168.100.09:45475/" + lista.get(position).getImagen())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.ivInmuebleInquilino);

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvDireccionInquilino, tvPrecioInquilino;
        private ImageView ivInmuebleInquilino;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDireccionInquilino = itemView.findViewById(R.id.tvDireccionInquilino);
            ivInmuebleInquilino = itemView.findViewById(R.id.ivInmuebleInquilino);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("inmueble", lista.get(getAdapterPosition()));
                    Navigation.findNavController((Activity)context,R.id.nav_host_fragment_content_main).navigate(R.id.inquilinoDetalleFragment,bundle);
                }
            });

        }
    }
}
