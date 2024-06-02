package com.example.prueba.ui.contratos;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.prueba.modelo.Contrato;
import com.example.prueba.modelo.Inquilino;
import com.example.prueba.modelo.Pago;
import com.example.prueba.request.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PagoViewModel extends AndroidViewModel {
    private MutableLiveData<List<Pago>> pagosM;

    private Context context;
    private MutableLiveData<String> error;
    public PagoViewModel(@NonNull Application application) {
        super(application);
        context=application.getApplicationContext();
    }

    public LiveData<List<Pago>> getPagosM() {
        if(pagosM == null){
            pagosM = new MutableLiveData<>();
        }
        return pagosM;
    }

    public LiveData<String> getError() {
        if(error == null)
        {
            error = new MutableLiveData<>();
        }
        return error;
    }
    public void cargarPagos(Bundle bundle){

        Contrato contrato = (Contrato) bundle.getSerializable("contrato");

        Call<ArrayList<Pago>> pagos= ApiClient.getMyApiInterface().PagosxContrato(contrato.getId(),ApiClient.obtenerToken(context));

        pagos.enqueue(new Callback<ArrayList<Pago>>() {
            @Override
            public void onResponse(Call<ArrayList<Pago>> call, Response<ArrayList<Pago>> response) {
                if (response.isSuccessful())
                {
                    if(response.body().size() != 0) {
                        pagosM.postValue(response.body());
                    }
                    else{
                        error.postValue("No tiene pagos");
                    }
                }
                else{
                    error.postValue("No hubo respuesta");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Pago>> call, Throwable t) {

                error.setValue("No existen Pagos");

            }
        });

    }
}
