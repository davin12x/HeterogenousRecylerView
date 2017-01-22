package com.example.loop_lab.hetrogeniouslayout;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import java.util.ArrayList;

public class ComplexAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int SPINNER = 0, TEXT_VIEW = 1;
    private ArrayList<Integer> arrayList = new ArrayList<>();
    private Context context;

    public ComplexAdapter(ArrayList<Integer> arrayList) {
        this.arrayList = arrayList;
    }

    public void addNewView(int viewPosition) {
        arrayList.add(viewPosition);
        notifyItemInserted(arrayList.size() + 1);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        context = parent.getContext();
        switch (viewType) {
            case SPINNER:
                View v1 = inflater.inflate(R.layout.spinner_card, parent, false);
                viewHolder = new SpineerViewHolder(v1);
                break;
            case TEXT_VIEW:
                View v2 = inflater.inflate(R.layout.text_card, parent, false);
                viewHolder = new ViewHolder(v2);
                break;
            default:
                View v3 = inflater.inflate(R.layout.text_card, parent, false);
                viewHolder = new ViewHolder(v3);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        switch (viewHolder.getItemViewType()) {
            case SPINNER:
                final SpineerViewHolder vh1 = (SpineerViewHolder) viewHolder;
                String[] items = new String[]{"1", "2", "three"};
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, items);
                vh1.spinner.setAdapter(adapter);
                vh1.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        configureViewHolder1(vh1,position);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                configureViewHolder1(vh1,position);
                break;
            case TEXT_VIEW:
                ViewHolder vh2 = (ViewHolder) viewHolder;
                configureViewHolder2(vh2,position);
                break;
            default:
                SpineerViewHolder vh3 = (SpineerViewHolder) viewHolder;
                configureViewHolder1(vh3,position);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (arrayList.get(position) == 0) {
            return SPINNER;
        } else if (arrayList.get(position) == 1) {
            return TEXT_VIEW;
        }
        return -1;
    }

    private void configureViewHolder1(SpineerViewHolder vh1, int positions) {
        String position = String.valueOf(positions);
        vh1.textView.setText(position);
    }

    public void configureViewHolder2(ViewHolder viewHolder,int positions) {
        String position = String.valueOf(positions);
        viewHolder. textView1.setText(position);
        viewHolder. textView2.setText(position);
        viewHolder.textView3.setText(position);

    }

}
