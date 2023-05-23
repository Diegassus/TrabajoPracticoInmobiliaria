package com.example.trabajopracticoinmobiliaria.ui.inmuebles.detalle;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.trabajopracticoinmobiliaria.Models.Inmueble;
import com.example.trabajopracticoinmobiliaria.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Inmueble> inmuebleM;
    public DetalleViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        inmuebleM = new MutableLiveData<>();
    }


    public LiveData<Inmueble> getInmueble(){
        return inmuebleM;
    }
    public void recuperarInmueble(Bundle bundle){

        Inmueble inmueble = (Inmueble) bundle.getSerializable("inmueble");
//        this.inmueble.setValue(inmueble);
        SharedPreferences sp = context.getSharedPreferences("token.xml",0);
        String token = sp.getString("token","");

        if(token.isEmpty())return;

        ApiClient.IEndpointInmobiliaria end = ApiClient.getApi();
        Call<Inmueble> call = end.obtenerInmueble(token,inmueble.getId());
        call.enqueue(new Callback<Inmueble>() {
            @Override
            public void onResponse(Call<Inmueble> call, Response<Inmueble> response) {
                if(response.isSuccessful()){
                    if(response.body()!=null){
                        inmuebleM.setValue(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<Inmueble> call, Throwable t) {
                Toast.makeText(context, "Error al obtener la propiedad", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setEstado(boolean estado){
        inmuebleM.getValue().setDisponible(estado);
        //ApiClient.getApi().actualizarInmueble(inmueble.getValue());
    }
}