package com.batdemir.stocks.example.oop.ui.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.batdemir.entity.model.stocks.Stock;
import com.batdemir.stocks.example.oop.R;
import com.batdemir.stocks.example.oop.databinding.ItemMainBinding;
import com.batdemir.stocks.example.oop.helper.GlobalVariable;

import java.util.List;
import java.util.Objects;

public class RecyclerAdapterMain extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_ITEM = 1;

    private Context context;
    private ItemMainBinding binding;
    private ItemListener itemListener;
    private List<Stock> models;

    public RecyclerAdapterMain(Context context, List<Stock> models, ItemListener itemListener) {
        this.context = context;
        this.itemListener = itemListener;
        this.models = models;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemMainBinding.inflate(LayoutInflater.from(context), parent, false);
        if (viewType == TYPE_ITEM)
            return new MyViewHolder(binding);
        throw new UnsupportedOperationException("no matches");
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder)
            ((MyViewHolder) holder).setData(models.get(position), position);
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    @Override
    public int getItemViewType(int position) {
        return TYPE_ITEM;
    }

    public List<Stock> getModels() {
        return models;
    }

    public interface ItemListener {
        void onItemClick(Stock item);
    }

    protected class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Stock item;

        private MyViewHolder(@NonNull ItemMainBinding binding) {
            super(binding.getRoot());
            binding.rootItemMain.setOnClickListener(this);
        }

        private void setData(Stock item, int position) {
            this.item = item;
            if (position % 2 == 0)
                binding.rootItemMain.setBackgroundColor(context.getColor(R.color.softGray2));

            binding.txtEditSymbol.setText(String.format(GlobalVariable.getLocale(), "%s", item.getSymbol()));
            binding.txtEditBid.setText(String.format(GlobalVariable.getLocale(), "%.2f", item.getBid()));
            binding.txtEditDifference.setText(String.format(GlobalVariable.getLocale(), "%.2f", item.getBid()));
            binding.txtEditVolume.setText(String.format(GlobalVariable.getLocale(), "%.2f", item.getBid()));
            binding.txtEditOffer.setText(String.format(GlobalVariable.getLocale(), "%.2f", item.getBid()));
            binding.txtEditPrice.setText(String.format(GlobalVariable.getLocale(), "%.2f", item.getBid()));
            binding.imgStatus.setImageResource(item.isUp() ? R.drawable.ic_up : R.drawable.ic_down);
            binding.imgStatus.setImageTintList(context.getColorStateList(item.isUp() ? R.color.darkGreen : R.color.red));
        }

        @Override
        public void onClick(View v) {
            try {
                if (itemListener == null)
                    itemListener = (ItemListener) context;
                itemListener.onItemClick(item);
            } catch (Exception e) {
                Log.e(RecyclerAdapterMain.class.getSimpleName(), Objects.requireNonNull(e.getMessage()));
            }
        }
    }
}
