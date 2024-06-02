package com.example.prueba.ui.login;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.prueba.modelo.Propietario;
import com.example.prueba.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivityViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<String> error;
    private MutableLiveData<Boolean> loginMutable;
    private MutableLiveData<Boolean> resetMutable;
    private MutableLiveData<Boolean> estadoM;
    private int activador = 0;
    public LoginActivityViewModel(@NonNull Application application) {
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

    public MutableLiveData<Boolean> getResetMutable() {

        if(resetMutable == null)
        {
            resetMutable = new MutableLiveData<>();
        }
        return resetMutable;
    }
    public MutableLiveData<Boolean> getLoginMutable() {

        if(loginMutable == null)
        {
            loginMutable = new MutableLiveData<>();
        }
        return loginMutable;
    }

    public LiveData<Boolean> getEstadoM() {
        if(estadoM == null){
            estadoM = new MutableLiveData<>();
        }
        return estadoM;
    }

    public void autenticar(String user, String pass) {

        Call<String> dato = ApiClient.getMyApiInterface().Login(user, pass);
        dato.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    SharedPreferences sp = context.getSharedPreferences("datos.dat", 0);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("Token", "Bearer " + response.body());
                    editor.commit();
                    loginMutable.setValue(true);

                } else {

                     error.setValue("El usuario o contraseña son incorrectos");
                }


            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("Token", "Salida Incorrecta" + t.getMessage());
            }
        });

    }
    public void sensorG(float x){
        if(x > 1 || x < -1){
            activador++;
        }
        if(activador > 20){
            activador = 0;
            estadoM.setValue(true);
        }
    }

    public void resetClave(String user)
    {
        Call<String> dato = ApiClient.getMyApiInterface().EnvioClave(user);

        dato.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(context, "Verifique su casilla de mail para resear clave", Toast.LENGTH_LONG).show();
                } else {

                    error.setValue("El usuario o contraseña son incorrectos");
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(context, "Ha ocurrido un error" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


    }
}
