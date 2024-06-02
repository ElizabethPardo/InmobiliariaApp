package com.example.prueba.ui.contratos;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.prueba.modelo.Contrato;
import com.example.prueba.modelo.Inmueble;
import com.example.prueba.request.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContratoViewModel extends AndroidViewModel {


    private MutableLiveData<ArrayList<Contrato>> contratosMutable;
    private Context context;
    private MutableLiveData<String> error;
    public ContratoViewModel(@NonNull Application application) {
        super(application);
        context=application.getApplicationContext();
    }

    public LiveData<ArrayList<Contrato>> getContratosMutable() {

        if(contratosMutable== null)
        {
            contratosMutable= new MutableLiveData<>();
        }
        return contratosMutable;
    }
    public LiveData<String> getError() {
        if(error == null)
        {
            error = new MutableLiveData<>();
        }
        return error;
    }

        public void cargarContratos()
        {
            Call<ArrayList<Contrato>> contratos= ApiClient.getMyApiInterface().ListaContrato(ApiClient.obtenerToken(context));
            contratos.enqueue(new Callback<ArrayList<Contrato>>() {
                @Override
                public void onResponse(Call<ArrayList<Contrato>> call, Response<ArrayList<Contrato>> response) {
                    if (response.isSuccessful())
                    {

                        contratosMutable.postValue(response.body());
                    }
                    else{
                        error.postValue("No hubo respuesta");
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<Contrato>> call, Throwable t) {

                    error.setValue("No existen contratos");

                }
            });
        }




}
