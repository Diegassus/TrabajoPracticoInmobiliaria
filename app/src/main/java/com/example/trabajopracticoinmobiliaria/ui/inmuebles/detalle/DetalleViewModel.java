package com.example.trabajopracticoinmobiliaria.ui.inmuebles.detalle;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.trabajopracticoinmobiliaria.Models.Inmueble;
import com.example.trabajopracticoinmobiliaria.request.ApiClient;

public class DetalleViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Inmueble> inmueble;
    public DetalleViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        inmueble = new MutableLiveData<>();
    }


    public LiveData<Inmueble> getInmueble(){
        return inmueble;
    }
    public void recuperarInmueble(Bundle bundle){

        Inmueble inmueble = (Inmueble) bundle.getSerializable("inmueble");
        this.inmueble.setValue(inmueble);
    }

    public void setEstado(boolean estado){
        inmueble.getValue().setEstado(estado);
    }
}