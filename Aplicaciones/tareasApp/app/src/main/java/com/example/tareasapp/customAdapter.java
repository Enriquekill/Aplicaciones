package com.example.tareasapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

public class customAdapter extends BaseAdapter {
    Context context;
    List<String> chbox;
    MainActivity acti;

    public customAdapter(Context context, List<String> chbox) {
        this.context = context;
        this.chbox = chbox;
    }


    @Override
    public int getCount() {
        return chbox.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        CheckBox chb;
        String aux = chbox.get(i);
        if(view == null)
            view = LayoutInflater.from(context).inflate(R.layout.listview_check,null);

        chb = view.findViewById(R.id.checkBox);
        chb.setText(aux);
        return view;
    }


}
