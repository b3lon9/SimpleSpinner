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
package com.b3lon9.app.simplespinner;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.b3lon9.app.simplespinner.adapter.SimpleSpinnerAdapter;

/**
 * @SimpleSpinner Layout Cycle
 * OnFinishInflate() > init() > onLayout()
 */
@SuppressLint("AppCompatCustomView")
public class SimpleSpinner extends Button implements View.OnClickListener, AdapterView.OnItemClickListener {
    private PopupWindow popupWindow;
    private SimpleSpinnerAdapter adapter;

    /**
     * SimpleSpinner present Property
     * */
    private boolean is_popup_outside_touch = true;



    /**
     * Constructor : Programmatically
     */
    public SimpleSpinner(Context context) {
        super(context);
    }

    /**
     * Constructor : XML
     */
    public SimpleSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        init();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        // popup layout size
        popupWindow.setWidth(getWidth());

        // popup list height
        adapter.setItemHeight(getHeight());
    }

    @Override
    public void onClick(View view) {
        popupWindow.showAsDropDown(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        setText(adapter.getItem(position));
        popupWindow.dismiss();
    }

    private void init() {
        // View ClickListener
        this.setOnClickListener(this);

        // measure width, height : because view is not drawing yet
        measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);

        // Popup init
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        popupWindow = new PopupWindow(getContext());
        popupWindow.setOutsideTouchable(is_popup_outside_touch);
        popupWindow.setWidth(getMeasuredWidth());

        // Popup layout
        View popup_view = layoutInflater.inflate(R.layout.popup_list, null, false);
        popupWindow.setContentView(popup_view);

        // Popup list & adapter
        ListView listView = (ListView) popup_view.findViewById(R.id.popup_list);
        adapter = new SimpleSpinnerAdapter(getContext());
        adapter.setData(getResources().getStringArray(R.array.color_array));
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    // popup outside touchable
    public void setOutsideTouchable(boolean is_popup_outside_touch) {
        this.is_popup_outside_touch = is_popup_outside_touch;
    }
}