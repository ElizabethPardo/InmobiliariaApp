package com.example.prueba.ui.inmuebles;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prueba.R;
import com.example.prueba.databinding.FragmentInmuebleBinding;
import com.example.prueba.modelo.Inmueble;
import com.example.prueba.modelo.Tipo;

import java.util.ArrayList;
import java.util.List;

public class InmuebleFragment extends Fragment {

    private RecyclerView rvInmueble;
    private InmuebleAdapter inmuebleAdapter;
    private InmuebleViewModel inmuebleViewModel;
    private FragmentInmuebleBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,@Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {
        inmuebleViewModel = new ViewModelProvider(this).get(InmuebleViewModel.class);
        binding = FragmentInmuebleBinding.inflate(inflater, container,false);
        View root = binding.getRoot();
        rvInmueble = binding.rvInmuebles;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
        rvInmueble.setLayoutManager(gridLayoutManager);
        inmuebleViewModel.getInmuebles().observe(getViewLifecycleOwner(), new Observer<List<Inmueble>>() {
            @Override
            public void onChanged(List<Inmueble> inmuebles) {


                    inmuebleAdapter = new InmuebleAdapter(inmuebles, root.getContext(), getLayoutInflater());
                    rvInmueble.setAdapter(inmuebleAdapter);
            }
        });
        inmuebleViewModel.cargarInmueble();

        binding.btCargarIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_main).navigate(R.id.agregarInmuebleFragment);
            }
        });

        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}