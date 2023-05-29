package com.example.trabajopracticoinmobiliaria.ui.contratos.contrato;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.trabajopracticoinmobiliaria.R;
import com.example.trabajopracticoinmobiliaria.databinding.FragmentContratoBinding;
import com.example.trabajopracticoinmobiliaria.databinding.FragmentContratosBinding;

public class ContratoFragment extends Fragment {

    private ContratoViewModel vm;
    private FragmentContratoBinding binding;

    public static ContratoFragment newInstance() {
        return new ContratoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        vm = new ViewModelProvider(this).get(ContratoViewModel.class);
        vm.recuperarContrato(getArguments());
        binding = FragmentContratoBinding.inflate(inflater, container, false);

        vm.getContrato().observe(getViewLifecycleOwner(), contrato -> {
            binding.etCodigo.setText(contrato.getId()+"");
            binding.etFin.setText(contrato.getHasta()+"");
            binding.etInicio.setText(contrato.getDesde()+"");
            binding.etInquilino.setText(contrato.getArrendatario().getNombre()+ " "+contrato.getArrendatario().getApellido());
            binding.etMonto.setText(contrato.getMensualidad()+"");
            binding.etInmueble.setText("Inmueble en "+contrato.getBien().getDireccion()+"");
        });

        binding.btnPagos.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("contrato", vm.getContrato().getValue());
            Navigation.findNavController(v).navigate(R.id.pagosFragment, bundle);
        });

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}