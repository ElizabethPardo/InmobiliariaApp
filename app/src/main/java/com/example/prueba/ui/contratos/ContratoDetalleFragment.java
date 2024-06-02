package com.example.prueba.ui.contratos;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.prueba.R;
import com.example.prueba.databinding.FragmentContratoDetalleBinding;
import com.example.prueba.modelo.Contrato;

import org.jetbrains.annotations.Nullable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ContratoDetalleFragment extends Fragment {

    private ContratoDetalleViewModel contratoDetalleViewModel;
    private FragmentContratoDetalleBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        contratoDetalleViewModel = new ViewModelProvider(this).get(ContratoDetalleViewModel.class);
        binding = FragmentContratoDetalleBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final TextView tvCodigoContrato = binding.tvCodigoContrato;
        final TextView tvInicioContrato = binding.tvInicioContrato;
        final TextView tvFinCOntrato = binding.tvFinContrato;
        final TextView tvMontoContrato = binding.tvMontoContrato;
        final TextView tvInquilinoContrato = binding.tvInquilinoContrato;
        final TextView tvInmuebleContrato = binding.tvInmuebleContrato;
        final Button btPagos = binding.btPagos;
        contratoDetalleViewModel.getContratoM().observe(getViewLifecycleOwner(), new Observer<Contrato>() {
            @Override
            public void onChanged(Contrato contrato) {

                tvCodigoContrato.setText(contrato.getId() + "");

                LocalDateTime fi = LocalDateTime.parse(contrato.getFechaDesde());
                LocalDate fff = fi.toLocalDate();
                tvInicioContrato.setText(fff.toString());

                LocalDateTime fc = LocalDateTime.parse(contrato.getFechaHasta());
                LocalDate hhh = fc.toLocalDate();
                tvFinCOntrato.setText(hhh.toString());

                tvMontoContrato.setText("$ " + contrato.getInmueble().getPrecio());
                tvInquilinoContrato.setText(contrato.getInquilino().getNombreGarante()+" "+ contrato.getInquilino().getApellido());
                tvInmuebleContrato.setText(contrato.getInmueble().getDireccion());
                btPagos.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("contrato", contrato);
                        Navigation.findNavController(getActivity(),R.id.nav_host_fragment_content_main).navigate(R.id.pagoFragment,bundle);
                    }
                });
            }
        });
        contratoDetalleViewModel.cargarCon(getArguments());
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
