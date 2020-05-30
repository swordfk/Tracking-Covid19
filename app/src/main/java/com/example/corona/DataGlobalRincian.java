package com.example.corona;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.corona.Adapter.CountryAdapter;
import com.example.corona.Model.ModelObjectGlobal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataGlobalRincian extends AppCompatActivity {
    RecyclerView arraylist;
    ProgressDialog dialog;
    ArrayList<HashMap<String, String>> arusdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_global_rincian);

        arraylist=findViewById(R.id.listtt);
        arraylist.setLayoutManager(new LinearLayoutManager(this));
        arusdata=new ArrayList<>();

        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading");
        dialog.setCancelable(false);
        getSupportActionBar().setTitle("#stayathome");
        getDataCountry();
    }

    private void getDataCountry() {
        dialog.show();
        Call<List<ModelObjectGlobal>> call = Api.service().getCountry();
        call.enqueue(new Callback<List<ModelObjectGlobal>>() {
            @Override
            public void onResponse(Call<List<ModelObjectGlobal>> call, Response<List<ModelObjectGlobal>> response) {
                arraylist.setAdapter(new CountryAdapter(getApplicationContext(), response.body()));
                dialog.cancel();
            }

            @Override
            public void onFailure(Call<List<ModelObjectGlobal>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(),Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });
    }
}
