package com.example.parimalmishra.libros;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by sharaddadhich on 27/11/17.
 */

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.RVViewHolder> {

    ArrayList<Items> theseItems;
    Context context;
    onItemClick onItemClick;
    public RvAdapter(ArrayList<Items> theseItems, Context context,onItemClick onItemClick) {
        this.theseItems = theseItems;
        this.context = context;
        this.onItemClick = onItemClick;
    }

    @Override
    public RVViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rootView = li.inflate(R.layout.resultshow,parent,false);
        return new RVViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(RVViewHolder holder, int position) {
        final Items thisItem = theseItems.get(position);
        holder.tvname.setText(thisItem.getExtract_data());
        holder.tvrating.setText(thisItem.getRating());
        holder.tvprice.setText(thisItem.getPrice());
        holder.tvfrom.setText(thisItem.getFrom());
        holder.thisView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClick.onItemClickListner(thisItem.getUrl());
            }
        });
    }

    @Override
    public int getItemCount() {
        return theseItems.size();
    }

    public void UpdateData(ArrayList<Items> theseItems)
    {
        this.theseItems = theseItems;
        notifyDataSetChanged();
    }

    class RVViewHolder extends RecyclerView.ViewHolder {
        TextView tvname,tvprice,tvrating,tvfrom;
        View thisView;
        public RVViewHolder(View itemView) {
            super(itemView);
            tvname = (TextView) itemView.findViewById(R.id.tv_name);
            tvprice = (TextView) itemView.findViewById(R.id.tv_price);
            tvrating = (TextView) itemView.findViewById(R.id.tv_rating);
            thisView = itemView;
            tvfrom = (TextView) itemView.findViewById(R.id.tv_from);
        }
    }
}
