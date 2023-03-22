package com.b3lon9.app.simplespinner.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.b3lon9.app.simplespinner.R;

public class SimpleSpinnerAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private String[] data;

    private int item_height;
    private int gravity = Gravity.CENTER;

    public SimpleSpinnerAdapter(Context context) {
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
            ((TextView)view).setText(getItem(i));
            ((TextView)view).setGravity(gravity);
            ((TextView)view).setHeight(item_height);
        }

        return view;
    }


    /**
     * @param data adapter item list
     */
    public void setData(String[] data) {
        this.data = data;
    }

    /**
     * @param height list item height
     */
    public void setItemHeight(int height) {
        this.item_height = height;
    }

    /**
     * @param gravity list item gravity
     */
    public void setGravity(int gravity) {
        this.gravity = gravity;
    }

}
