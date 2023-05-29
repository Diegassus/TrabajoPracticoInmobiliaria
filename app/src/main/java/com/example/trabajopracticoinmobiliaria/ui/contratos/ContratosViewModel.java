package com.example.trabajopracticoinmobiliaria.ui.contratos;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.trabajopracticoinmobiliaria.Models.Inmueble;
import com.example.trabajopracticoinmobiliaria.request.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContratosViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<ArrayList<Inmueble>> inmuebles;
    private ArrayList<Inmueble> lista;
    public ContratosViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        inmuebles = new MutableLiveData<>();
        obtenerPropiedades();
    }

    public LiveData<ArrayList<Inmueble>> getInmuebles(){
        return inmuebles;
    }
    // TODO: Implement the ViewModel

    public void obtenerPropiedades(){
        SharedPreferences sp = context.getSharedPreferences("token.xml", 0);
        String token = sp.getString("token", "");
        if(token.isEmpty()){
            return;
        }

        ApiClient.IEndpointInmobiliaria end = ApiClient.getApi();
        Call<List<Inmueble>> call = end.alquiladas(token);
        call.enqueue(new Callback<List<Inmueble>>() {

            @Override
            public void onResponse(Call<List<Inmueble>> call, Response<List<Inmueble>> response) {
                if(response.isSuccessful()){
                    if(response.body() != null){
                        lista = (ArrayList<Inmueble>) response.body();
                        inmuebles.setValue(lista);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Inmueble>> call, Throwable t) {
                Toast.makeText(context, "Error al obtener las propiedades alquiladas", Toast.LENGTH_SHORT).show();
            }
        });

    }
}