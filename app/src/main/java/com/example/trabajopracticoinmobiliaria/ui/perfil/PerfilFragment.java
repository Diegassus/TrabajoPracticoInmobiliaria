package com.example.trabajopracticoinmobiliaria.ui.perfil;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.trabajopracticoinmobiliaria.databinding.FragmentPerfilBinding;

public class PerfilFragment extends Fragment {
    private PerfilViewModel vm;
    private FragmentPerfilBinding binding;

    @SuppressLint("SetTextI18n")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        vm= new ViewModelProvider(this).get(PerfilViewModel.class);

        binding = FragmentPerfilBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        vm.getPropietario().observe(getViewLifecycleOwner(), p -> {
            binding.etDni.setText(p.getDni() + "");
            binding.etNombre.setText(p.getNombre());
            binding.etApellido.setText(p.getApellido());
            binding.etCorreo.setText(p.getCorreo());
            binding.etTelefono.setText(p.getTelefono());
            //binding.etPassword.setText(p.getContraseña());
            binding.etCodigo.setText(p.getId()+"");
        });

        vm.getEnabled().observe(getViewLifecycleOwner(), enabled -> {
            binding.etDni.setEnabled(enabled);
            binding.etNombre.setEnabled(enabled);
            binding.etApellido.setEnabled(enabled);
            binding.etCorreo.setEnabled(enabled);
            binding.etTelefono.setEnabled(enabled);
            binding.etPassword.setEnabled(enabled);
            binding.btnEditar.setText(enabled ? "Guardar" : "Editar");
        });

        binding.btnEditar.setOnClickListener(v ->vm.guardarPropietario(binding.etDni.getText().toString(),binding.etNombre.getText().toString(),binding.etApellido.getText().toString(),binding.etCorreo.getText().toString(),binding.etTelefono.getText().toString(),binding.etPassword.getText().toString()));

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}