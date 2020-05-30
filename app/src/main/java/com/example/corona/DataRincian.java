package com.example.corona;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.corona.Adapter.ProvinsiAdapter;
import com.example.corona.Model.ModelObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataRincian extends AppCompatActivity {
    RecyclerView list;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_rincian);

        list=findViewById(R.id.listt);
        list.setLayoutManager(new LinearLayoutManager(this));

        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading");
        dialog.setCancelable(false);
        getSupportActionBar().setTitle("#stayathome");
        getDataProvinsi();
    }

    private void getDataProvinsi() {
        dialog.show();
        Call<List<ModelObject>> call = Api.service().getProvinsi();
        call.enqueue(new Callback<List<ModelObject>>() {
            @Override
            public void onResponse(Call<List<ModelObject>> call, Response<List<ModelObject>> response) {
                list.setAdapter(new ProvinsiAdapter(getApplicationContext(),response.body()));
                dialog.cancel();
            }

            @Override
            public void onFailure(Call<List<ModelObject>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage() , Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });
    }
}
