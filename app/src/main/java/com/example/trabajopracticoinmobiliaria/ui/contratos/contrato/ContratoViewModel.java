package com.example.trabajopracticoinmobiliaria.ui.contratos.contrato;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.MutableBoolean;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;

import com.example.trabajopracticoinmobiliaria.Models.Contrato;
import com.example.trabajopracticoinmobiliaria.Models.Inmueble;
import com.example.trabajopracticoinmobiliaria.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContratoViewModel extends AndroidViewModel {
    private MutableLiveData<Contrato> contrato = new MutableLiveData<>();
    private Context context;

    public ContratoViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    public LiveData<Contrato> getContrato() {
        return contrato;
    }
    public void recuperarContrato(Bundle bundle) {
        int inmuebleId = bundle.getInt("inmueble");
        SharedPreferences sp = context.getSharedPreferences("token.xml", 0);
        String token = sp.getString("token", "");

        if(token.isEmpty()) return;

        ApiClient.IEndpointInmobiliaria end = ApiClient.getApi();
        Call<Contrato>call = end.obtenerContrato(token, inmuebleId);
        call.enqueue(new Callback<Contrato>() {

            @Override
            public void onResponse(Call<Contrato> call, Response<Contrato> response) {
                if(response.isSuccessful()){
                    if(response.body()!=null){
                        contrato.setValue(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<Contrato> call, Throwable t) {
                Toast.makeText(context, "Error al obtener el contrato", Toast.LENGTH_SHORT).show();
            }
        });
    }

}