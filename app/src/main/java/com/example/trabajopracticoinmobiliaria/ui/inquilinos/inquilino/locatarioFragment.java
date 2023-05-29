package com.example.trabajopracticoinmobiliaria.ui.inquilinos.inquilino;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.trabajopracticoinmobiliaria.R;
import com.example.trabajopracticoinmobiliaria.databinding.FragmentLocatarioBinding;

public class locatarioFragment extends Fragment {

    private LocatarioViewModel vm;
    private FragmentLocatarioBinding binding;

    public static locatarioFragment newInstance() {
        return new locatarioFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        vm = new ViewModelProvider(this).get(LocatarioViewModel.class);
        binding = FragmentLocatarioBinding.inflate(inflater, container, false);
        vm.recuperarInquilino(getArguments());
        vm.getInquilino().observe(getViewLifecycleOwner(), inquilino -> {
           binding.etCodigo.setText(inquilino.getId()+"");
           binding.etApellido.setText(inquilino.getApellido());
           binding.etNombre.setText(inquilino.getNombre());
           binding.etCorreo.setText(inquilino.getCorreo());
           binding.etDni.setText(inquilino.getDni()+"");
           binding.etTelefono.setText(inquilino.getTelefono()+"");
        });

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

}