package com.example.trabajopracticoinmobiliaria.request;

import com.example.trabajopracticoinmobiliaria.Models.Contrato;
import com.example.trabajopracticoinmobiliaria.Models.Inmueble;
import com.example.trabajopracticoinmobiliaria.Models.Inquilino;
import com.example.trabajopracticoinmobiliaria.Models.Pago;
import com.example.trabajopracticoinmobiliaria.Models.Propietario;
import com.example.trabajopracticoinmobiliaria.Models.Usuario;
import com.example.trabajopracticoinmobiliaria.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public class ApiClient {
    private static final String PATH = "http://192.168.0.147:5200/api/";
    private static IEndpointInmobiliaria servicio;
    public static IEndpointInmobiliaria getApi() {
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(PATH)
                .addConverterFactory(GsonConverterFactory.create(gson)).build();
        return servicio = retrofit.create(IEndpointInmobiliaria.class);
    }



    public interface IEndpointInmobiliaria{
        @POST("Propietarios/Login")
        Call<String> login(@Body Usuario user);

        @PUT("Propietarios/editar")
        Call<Propietario> editar(@Header("Authorization") String token,@Body Propietario propietario);

        @GET("Propietarios/User")
        Call<Propietario> obtenerPerfil(@Header("Authorization") String token);

        @GET("Inmuebles/propiedadesUsuario")
        Call<List<Inmueble>> obtenerInmuebles(@Header("Authorization") String token);

        @GET("inmuebles/{id}")
        Call<Inmueble> obtenerInmueble(@Header("Authorization") String token, @Path("id") int id);

        @GET("inmuebles/alquiladas")
        Call<List<Inmueble>> alquiladas(@Header("Authorization") String token);

        @GET("inmuebles/contrato/{id}")
        Call<Contrato> obtenerContrato(@Header("Authorization") String token, @Path("id") int id);

        @GET("inmuebles/pagos/{id}")
        Call<List<Pago>> obtenerPagos(@Header("Authorization") String token, @Path("id") int id);

        @GET("inmuebles/inquilinos/{id}")
        Call<Inquilino> obtenerInquilino(@Header("Authorization") String token, @Path("id") int id);

        @PUT("inmuebles/estado/{id}")
        Call<Inmueble> cambiarEstado(@Header("Authorization") String token, @Path("id") int id);
    }
}
