package com.example.trabajopracticoinmobiliaria.ui.perfil;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
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

    public void guardarPropietario(String dni, String nombre, String apellido, String correo, String telefono, String clave){
        turnEnabled();
        if(enabled.getValue().booleanValue()){
            return;
        }
        SharedPreferences sp = context.getSharedPreferences("token.xml",0);
        String token = sp.getString("token","");
        Log.d("salida",token);
        if(token.isEmpty()) {
            return;
        }
        //String nombre, String apellido, String clave, String correo, String telefono, int id, String dni, boolean estado
        ApiClient.IEndpointInmobiliaria end = ApiClient.getApi();
        Propietario p = new Propietario(nombre,apellido,clave,correo,telefono,propietario.getValue().getId(),dni,true   );
        if(p.getClave().isEmpty()){
            p.setClave(propietario.getValue().getClave());
        }
        System.out.println(p.toString());
        Call<Propietario> call = end.editar(token, p);
        call.enqueue(new Callback<Propietario>() {

            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if(response.isSuccessful()){
                    if(response.body()!= null){
                        propietario.setValue(response.body());
                        System.out.println(response.body().toString());
                    }
                }else {
                    Log.d("salida",response.message());
                }
            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {
                Toast.makeText(context, "Error al actualizar", Toast.LENGTH_SHORT).show();
            }
        });
        // desarrollar logica para actualizar propietario
    }
}