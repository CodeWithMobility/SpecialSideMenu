package com.mobiledev.residedrawer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mobiledev.residedrawer.model.MenuModel;

import java.util.Collections;
import java.util.List;

/**
 * Created by Manu on 11/12/2017.
 */

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MyViewHolder> {
    List<MenuModel> data = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;
    OnItemClickListsner onItemClickListsner;

    public MenuAdapter(Context context, List<MenuModel> data, OnItemClickListsner onItemClickListsner) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
        this.onItemClickListsner = onItemClickListsner;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.menu_drawer_row, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        MenuModel current = data.get(position);
        holder.title.setText(current.getTitle());
        holder.icon.setImageResource(current.getIcon());
        holder.row_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListsner.clickListener(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface OnItemClickListsner{
        void clickListener(int position);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView icon;
        LinearLayout row_layout;


        public MyViewHolder(View itemView) {
            super(itemView);
            title =  itemView.findViewById(R.id.title);
            icon =  itemView.findViewById(R.id.iconView);
            row_layout = itemView.findViewById(R.id.menu_row);

        }
    }
}
