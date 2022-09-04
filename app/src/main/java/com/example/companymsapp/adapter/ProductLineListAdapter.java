package com.example.companymsapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.companymsapp.R;
import com.example.companymsapp.model.ProductLine;

import java.util.List;

public class ProductLineListAdapter extends RecyclerView.Adapter<ProductLineListAdapter.ViewHolder> {

    private List<ProductLine> data;
    private ProductLineListAdapter.ItemOnClickListener listener;

    public ProductLineListAdapter(List<ProductLine> data, ItemOnClickListener listener) {
        this.data = data;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_line_row, parent, false);
        return new ProductLineListAdapter.ViewHolder(view);
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

        TextView textView8;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView8 = itemView.findViewById(R.id.textView8);
        }
        public void bind(final ProductLine item, final ItemOnClickListener listener) {
            textView8.setText(""+item.getProductLine());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onClick(item);
                }
            });
        }
    }
    public interface ItemOnClickListener{
        void onClick(ProductLine productLine);
    }
}
