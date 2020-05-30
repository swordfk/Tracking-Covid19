package com.example.corona.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.corona.Model.ModelAttributesGlobal;
import com.example.corona.Model.ModelObjectGlobal;
import com.example.corona.R;

import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {
    Context context;
    List<ModelObjectGlobal> objects2;

    public CountryAdapter(Context context, List<ModelObjectGlobal> objects) {
        this.context = context;
        this.objects2 = objects;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_country, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ModelAttributesGlobal attributesGlobal = objects2.get(position).getAttributes();
        holder.tx1.setText(attributesGlobal.getCountry_Region());
        holder.tx2.setText(": "+attributesGlobal.getRecovered());
        holder.tx3.setText(": "+attributesGlobal.getConfirmed());
        holder.tx4.setText(": "+attributesGlobal.getDeaths());
    }

    @Override
    public int getItemCount() {
        return objects2.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tx1, tx2, tx3, tx4;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tx1=itemView.findViewById(R.id.txt1);
            tx2=itemView.findViewById(R.id.txt2);
            tx3=itemView.findViewById(R.id.txt3);
            tx4=itemView.findViewById(R.id.txt4);

        }
    }
}
