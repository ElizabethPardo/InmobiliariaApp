package com.example.prueba.ui.inquilinos;

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

import com.example.prueba.modelo.Contrato;
import com.example.prueba.modelo.Inmueble;
import com.example.prueba.modelo.Inquilino;
import com.example.prueba.request.ApiClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InquilinoDetalleViewModel extends AndroidViewModel {

    private MutableLiveData<Inquilino> inquilinoM;
    private Context context;
    private MutableLiveData<String> error;

    public InquilinoDetalleViewModel(@NonNull Application application) {
        super(application);
        context=application.getApplicationContext();
    }


    public LiveData<String> getError() {
        if(error == null)
        {
            error = new MutableLiveData<>();
        }
        return error;
    }
    public LiveData<Inquilino> getInquilinoM() {
        if(inquilinoM == null){
            inquilinoM = new MutableLiveData<>();
        }
        return inquilinoM;
    }
    public void cargar(Bundle b){
       Inmueble inmu = (Inmueble) b.getSerializable("inmueble");

        Call<Inquilino> inquilino= ApiClient.getMyApiInterface().VerInquilino(inmu.getId(),ApiClient.obtenerToken(context));

        inquilino.enqueue(new Callback<Inquilino>() {
            @Override
            public void onResponse(Call<Inquilino> call, Response<Inquilino> response) {

                if(response.isSuccessful()){
                    inquilinoM.setValue(response.body());
                }
                else {
                    error.setValue("No existe Inquilino");
                }
            }

            @Override
            public void onFailure(Call<Inquilino> call, Throwable t) {

                Toast.makeText(context,"Ha ocurrido un error",Toast.LENGTH_LONG).show();
            }
        });

    }


}
