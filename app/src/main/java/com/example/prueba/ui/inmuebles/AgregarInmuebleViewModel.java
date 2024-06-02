package com.example.prueba.ui.inmuebles;

import static android.app.Activity.RESULT_OK;

import android.app.Application;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.prueba.modelo.Inmueble;
import com.example.prueba.modelo.RealPathUtil;
import com.example.prueba.request.ApiClient;

import java.io.File;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Multipart;

public class AgregarInmuebleViewModel extends AndroidViewModel {
    private Context context;

    private MutableLiveData<Uri> uriMutable;
    public AgregarInmuebleViewModel(@NonNull Application application) {
        super(application);
        context=application.getApplicationContext();
    }

    public LiveData<Uri> getUriMutable() {

        if(uriMutable== null)
        {
            uriMutable= new MutableLiveData<>();
        }
        return uriMutable;
    }

    public void recibirFoto(ActivityResult result){

    if(result.getResultCode() == RESULT_OK)
    {
        Intent data=result.getData();
        Uri uri=data.getData();
        uriMutable.setValue(uri);
    }


    }

    public void NuevoInmueble(Inmueble inmueble,Uri uri)
    {   int iduso=0;
        int idtipo=0;
        switch(inmueble.getTipo()) {
            case "Local": idtipo= 1;break;
            case "Deposito": idtipo=2; break;
            case "Casa": idtipo=3;break;
            case "Departamento": idtipo=4;break;
        }
        if(inmueble.getUso()== "Comercial")
            iduso=1;
        else
            iduso=2;
        RequestBody direccion= RequestBody.create(MediaType.parse("application/json"),inmueble.getDireccion());
        RequestBody ambientes= RequestBody.create(MediaType.parse("application/json"),inmueble.getAmbientes()+ "");
        RequestBody uso= RequestBody.create(MediaType.parse("application/json"),iduso + "");
        RequestBody tipo= RequestBody.create(MediaType.parse("application/json"),idtipo + "");
        RequestBody precio= RequestBody.create(MediaType.parse("application/json"),inmueble.getPrecio()+"");
        RequestBody estado= RequestBody.create(MediaType.parse("application/json"),inmueble.getEstado()+"");
        String rutaArchivo= RealPathUtil.getRealPath(getApplication(),uri);

        File archivo= new File(rutaArchivo);

        RequestBody imagen= RequestBody.create(MediaType.parse("multipart/form-data"),archivo);
        MultipartBody.Part imagenFile= MultipartBody.Part.createFormData("ImagenFile",archivo.getName(),imagen);

        Call<Inmueble> inmu= ApiClient.getMyApiInterface().NuevoInmueble(
                ApiClient.obtenerToken(context),direccion,ambientes,uso,tipo,precio,estado,imagenFile);

        inmu.enqueue(new Callback<Inmueble>() {
            @Override
            public void onResponse(Call<Inmueble> call, Response<Inmueble> response) {
                if(response.isSuccessful()){

                    Toast.makeText(context,"Inmueble creado correctamente!", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(context,"Ha ocurrido un error", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Inmueble> call, Throwable t) {

                Toast.makeText(context,"Ha ocurrido una falla", Toast.LENGTH_LONG).show();
            }
        });
    }
}
