package com.example.prueba.ui.inmuebles;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.prueba.modelo.Inmueble;
import com.example.prueba.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class InmuebleDetalleViewModel extends  AndroidViewModel {
    private MutableLiveData<Inmueble> inmuebleM;
    private Context context;
    private Inmueble i;
    private MutableLiveData<String> error;

    public InmuebleDetalleViewModel(@NonNull Application application) {
        super(application);
        context=application.getApplicationContext();
    }
    public LiveData<Inmueble> getInmuebleM() {
        if(inmuebleM == null){
            inmuebleM = new MutableLiveData<>();
        }
        return inmuebleM;
    }
    public LiveData<String> getError() {
        if(error == null)
        {
            error = new MutableLiveData<>();
        }
        return error;
    }
    public void cargar(Bundle b){
        i = (Inmueble) b.getSerializable("inmueble");

        String tipo=null;
        String uso=null;

        switch(i.getTipo()) {
            case "1": tipo= "Local";break;
            case "2": tipo="Deposito"; break;
            case "3": tipo="Casa";break;
            case "4": tipo="Departamento";break;
        }
        if(i.getUso()== "1")
            uso="Comercial";
        else
            uso="Residencial";
        i.setTipo(tipo);
        i.setUso(uso);
        inmuebleM.setValue(i);
    }
    public void editarInmueble(Inmueble inmu){


        Call<Inmueble> inmueble= ApiClient.getMyApiInterface().EditarInmueble(inmu.getId(),ApiClient.obtenerToken(context));
        inmueble.enqueue(new Callback<Inmueble>() {
            @Override
            public void onResponse(Call<Inmueble> call, Response<Inmueble> response) {

                if(response.isSuccessful()){
                    inmuebleM.setValue(response.body());
                    Toast.makeText(context,"Se ha actualizado el estado del Inmueble!",Toast.LENGTH_LONG).show();
                }
                else {
                    error.setValue("No existen inmuebles");
                }
            }

            @Override
            public void onFailure(Call<Inmueble> call, Throwable t) {

                Toast.makeText(context,"Ha ocurrido un error",Toast.LENGTH_LONG).show();
            }
        });
    }
}