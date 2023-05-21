package com.example.trabajopracticoinmobiliaria.ui.Ubicacion;

import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.trabajopracticoinmobiliaria.R;
import com.example.trabajopracticoinmobiliaria.databinding.FragmentUbicacionBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class UbicacionFragment extends Fragment {

    protected static final int UBICACION_PERMISSION_REQUEST_CODE = 321;
    public static final LatLng INMOBILIARIA = new LatLng(-32.35634867744977, -64.99709456473751);

    FusedLocationProviderClient fusedLocationProviderClient;
    private Location ubicacionActual;
    private MapaActual mapa;
    SupportMapFragment supportMapFragment;
    private FragmentUbicacionBinding binding;
    private UbicacionViewModel vm;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        vm = new ViewModelProvider(this).get(UbicacionViewModel.class);
        binding = FragmentUbicacionBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity());

        supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);

        verificarPermisios();

        initMapa();

        return root;
    }

    public void initMapa() {
        mapa = new MapaActual();
        supportMapFragment.getMapAsync(mapa);
    }

    private void verificarPermisios() {
        if (ActivityCompat.checkSelfPermission(requireContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(requireActivity(), ubicacion -> {
                if (ubicacion != null) {
                    ubicacionActual = ubicacion;
                    actualizarMapa();
                }
            });
        } else {
            locationPermissionLauncher.launch(android.Manifest.permission.ACCESS_FINE_LOCATION);
        }
    }

    private final ActivityResultLauncher<String> locationPermissionLauncher = registerForActivityResult(
            new ActivityResultContracts.RequestPermission(),
            isGranted -> {
                if (isGranted) {
                    verificarPermisios(); // Ya se que tiene permisos, pero es para obtener la ubicacion y actualizar el mapa
                } else {
                    Toast.makeText(requireActivity(), "Permisos no dadas", Toast.LENGTH_SHORT).show();
                }
            }
    );

    private void actualizarMapa() {
        if (ubicacionHabilitada()) {
            if (mapa == null) mapa = new MapaActual();
            supportMapFragment.getMapAsync(googleMap -> mapa.onMapReady(googleMap, ubicacionActual));
        }
    }

    private boolean ubicacionHabilitada() {
        LocationManager locationManager = requireContext().getSystemService(LocationManager.class);
        return locationManager != null && locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public class MapaActual implements OnMapReadyCallback {

        @Override
        public void onMapReady(@NonNull GoogleMap googleMap) {
            googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            googleMap.addMarker(new MarkerOptions().position(INMOBILIARIA).title("Inmobiliaria La Punta"));

            CameraPosition cameraPosition = new CameraPosition.Builder().target(INMOBILIARIA).zoom(15).bearing(45).tilt(70).build();

            CameraUpdate update = CameraUpdateFactory.newCameraPosition(cameraPosition);
            googleMap.animateCamera(update);
        }

        public void onMapReady(@NonNull GoogleMap googleMap, Location location) {
            googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            googleMap.addMarker(new MarkerOptions().position(INMOBILIARIA).title("Inmobiliaria NetCore"));

            if (location != null) {
                LatLng userLatLng = new LatLng(location.getLatitude(), location.getLongitude());
                googleMap.addMarker(new MarkerOptions().position(userLatLng).title("Tu ubicación"));
            }

            CameraPosition cameraPosition = new CameraPosition.Builder().target(INMOBILIARIA).zoom(15).bearing(45).tilt(70).build();

            CameraUpdate update = CameraUpdateFactory.newCameraPosition(cameraPosition);
            googleMap.animateCamera(update);
        }
    }
}