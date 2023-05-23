package com.example.trabajopracticoinmobiliaria;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.trabajopracticoinmobiliaria.Models.Propietario;
import com.example.trabajopracticoinmobiliaria.Models.Usuario;
import com.example.trabajopracticoinmobiliaria.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<String> error;
    private SensorManager sensorManager;
    private MutableLiveData<Boolean> permisoLlamada;
    private LeeSensor leeSensor;
    protected static final int CALL_PERMISSION_REQUEST_CODE = 123;
    private static final String NUMERO_INMOBILIARIA = "1131427593";

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        permisoLlamada = new MutableLiveData<>();
        verificarPermisosDeLlamada();
        leeSensor = new LeeSensor(context);
        registerSensorListener();
    }

    public LiveData<String> getError() {
        if (error == null) {
            error = new MutableLiveData<>();
        }
        return error;
    }

    public LiveData<Boolean> getPermisoLlamada() {
        return permisoLlamada;
    }

    public void login(String mail, String password){
        if(!mail.contains("@")){
            error.setValue("Debe brindar una direccion de correo valida");
        }else{
            if(password.isEmpty()){
                error.setValue("Debe ingresar una contraseña");
            }else{
                Usuario miUsuario = new Usuario(mail, password);
                ApiClient.IEndpointInmobiliaria end = ApiClient.getApi();
                Call<String> call = end.login(miUsuario);
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if(response.isSuccessful()){
                            if(response.body()!=null){
                                SharedPreferences sp = context.getSharedPreferences("token.xml",0);
                                SharedPreferences.Editor editor = sp.edit();
                                editor.putString("token","Bearer "+response.body());
                                editor.commit();
                                Intent intent = new Intent(context, MenuActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(intent);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(context, "Error a llamar al login", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }

    public void registerSensorListener() {
        if(leeSensor==null) return;
        leeSensor.registerListener();
    }

    public void unregisterSensorListener() {
        if (leeSensor == null) return;
        leeSensor.unregisterListener();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        leeSensor.unregisterListener();
    }
    public void llamar(){
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + NUMERO_INMOBILIARIA));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    private class LeeSensor implements SensorEventListener {

        private SensorManager sensorManager;
        private Sensor accelerometer;
        private float acceleration, currentAcceleration, lastAcceleration;

        private static final float SHAKE_THRESHOLD = 10.0f;

        public LeeSensor(Context context) {
            this.sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
            this.accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

            acceleration = 0.0f;
            currentAcceleration = SensorManager.GRAVITY_EARTH;
            lastAcceleration = SensorManager.GRAVITY_EARTH;
        }

        public void registerListener() {
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        }

        public void unregisterListener() {
            sensorManager.unregisterListener(this);
        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            lastAcceleration = currentAcceleration;
            currentAcceleration = (float) Math.sqrt((double) (x * x + y * y + z * z));
            float delta = currentAcceleration - lastAcceleration;
            acceleration = acceleration * 0.9f + delta;

            if (acceleration > SHAKE_THRESHOLD && permisoLlamada.getValue()) {
                MainActivityViewModel.this.llamar();
            }

        }



        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {}
    }


    //    Funciones de llamada y permisos
    public void verificarPermisosDeLlamada() {
        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            permisoLlamada.setValue(false);
            return;
        }
        permisoLlamada.setValue(true);
    }

}
