package com.example.corona;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.corona.Adapter.ProvinsiAdapter;
import com.example.corona.Model.ModelDataGlobalKematian;
import com.example.corona.Model.ModelDataGlobalPositif;
import com.example.corona.Model.ModelDataGlobalSembuh;
import com.example.corona.Model.ModelDataIndonesia;
import com.example.corona.Model.ModelObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity {
    TextView tSembuh,tPositif,tMeninggal;
    Button provinsi;
    ProgressDialog dialog;
    SwipeRefreshLayout swipe_refresh;
    TextView GlobalSembuh, GlobalPositif, GlobalKematian;
    Button country;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        GlobalSembuh=findViewById(R.id.kesembuhanGlobal);
        GlobalPositif=findViewById(R.id.positifGlobal);
        GlobalKematian=findViewById(R.id.kematianGlobal);
        country=findViewById(R.id.negara);
        country.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), DataGlobalRincian.class));
            }
        });

        tSembuh = findViewById(R.id.kesembuhan);
        tPositif = findViewById(R.id.positif);
        tMeninggal = findViewById(R.id.kematian);
        provinsi = findViewById(R.id.provinsi);
        provinsi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), DataRincian.class));
            }
        });
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading");
        dialog.setCancelable(false);
        getSupportActionBar().setTitle("#stayathome");
        dialog.show();
        getData();
        getDataGlobalSembuh();
        getDataGlobalPositif();
        getDataGlobalKematian();
        swipe_refresh=findViewById(R.id.swipe);
        swipe_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Handler untuk menjalankan jeda selama 5 detik
                new Handler().postDelayed(new Runnable() {
                    @Override public void run() {

                        // Berhenti berputar/refreshing
                        swipe_refresh.setRefreshing(false);

                        // fungsi-fungsi lain yang dijalankan saat refresh berhenti
                        dialog.show();
                        getData();
                        getDataGlobalSembuh();
                        getDataGlobalPositif();
                        getDataGlobalKematian();
                    }
                }, 0);
            }
        });

    }

    private void getData() {
        Call<List<ModelDataIndonesia>> call = Api.service().getData();
        call.enqueue(new Callback<List<ModelDataIndonesia>>() {
            @Override
            public void onResponse(Call<List<ModelDataIndonesia>> call, Response<List<ModelDataIndonesia>> response) {
                tSembuh.setText(response.body().get(0).getSembuh());
                tPositif.setText(response.body().get(0).getPositif());
                tMeninggal.setText(response.body().get(0).getMeninggal());
                dialog.cancel();
            }

            @Override
            public void onFailure(Call<List<ModelDataIndonesia>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });
    }
    private void getDataGlobalKematian() {
        Call<ModelDataGlobalKematian> call = Api.service().getGlobalKematian();
        call.enqueue(new Callback<ModelDataGlobalKematian>() {
            @Override
            public void onResponse(Call<ModelDataGlobalKematian> call, Response<ModelDataGlobalKematian> response) {
                GlobalKematian.setText(response.body().getValue());
                dialog.cancel();
            }

            @Override
            public void onFailure(Call<ModelDataGlobalKematian> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(),Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });
    }

    private void getDataGlobalPositif() {
        Call<ModelDataGlobalPositif> call = Api.service().getGlobalPositif();
        call.enqueue(new Callback<ModelDataGlobalPositif>() {
            @Override
            public void onResponse(Call<ModelDataGlobalPositif> call, Response<ModelDataGlobalPositif> response) {
                GlobalPositif.setText(response.body().getValue());
                dialog.cancel();
            }

            @Override
            public void onFailure(Call<ModelDataGlobalPositif> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(),Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });
    }

    private void getDataGlobalSembuh() {
        Call<ModelDataGlobalSembuh> call = Api.service().getGlobalSembuh();
        call.enqueue(new Callback<ModelDataGlobalSembuh>() {
            @Override
            public void onResponse(Call<ModelDataGlobalSembuh> call, Response<ModelDataGlobalSembuh> response) {
                GlobalSembuh.setText(response.body().getValue());
                dialog.cancel();
            }

            @Override
            public void onFailure(Call<ModelDataGlobalSembuh> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(),Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });
    }
}
