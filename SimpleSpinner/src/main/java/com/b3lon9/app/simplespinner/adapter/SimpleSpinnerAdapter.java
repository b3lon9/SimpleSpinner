package com.b3lon9.app.simplespinner.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.b3lon9.app.simplespinner.R;

public class SimpleSpinnerAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private String[] data;

    public SimpleSpinnerAdapter(Context context) {
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(String[] data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public String getItem(int i) {
        return data[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = layoutInflater.inflate(R.layout.popup_list_item, viewGroup, false);
        }
        ((TextView)view).setText(getItem(i));

        return view;
    }
}
