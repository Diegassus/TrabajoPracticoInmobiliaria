package com.example.trabajopracticoinmobiliaria.ui.pagos;

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

import com.example.trabajopracticoinmobiliaria.R;
import com.example.trabajopracticoinmobiliaria.databinding.FragmentPagosBinding;

public class PagosFragment extends Fragment {

    private PagosViewModel vm;
    private FragmentPagosBinding binding;

    public static PagosFragment newInstance() {
        return new PagosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        vm = new ViewModelProvider(this).get(PagosViewModel.class);
        binding = FragmentPagosBinding.inflate(inflater, container, false);
        vm.obtenerPagos(getArguments());

        RecyclerView rv = binding.listaPagos;
        GridLayoutManager gm = new GridLayoutManager(getContext(),1,RecyclerView.VERTICAL,false);
        rv.setLayoutManager(gm);

        vm.getInmuebles().observe(getViewLifecycleOwner(), inmuebles -> {
            rv.setAdapter(new PagosAdapter(getContext(),inmuebles,getLayoutInflater()));
        });

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}