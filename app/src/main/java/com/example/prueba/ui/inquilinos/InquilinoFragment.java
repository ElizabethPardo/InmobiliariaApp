package com.example.prueba.ui.inquilinos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prueba.R;
import com.example.prueba.databinding.FragmentInquilinoBinding;
import com.example.prueba.modelo.Contrato;
import com.example.prueba.modelo.Inmueble;
import com.example.prueba.modelo.Inquilino;
import com.google.android.material.textfield.TextInputEditText;

import org.jetbrains.annotations.Nullable;

import java.util.List;

public class InquilinoFragment extends Fragment {

    private RecyclerView rvInquilino;
    private InquilinoAdapter inquilinoAdapter;
    private InquilinoViewModel inquilinoViewModel;
    private FragmentInquilinoBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inquilinoViewModel = new ViewModelProvider(this).get(InquilinoViewModel.class);
        binding = FragmentInquilinoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        rvInquilino = binding.rvInquilino;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, LinearLayoutManager.VERTICAL, false);
        rvInquilino.setLayoutManager(gridLayoutManager);
        inquilinoViewModel.getInmuebles().observe(getViewLifecycleOwner(), new Observer<List<Inmueble>>() {
            @Override
            public void onChanged(List<Inmueble> inmuebles) {
                inquilinoAdapter = new InquilinoAdapter(inmuebles, root.getContext(), getLayoutInflater());
                rvInquilino.setAdapter(inquilinoAdapter);
            }
        });
        inquilinoViewModel.cargarInmueble();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
