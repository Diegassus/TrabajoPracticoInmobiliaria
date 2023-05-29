package com.example.trabajopracticoinmobiliaria.ui.contratos;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.trabajopracticoinmobiliaria.Models.Inmueble;
import com.example.trabajopracticoinmobiliaria.R;
import com.example.trabajopracticoinmobiliaria.databinding.FragmentContratosBinding;

import java.util.ArrayList;

public class ContratosFragment extends Fragment {

    private ContratosViewModel vm;
    private FragmentContratosBinding binding;

    public static ContratosFragment newInstance() {
        return new ContratosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        vm = new ViewModelProvider(this).get(ContratosViewModel.class);
        binding=FragmentContratosBinding.inflate(inflater,container,false);

        RecyclerView rv = binding.listaContratos;
        GridLayoutManager gm = new GridLayoutManager(getContext(),1,GridLayoutManager.VERTICAL,false);
        rv.setLayoutManager(gm);

        vm.getInmuebles().observe(getViewLifecycleOwner(), new Observer<ArrayList<Inmueble>>() {
            @Override
            public void onChanged(ArrayList<Inmueble> inmuebles) {
                rv.setAdapter(new ContratosAdapter(getContext(),inmuebles,getLayoutInflater()));
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}