package com.example.trabajopracticoinmobiliaria.ui.inmuebles;

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

public class InmueblesViewModel extends AndroidViewModel {
    private ArrayList<Inmueble> listaInmuebles;
    private MutableLiveData<ArrayList<Inmueble>> inmuebles;
    private Context context;

    public InmueblesViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        inmuebles = new MutableLiveData<>();
        listaInmuebles = ApiClient.getApi().obtnerPropiedades();
        inmuebles.setValue(listaInmuebles);
    }

    public LiveData<ArrayList<Inmueble>> getInmuebles() {
        return inmuebles;
    }
}