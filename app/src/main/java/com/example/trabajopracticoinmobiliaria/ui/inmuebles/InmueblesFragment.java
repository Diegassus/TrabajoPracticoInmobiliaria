package com.example.trabajopracticoinmobiliaria.ui.inmuebles;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabajopracticoinmobiliaria.databinding.FragmentInmueblesBinding;

public class InmueblesFragment extends Fragment {
    private InmueblesViewModel vm;
    private FragmentInmueblesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        vm = new ViewModelProvider(this).get(InmueblesViewModel.class);

        binding = FragmentInmueblesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RecyclerView rv = binding.lista;
        GridLayoutManager gm = new GridLayoutManager(getContext(), 1,GridLayoutManager.VERTICAL, false);
        rv.setLayoutManager(gm);

        vm.getInmuebles().observe(getViewLifecycleOwner(), inmuebles -> {
            InmuebleAdapter adapter = new InmuebleAdapter(getContext(),inmuebles,getLayoutInflater());
            rv.setAdapter(adapter);
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}