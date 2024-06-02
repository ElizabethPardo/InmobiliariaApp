package com.example.prueba.ui.inquilinos;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.example.prueba.databinding.FragmentInquilinoDetalleBinding;
import com.example.prueba.modelo.Contrato;
import com.example.prueba.modelo.Inquilino;

import androidx.lifecycle.ViewModelProvider;
import org.jetbrains.annotations.Nullable;

public class InquilinoDetalleFragment extends Fragment {
    private InquilinoDetalleViewModel inquilinoDetalleViewModel;
    private FragmentInquilinoDetalleBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inquilinoDetalleViewModel = new ViewModelProvider(this).get(InquilinoDetalleViewModel.class);
        binding = FragmentInquilinoDetalleBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final TextView tvCodigoInquilino = binding.tvCodigoInquilino;
        final TextView tvNombreInquilino = binding.tvNombreInquilino;
        final TextView tvApellidoInquilino = binding.tvApellidoInquilino;
        final TextView tvDniInquilino = binding.tvDniInquilino;
        final TextView tvMailInquilino = binding.tvMailInquilino;
        final TextView tvTelInquilino = binding.tvTelInquilino;
        final TextView tvGaranteInquilino = binding.tvGaranteInquilino;
        final TextView tvTelGarante = binding.tvTelGarante;

        inquilinoDetalleViewModel.getInquilinoM().observe(getViewLifecycleOwner(), new Observer<Inquilino>() {
            @Override
            public void onChanged(Inquilino inquilino) {
                tvCodigoInquilino.setText(inquilino.getId() + "");
                tvNombreInquilino.setText(inquilino.getNombre());
                tvApellidoInquilino.setText(inquilino.getApellido());
                tvDniInquilino.setText(inquilino.getDni()+"");
                tvMailInquilino.setText(inquilino.getEmail());
                tvTelInquilino.setText(inquilino.getTelefono());
                tvGaranteInquilino.setText(inquilino.getNombreGarante());
                tvTelGarante.setText(inquilino.getTelefonoGarante());
            }
        });
        inquilinoDetalleViewModel.cargar(getArguments());
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
