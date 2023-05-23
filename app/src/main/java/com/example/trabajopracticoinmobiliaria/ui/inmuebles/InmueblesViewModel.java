package com.example.trabajopracticoinmobiliaria.ui.inmuebles;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.trabajopracticoinmobiliaria.Models.Inmueble;
import com.example.trabajopracticoinmobiliaria.Models.Propietario;
import com.example.trabajopracticoinmobiliaria.request.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InmueblesViewModel extends AndroidViewModel {
    private ArrayList<Inmueble> listaInmuebles;
    private MutableLiveData<ArrayList<Inmueble>> inmuebles;
    private Context context;

    public InmueblesViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        inmuebles = new MutableLiveData<>();
        //listaInmuebles = ApiClient.getApi().obtnerPropiedades();
        verInmuebles();
    }

    public LiveData<ArrayList<Inmueble>> getInmuebles() {
        return inmuebles;
    }

    private void verInmuebles(){
        SharedPreferences sp = context.getSharedPreferences("token.xml",0);
        String token = sp.getString("token","");

        if(token.isEmpty()){
            return;
        }

        ApiClient.IEndpointInmobiliaria end = ApiClient.getApi();
        Call<List<Inmueble>> call = end.obtenerInmuebles(token);
        call.enqueue(new Callback<List<Inmueble>>() {
            @Override
            public void onResponse(Call<List<Inmueble>> call, Response<List<Inmueble>> response) {
                if(response.isSuccessful()){
                    if(response.body()!=null){
                        listaInmuebles = (ArrayList<Inmueble>) response.body();
                        inmuebles.setValue(listaInmuebles);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Inmueble>> call, Throwable t) {
                Toast.makeText(context, "Error al llamar las propiedades", Toast.LENGTH_SHORT).show();
            }

        });
    }

}