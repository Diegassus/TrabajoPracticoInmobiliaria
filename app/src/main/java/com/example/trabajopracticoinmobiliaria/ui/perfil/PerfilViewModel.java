package com.example.trabajopracticoinmobiliaria.ui.perfil;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.trabajopracticoinmobiliaria.Models.Propietario;
import com.example.trabajopracticoinmobiliaria.request.ApiClient;

public class PerfilViewModel extends AndroidViewModel {
    private MutableLiveData<Boolean> enabled;
    private MutableLiveData<Propietario> propietario;
    private Context context;
    public PerfilViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        propietario = new MutableLiveData<>();
        cargarDatos();
    }

    public LiveData<Boolean> getEnabled() {
        if(enabled == null){
            enabled = new MutableLiveData<>();
            enabled.setValue(false);
        }
        return enabled;
    }

    public LiveData<Propietario> getPropietario() {
        return propietario;
    }

    public void cargarDatos(){
        Propietario p = ApiClient.getApi().obtenerUsuarioActual();
        propietario.setValue(p);
    }

    public void turnEnabled() {
        this.enabled.setValue(!this.enabled.getValue());
    }

    public void guardarPropietario(Long dni, String nombre, String apellido, String correo, String telefono, String clave){
        Propietario actual = new Propietario(propietario.getValue().getId(), dni, nombre, apellido, correo, clave, telefono, propietario.getValue().getAvatar());
        ApiClient.getApi().actualizarPerfil(actual);
        propietario.setValue(actual);
        turnEnabled();
    }
}