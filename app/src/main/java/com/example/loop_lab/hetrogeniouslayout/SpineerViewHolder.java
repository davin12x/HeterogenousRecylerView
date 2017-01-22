package com.example.loop_lab.hetrogeniouslayout;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SpineerViewHolder extends RecyclerView.ViewHolder {
    TextView textView;
    Spinner spinner;

    public SpineerViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        textView = (TextView)itemView.findViewById(R.id.textView);
        spinner = (Spinner)itemView.findViewById(R.id.spinner);
    }
}
