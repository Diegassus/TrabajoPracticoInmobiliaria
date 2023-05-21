package com.example.trabajopracticoinmobiliaria.ui.pagos;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.trabajopracticoinmobiliaria.Models.Contrato;
import com.example.trabajopracticoinmobiliaria.Models.Pago;
import com.example.trabajopracticoinmobiliaria.request.ApiClient;

import java.util.ArrayList;

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
        Contrato contrato = (Contrato) bundle.getSerializable("contrato");
        listaPagos = ApiClient.getApi().obtenerPagos(contrato);
        pagos.setValue(listaPagos);
    }
}