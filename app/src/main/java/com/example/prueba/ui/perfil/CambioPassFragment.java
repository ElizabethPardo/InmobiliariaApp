package com.example.prueba.ui.perfil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.prueba.R;
import com.example.prueba.modelo.CambioClaveView;
import com.example.prueba.modelo.Propietario;
import com.google.android.material.textfield.TextInputEditText;

public class CambioPassFragment extends Fragment {
    private PerfilViewModel vm;
    private Button btAceptarCambiarPass;
    private TextInputEditText etClaveVieja;
    private TextInputEditText etClaveRepeticion;
    private TextInputEditText etClaveNueva;
    private int id;
    private Propietario propietarioActual;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_cambio_pass, container, false);
        inicializar(root);

        return root;
    }

    private  void  inicializar(View view)
    {   etClaveVieja=view.findViewById(R.id.etClaveVieja);
        etClaveRepeticion=view.findViewById(R.id.etClaveRepeticion);
        etClaveNueva=view.findViewById(R.id.etClaveNueva);

        btAceptarCambiarPass=view.findViewById(R.id.btAceptarCambiarPass);
        vm= ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(PerfilViewModel.class);
        vm.getPropietarioMutable().observe(getViewLifecycleOwner(), new Observer<Propietario>() {
            @Override
            public void onChanged(Propietario propietario) {
               id= propietario.getId();

                propietarioActual=propietario;

            }
        });

        vm.recuperarPropietario();

        btAceptarCambiarPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                vm.cambiarPass(id,etClaveVieja.getText().toString(),etClaveNueva.getText().toString(),etClaveRepeticion.getText().toString());


            }
        });
        vm.getError().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getContext(),s, Toast.LENGTH_LONG).show();
            }
        });
    }
}
