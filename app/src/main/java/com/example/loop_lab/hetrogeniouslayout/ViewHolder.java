package com.example.loop_lab.hetrogeniouslayout;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;


public class ViewHolder extends RecyclerView.ViewHolder {
     TextView textView1;
    TextView textView2;
    TextView textView3;

    public ViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        textView1 = (TextView)itemView.findViewById(R.id.textView1);
        textView2 = (TextView)itemView.findViewById(R.id.textView2);
        textView3 = (TextView)itemView.findViewById(R.id.textView3);
    }
}
