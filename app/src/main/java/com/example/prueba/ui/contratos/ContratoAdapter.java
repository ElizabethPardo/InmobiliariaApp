package com.example.prueba.ui.contratos;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.prueba.R;
import com.example.prueba.modelo.Contrato;
import com.example.prueba.modelo.Inmueble;
import com.example.prueba.request.ApiClient;
import com.google.android.material.textfield.TextInputEditText;

import org.jetbrains.annotations.Nullable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContratoAdapter extends RecyclerView.Adapter<ContratoAdapter.ViewHolder> {
private List<Contrato> lista;
private Context context;
private Contrato con;
private MutableLiveData<Contrato> contratoM;
private LayoutInflater layoutInflater;
private ContratoDetalleViewModel vm;

    public ContratoAdapter(List<Contrato> lista, Context context, LayoutInflater layoutInflater) {
        this.lista = lista;
        this.context = context;
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public ContratoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_contrato, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContratoAdapter.ViewHolder holder, int position) {
        ApiClient api = ApiClient.getApi();
        Contrato inmueble = lista.get(position);

        holder.tvDireccionC.setText(lista.get(position).getInmueble().getDireccion());
        Glide.with(context)
                .load("http://192.168.100.9:45475/" + lista.get(position).getInmueble().getImagen())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.ivInmuebleC);

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvDireccionC;
        private ImageView ivInmuebleC;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDireccionC = itemView.findViewById(R.id.tvDireccionContrato);
            ivInmuebleC = itemView.findViewById(R.id.ivInmuebleContrato);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("contrato",lista.get(getAdapterPosition()));
                    Navigation.findNavController((Activity)context,R.id.nav_host_fragment_content_main).navigate(R.id.contratoDetalleFragment,bundle);
                }
            });
        }
    }
}


