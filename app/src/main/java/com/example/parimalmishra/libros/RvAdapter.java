package com.example.parimalmishra.libros;

import android.content.Context;
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

    public RvAdapter(ArrayList<Items> theseItems, Context context) {
        this.theseItems = theseItems;
        this.context = context;
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
        holder.thisView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri.parse(thisItem.getUrl());
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
        TextView tvname,tvprice,tvrating;
        View thisView;
        public RVViewHolder(View itemView) {
            super(itemView);
            tvname = (TextView) itemView.findViewById(R.id.tv_name);
            tvprice = (TextView) itemView.findViewById(R.id.tv_price);
            tvrating = (TextView) itemView.findViewById(R.id.tv_rating);
            thisView = itemView;
        }
    }
}
