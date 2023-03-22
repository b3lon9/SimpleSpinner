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
