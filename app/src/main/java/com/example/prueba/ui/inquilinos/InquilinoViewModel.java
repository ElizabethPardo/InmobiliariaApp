package com.example.prueba.ui.inquilinos;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.prueba.modelo.Contrato;
import com.example.prueba.modelo.Inmueble;
import com.example.prueba.modelo.Inquilino;
import com.example.prueba.request.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InquilinoViewModel extends AndroidViewModel {

    private MutableLiveData<List<Inmueble>> lista;
    private MutableLiveData<ArrayList<Inmueble>> inmueblesMutable;
    private Context context;
    private MutableLiveData<String> error;

    public InquilinoViewModel(@NonNull Application application) {
        super(application);
        context=application.getApplicationContext();
    }

    public LiveData<ArrayList<Inmueble>> getInmuebles() {

        if(inmueblesMutable== null)
        {
            inmueblesMutable= new MutableLiveData<>();
        }
        return inmueblesMutable;
    }
    public LiveData<List<Inmueble>> getLista() {
        if(lista == null){
            lista = new MutableLiveData<>();
        }
        return lista;
    }

    public LiveData<String> getError() {
        if(error == null)
        {
            error = new MutableLiveData<>();
        }
        return error;
    }
    public void cargarInmueble(){

        Call<ArrayList<Inmueble>> inmuebles= ApiClient.getMyApiInterface().ListaInmuebleDis(ApiClient.obtenerToken(context));

        inmuebles.enqueue(new Callback<ArrayList<Inmueble>>() {
            @Override
            public void onResponse(Call<ArrayList<Inmueble>> call, Response<ArrayList<Inmueble>> response) {
                if(response.isSuccessful()){
                    inmueblesMutable.postValue(response.body());
                }
                else {
                    error.setValue("No existen inmuebles");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Inmueble>> call, Throwable t) {

                Toast.makeText(context,"Ha ocurrido un error", Toast.LENGTH_LONG).show();
            }
        });

    }

}
