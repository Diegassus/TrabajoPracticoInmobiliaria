package com.example.trabajopracticoinmobiliaria.ui.perfil;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.trabajopracticoinmobiliaria.Models.Propietario;
import com.example.trabajopracticoinmobiliaria.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        ApiClient.IEndpointInmobiliaria end = ApiClient.getApi();
        Call<Propietario> call = end.obtenerPerfil(context.getSharedPreferences("token.xml",0).getString("token",""));
        call.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if(response.isSuccessful()){
                    if(response.body()!=null){
                        propietario.setValue(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {
                Toast.makeText(context, "Error cargar propietario", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void turnEnabled() {
        this.enabled.setValue(!this.enabled.getValue());
    }

    public void guardarPropietario(Long dni, String nombre, String apellido, String correo, String telefono, String clave){
//        Propietario actual = new Propietario(propietario.getValue().getId(), dni, nombre, apellido, correo, clave, telefono, propietario.getValue().getAvatar());
//        ApiClient.getApi().actualizarPerfil(actual);
//        propietario.setValue(actual);
        turnEnabled();

        // desarrollar logica para actualizar propietario
    }
}