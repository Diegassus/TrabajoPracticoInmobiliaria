package com.example.trabajopracticoinmobiliaria.ui.Ubicacion;

import android.app.Application;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
public class UbicacionViewModel extends AndroidViewModel {
    private Context context;

    public UbicacionViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }



}