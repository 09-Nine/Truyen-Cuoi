package com.example.truyencuoi.adapters;


import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.truyencuoi.model.Category;
import com.example.truyencuoi.R;
import com.example.truyencuoi.listeners.AdapterListener;

import java.util.List;


public class NavAdapter extends RecyclerView.Adapter<NavAdapter.ViewHolder> {

    private List<Category> categories;
    private AdapterListener listener;

    public NavAdapter(List<Category> categories) {
        this.categories = categories;
    }

    public void setListener(AdapterListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category cat = categories.get(position);

        holder.categoryText.setText(cat.getName());
//        Glide.with(holder.itemView.getContext()).load(cat.getImageResourceId()).into(holder.categoryImage);
        String iconPath = "file:///android_asset/" + cat.getIcon();
        Glide.with(holder.itemView.getContext()).load(Uri.parse(iconPath)).into(holder.categoryImage);
        holder.categoryContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null){
                    listener.onClick(cat.getName());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView categoryImage;
        public TextView categoryText;
        public LinearLayout categoryContainer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryImage = itemView.findViewById(R.id.category_img);
            categoryText = itemView.findViewById(R.id.category_text);
            categoryContainer = itemView.findViewById(R.id.category_container);
        }
    }
}
