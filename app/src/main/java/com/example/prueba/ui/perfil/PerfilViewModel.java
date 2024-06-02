package com.example.prueba.ui.perfil;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;

import com.example.prueba.R;
import com.example.prueba.modelo.Propietario;
import com.example.prueba.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilViewModel extends AndroidViewModel {

    private MutableLiveData<Propietario> propietarioMutable;
    private MutableLiveData<String> error;
    private Context context;

    public PerfilViewModel(@NonNull Application application) {
        super(application);
        context=application.getApplicationContext();
    }


    public LiveData<Propietario> getPropietarioMutable() {
        if(propietarioMutable== null)
        {
            propietarioMutable= new MutableLiveData<>();
        }
        return propietarioMutable;
    }

    public LiveData<String> getError() {
        if(error == null)
        {
            error = new MutableLiveData<>();
        }
        return error;
    }

    public void recuperarPropietario()
    {

        Call<Propietario> propietario= ApiClient.getMyApiInterface().MiPerfil(ApiClient.obtenerToken(context));
        propietario.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if(response.isSuccessful()){
                    propietarioMutable.postValue(response.body());
                }
                else{
                    error.setValue("Perfil no encontrado");
                }
            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {


                Toast.makeText(context,"Ha ocurrido un error"+ t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

    }

    public void editarPerfil(Propietario prop)
    {

        Call<Propietario> propietarios= ApiClient.getMyApiInterface().EditarPerfil(prop,ApiClient.obtenerToken(context));

        propietarios.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {

                if(response.isSuccessful()){
                    if(response.body() != null) {
                        propietarioMutable.setValue(response.body());
                        Toast.makeText(context,"Datos actualizados!",Toast.LENGTH_LONG).show();
                    }
                    else{
                         Toast.makeText(context,"No hay datos para actualizar",Toast.LENGTH_LONG).show();
                        }
                }
                else{
                     error.setValue("No se pudo modificar el perfil");
                }

            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {

                Toast.makeText(context,"Ha ocurrido un error"+ t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    public void cambiarPass(int id,String ClaveVieja,String ClaveNueva,String ClaveRepeticion)
    {

        Call<Propietario> propietario= ApiClient.getMyApiInterface().CambiarPass(id,ClaveVieja,ClaveNueva,ClaveRepeticion,ApiClient.obtenerToken(context));
        propietario.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if(response.isSuccessful()){
                    propietarioMutable.postValue(response.body());
                    Toast.makeText(context,"Contraseña actualizada!",Toast.LENGTH_LONG).show();
                    System.exit(0);
                }
                else{
                    error.setValue("No se puede cambiar contraseña");
                }
            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {


                Toast.makeText(context,"Ha ocurrido un error"+ t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}