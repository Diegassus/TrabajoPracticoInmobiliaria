package com.example.trabajopracticoinmobiliaria.ui.inquilinos.inquilino;

import android.os.Bundle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.trabajopracticoinmobiliaria.Models.Inmueble;
import com.example.trabajopracticoinmobiliaria.Models.Inquilino;
import com.example.trabajopracticoinmobiliaria.request.ApiClient;

public class LocatarioViewModel extends ViewModel {
    private MutableLiveData<Inquilino> inquilino = new MutableLiveData<>();
    public LocatarioViewModel(){}

    public LiveData<Inquilino> getInquilino(){
        return inquilino;
    }

    public void recuperarInquilino(Bundle bundle){
        //Inquilino inquilino = ApiClient.getApi().obtenerInquilino((Inmueble) bundle.getSerializable("inmueble"));
        //this.inquilino.setValue(inquilino);
    }
}