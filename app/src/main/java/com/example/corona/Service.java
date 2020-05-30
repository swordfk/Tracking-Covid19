package com.example.corona;


import com.example.corona.Model.ModelDataGlobalKematian;
import com.example.corona.Model.ModelDataGlobalPositif;
import com.example.corona.Model.ModelDataGlobalSembuh;
import com.example.corona.Model.ModelDataIndonesia;
import com.example.corona.Model.ModelObject;
import com.example.corona.Model.ModelObjectGlobal;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Service {
    @GET("sembuh")
    Call<ModelDataGlobalSembuh> getGlobalSembuh();

    @GET("positif")
    Call<ModelDataGlobalPositif> getGlobalPositif();

    @GET("meninggal")
    Call<ModelDataGlobalKematian> getGlobalKematian();

    @GET("indonesia")
    Call<List<ModelDataIndonesia>> getData();

    @GET("indonesia/provinsi")
    Call<List<ModelObject>> getProvinsi();

    @GET(" ")
    Call<List<ModelObjectGlobal>> getCountry();
}
