package com.example.trabajopracticoinmobiliaria.ui.inmuebles.detalle;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.trabajopracticoinmobiliaria.R;
import com.example.trabajopracticoinmobiliaria.databinding.FragmentDetalleBinding;

public class DetalleFragment extends Fragment {

    private DetalleViewModel vm;
    private FragmentDetalleBinding binding;

    public static DetalleFragment newInstance() {
        return new DetalleFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentDetalleBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        vm = new ViewModelProvider(this).get(DetalleViewModel.class);

        vm.recuperarInmueble(getArguments());

        vm.getInmueble().observe(getViewLifecycleOwner(), inmueble -> {
            binding.cbDisponible.setChecked(inmueble.isDisponible());
            binding.etAmbientes.setText(String.valueOf(inmueble.getAmbientes()));
            binding.etCalle.setText(inmueble.getDireccion());
            binding.etCodigo.setText(inmueble.getId()+"");
            binding.etPrecio.setText("$"+inmueble.getPrecio());
            binding.etTipo.setText(inmueble.getTipo()+"");
            binding.etUso.setText(inmueble.getUso()+"");
            Glide.with(getContext())
                    .load(inmueble.getFoto())
                    .into(binding.ivImagenCasa);
        });

        binding.cbDisponible.setOnClickListener(v -> {
            vm.setEstado(binding.cbDisponible.isChecked());
        });

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // TODO: Use the ViewModel
    }

}