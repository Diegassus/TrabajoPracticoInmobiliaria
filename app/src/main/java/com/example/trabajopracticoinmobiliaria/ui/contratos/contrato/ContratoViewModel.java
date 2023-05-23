package com.example.trabajopracticoinmobiliaria.ui.contratos.contrato;

import android.os.Bundle;
import android.util.MutableBoolean;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;

import com.example.trabajopracticoinmobiliaria.Models.Contrato;
import com.example.trabajopracticoinmobiliaria.Models.Inmueble;
import com.example.trabajopracticoinmobiliaria.request.ApiClient;

public class ContratoViewModel extends ViewModel {
    private MutableLiveData<Contrato> contrato = new MutableLiveData<>();
    public ContratoViewModel() {}
    public LiveData<Contrato> getContrato() {
        return contrato;
    }
    public void recuperarContrato(Bundle bundle){;
        //this.contrato.setValue(ApiClient.getApi().obtenerContratoVigente((Inmueble) bundle.getSerializable("inmueble")));
    }

}