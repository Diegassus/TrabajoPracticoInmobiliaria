package com.example.trabajopracticoinmobiliaria.ui.inquilinos.inquilino;

import android.os.Bundle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.trabajopracticoinmobiliaria.Models.Inquilino;

public class LocatarioViewModel extends ViewModel {
    private MutableLiveData<Inquilino> inquilino = new MutableLiveData<>();
    public LocatarioViewModel(){}

    public LiveData<Inquilino> getInquilino(){
        return inquilino;
    }

    public void recuperarInquilino(Bundle bundle){
        Inquilino inquilino = (Inquilino) bundle.getSerializable("inquilino");
        this.inquilino.setValue(inquilino);
    }
}