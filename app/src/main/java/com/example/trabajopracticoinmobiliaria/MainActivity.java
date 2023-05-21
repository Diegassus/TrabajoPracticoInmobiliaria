package com.example.trabajopracticoinmobiliaria;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import com.example.trabajopracticoinmobiliaria.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainActivityViewModel vm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainActivityViewModel.class);
        vm.getError().observe(this, s -> binding.tvError.setText(s));

        binding.btnLogin.setOnClickListener(v -> vm.login(binding.etUsername.getText().toString(), binding.etPassword.getText().toString()));

        vm.getPermisoLlamada().observe(this, b -> {
            if(!b){
                pedirPermisos();
            }
        });
    }

    private void pedirPermisos(){
        ActivityCompat.requestPermissions(this, new String[]{
                android.Manifest.permission.CALL_PHONE
        },MainActivityViewModel.CALL_PERMISSION_REQUEST_CODE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        vm.registerSensorListener();
    }

    @Override
    protected void onPause() {
        super.onPause();
        vm.unregisterSensorListener();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MainActivityViewModel.CALL_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                vm.verificarPermisosDeLlamada();
            } else {
                Toast.makeText(this, "Permiso denegado", Toast.LENGTH_SHORT).show();
            }
        }
    }
}