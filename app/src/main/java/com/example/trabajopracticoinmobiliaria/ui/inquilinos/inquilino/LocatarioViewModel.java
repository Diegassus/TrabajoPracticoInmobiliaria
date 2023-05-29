package com.example.trabajopracticoinmobiliaria.ui.inquilinos.inquilino;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.trabajopracticoinmobiliaria.Models.Inmueble;
import com.example.trabajopracticoinmobiliaria.Models.Inquilino;
import com.example.trabajopracticoinmobiliaria.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocatarioViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Inquilino> inquilino = new MutableLiveData<>();

    public LocatarioViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    public LiveData<Inquilino> getInquilino(){
        return inquilino;
    }

    public void recuperarInquilino(Bundle bundle){
        SharedPreferences sp = context.getSharedPreferences("token.xml", 0);
        String token = sp.getString("token", "");
        if(token.isEmpty()){
            return;
        }

        ApiClient.IEndpointInmobiliaria end = ApiClient.getApi();
        Call<Inquilino> call = end.obtenerInquilino(token, bundle.getInt("inmueble"));
        call.enqueue(new Callback<Inquilino>() {

            @Override
            public void onResponse(Call<Inquilino> call, Response<Inquilino> response) {
                if(response.isSuccessful()){
                    if(response.body() != null){
                        inquilino.setValue(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<Inquilino> call, Throwable t) {
                Toast.makeText(context, "No se pudo obtener al inquilino", Toast.LENGTH_SHORT).show();
            }
        });
    }
}