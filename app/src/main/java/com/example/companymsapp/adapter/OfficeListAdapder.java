package com.example.companymsapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.companymsapp.R;
import com.example.companymsapp.model.Office;

import java.util.List;

public class OfficeListAdapder extends RecyclerView.Adapter<OfficeListAdapder.ViewHolder>{

    private List<Office> data;
    private ItemOnClickListener listener;

    public OfficeListAdapder(List<Office> data, OfficeListAdapder.ItemOnClickListener listener){
        this.data = data;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.office_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(data.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView officeCodeTextView, cityTextView, countryTextView, phoneTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            officeCodeTextView = itemView.findViewById(R.id.officeCodeTextView);
            cityTextView = itemView.findViewById(R.id.cityTextView);
            countryTextView = itemView.findViewById(R.id.countryTextView);
            phoneTextView = itemView.findViewById(R.id.phoneTextView);
        }
        public void bind(final Office item, final ItemOnClickListener listener) {
            officeCodeTextView.setText(""+item.getOfficeCode());
            cityTextView.setText(""+item.getCity());
            countryTextView.setText(""+item.getCountry());
            phoneTextView.setText(""+item.getPhone());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onClick(item);
                }
            });
        }
    }

    public interface ItemOnClickListener{
        void onClick(Office office);
    }
}
