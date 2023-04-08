/*
 * Copyright 2023 Neander
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.b3lon9.app.simplespinner.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.b3lon9.app.simplespinner.R;

public class SimpleSpinnerAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private CharSequence[] data;

    private int item_height;
    private int gravity;

    private int padding_left;
    private int padding_top;
    private int padding_right;
    private int padding_bottom;

    private int textColor;
    private float textSize;
    private Drawable textBackground;

    public SimpleSpinnerAdapter(Context context) {
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public SimpleSpinnerAdapter(Context context, CharSequence[] data) {
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public String getItem(int i) {
        return data[i].toString();
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = layoutInflater.inflate(R.layout.spinner_list_item, viewGroup, false);
        }

        TextView textView = view.findViewById(R.id.item);
        textView.setText(getItem(i));
        textView.setGravity(gravity);
        textView.setHeight(item_height);
        textView.setPadding(padding_left, padding_top, padding_right, padding_bottom);
        textView.setTextSize(textSize);
        textView.setTextColor(textColor);
        if (textBackground != null) textView.setBackground(textBackground);

        return view;
    }


    /**
     * @param data adapter item list
     */
    public void setData(CharSequence[] data) {
        this.data = data;
        notifyDataSetChanged();
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

    /**
     * @padding padding TextView Item
     * */
    public void setPadding(int left, int top, int right, int bottom) {
        this.padding_left = left;
        this.padding_top = top;
        this.padding_right = right;
        this.padding_bottom = bottom;
    }

    public void setTextColor(int color) {
        this.textColor = color;
    }

    public void setTextSize(float size) {
        this.textSize = size;
    }

    public void setTextBackground(Drawable background) {
        this.textBackground = background;
    }
}
