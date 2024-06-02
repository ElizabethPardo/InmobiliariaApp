package com.example.prueba.ui.contratos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prueba.R;
import com.example.prueba.databinding.FragmentPagoBinding;
import com.example.prueba.modelo.Pago;
import com.example.prueba.ui.contratos.PagoViewModel;


import com.google.android.material.textfield.TextInputEditText;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class PagoFragment extends Fragment {
    private RecyclerView rvPago;
    private PagoAdapter pagoAdapter;
    private PagoViewModel pagoViewModel;
    private FragmentPagoBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        pagoViewModel = new ViewModelProvider(this).get(PagoViewModel.class);
        binding = FragmentPagoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        rvPago = binding.rvPago;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1, LinearLayoutManager.VERTICAL, false);
        rvPago.setLayoutManager(gridLayoutManager);
        pagoViewModel.getPagosM().observe(getViewLifecycleOwner(), new Observer<List<Pago>>() {
            @Override
            public void onChanged(List<Pago> pagos) {
                pagoAdapter = new PagoAdapter(pagos, root.getContext(), getLayoutInflater());
                rvPago.setAdapter(pagoAdapter);
            }
        });
        pagoViewModel.cargarPagos(getArguments());
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
