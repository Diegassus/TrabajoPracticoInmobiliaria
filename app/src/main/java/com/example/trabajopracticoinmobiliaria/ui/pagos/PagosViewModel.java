package com.example.trabajopracticoinmobiliaria.ui.pagos;

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

import com.example.trabajopracticoinmobiliaria.Models.Contrato;
import com.example.trabajopracticoinmobiliaria.Models.Pago;
import com.example.trabajopracticoinmobiliaria.request.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PagosViewModel extends AndroidViewModel {
    private ArrayList<Pago> listaPagos;
    private MutableLiveData<ArrayList<Pago>> pagos;
    private Context context;

    public PagosViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        pagos = new MutableLiveData<>();
    }

    public LiveData<ArrayList<Pago>> getInmuebles() {
        return pagos;
    }

    public void obtenerPagos(Bundle bundle) {
        int id = bundle.getInt("contrato");
        SharedPreferences sp = context.getSharedPreferences("token.xml", 0);
        String token = sp.getString("token", "");
        if(token.isEmpty()){
            return;
        }

        ApiClient.IEndpointInmobiliaria end = ApiClient.getApi();
        Call<List<Pago>> call = end.obtenerPagos(token, id);
        call.enqueue(new Callback<List<Pago>>() {

            @Override
            public void onResponse(Call<List<Pago>> call, Response<List<Pago>> response) {
                if(response.isSuccessful()){
                    if(response.body()!=null){
                        listaPagos = (ArrayList<Pago>) response.body();
                        pagos.setValue(listaPagos);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Pago>> call, Throwable t) {
                Toast.makeText(context, "Error al obtener los pagos del contrato", Toast.LENGTH_SHORT).show();
            }
        });
    }
}