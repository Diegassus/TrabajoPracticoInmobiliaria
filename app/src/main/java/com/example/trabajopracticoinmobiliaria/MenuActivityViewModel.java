package com.example.trabajopracticoinmobiliaria;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.trabajopracticoinmobiliaria.Models.Propietario;
import com.example.trabajopracticoinmobiliaria.request.ApiClient;

public class MenuActivityViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Propietario> propietario;

    public MenuActivityViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<Propietario> getPropietario() {
        if (propietario == null) {
            propietario = new MutableLiveData<>();
        }
        return propietario;
    }

    public void obtenerDatos(){
        propietario.setValue(ApiClient.getApi().obtenerUsuarioActual());
    }
}
