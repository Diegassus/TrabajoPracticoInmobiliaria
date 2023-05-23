package com.example.trabajopracticoinmobiliaria.ui.contratos;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.trabajopracticoinmobiliaria.Models.Inmueble;
import com.example.trabajopracticoinmobiliaria.request.ApiClient;

import java.util.ArrayList;

public class ContratosViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<ArrayList<Inmueble>> inmuebles;
    private ArrayList<Inmueble> lista;
    public ContratosViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        inmuebles = new MutableLiveData<>();
        //lista = ApiClient.getApi().obtenerPropiedadesAlquiladas();
        inmuebles.setValue(lista);
    }

    public LiveData<ArrayList<Inmueble>> getInmuebles(){
        return inmuebles;
    }
    // TODO: Implement the ViewModel
}