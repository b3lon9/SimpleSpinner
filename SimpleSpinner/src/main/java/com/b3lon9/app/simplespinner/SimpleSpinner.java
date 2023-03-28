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
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.PopupWindow;

import com.b3lon9.app.simplespinner.adapter.SimpleSpinnerAdapter;
import com.b3lon9.app.simplespinner.databinding.SpinnerListBinding;

/**
 * @SimpleSpinner Layout Cycle :
 * OnFinishInflate() > init() > onLayout()
 */
@SuppressLint("AppCompatCustomView")
public class SimpleSpinner extends Button implements View.OnClickListener, AdapterView.OnItemClickListener {
    /**
     * @SimpleSpinnerAdapter adapter
     * */
    private SimpleSpinnerAdapter adapter;

    /**
     * @PopupWindow popupWindow -> name : spinner
     * */
    private PopupWindow spinner;

    /**
     * @ListView Title:TextView, List:ListView
     * */
    private SpinnerListBinding spinnerListBinding;

    /**
     * @Popup present Property
     * */
    private boolean is_spinner_outside_touch = true;

    /**
     * @PopupList Title
     * */
    private String spinner_list_title = "선택하세요";

    /**
     * @PopupList Title IsVisible
     * */
    private boolean is_spinner_list_title_visible;

    /**
     * @PopupList Item Gravity
     * */
    private int spinner_list_item_gravity = -1;

    private int spinner_width;

    private int spinner_height;

    private int spinner_pivot_x;

    private int spinner_pivot_y;

    private int spinner_width_offset;

    private int spinner_height_offset;

    private Drawable spinner_background;

    private int spinner_item_height;

    /**
     * @Constructor : Programmatically
     */
    public SimpleSpinner(Context context) {
        super(context);
    }

    /**
     * @Constructor : XML
     */
    public SimpleSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
        setAttr(attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        init();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        appear();
    }

    @Override
    public void onClick(View view) {
        spinner.showAsDropDown(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        setText(adapter.getItem(position));
        spinner.dismiss();
    }



    private void setAttr(AttributeSet attributeSet) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attributeSet, R.styleable.SimpleSpinner);

        is_spinner_list_title_visible = typedArray.getBoolean(R.styleable.SimpleSpinner_spinner_visibility, false);
        spinner_width = typedArray.getDimensionPixelSize(R.styleable.SimpleSpinner_spinner_width, -1);
        spinner_height = typedArray.getDimensionPixelSize(R.styleable.SimpleSpinner_spinner_height, -1);
        spinner_background = typedArray.getDrawable(R.styleable.SimpleSpinner_spinner_background);

        // item
        spinner_item_height = typedArray.getDimensionPixelSize(R.styleable.SimpleSpinner_spinner_items_height, -1);
    }


    private void init() {
        // View ClickListener
        this.setOnClickListener(this);

        // spinner(popup) init
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        spinner = new PopupWindow(getContext());
        spinner.setOutsideTouchable(is_spinner_outside_touch);
        spinner.setAnimationStyle(R.style.animation_dropdown);

        // spinner layout
        spinnerListBinding = SpinnerListBinding.inflate(layoutInflater);
        spinner.setContentView(spinnerListBinding.getRoot());
        spinnerListBinding.spinnerList.setOnItemClickListener(this);
    }

    private void appear() {
        // spinner list & adapter
        if (adapter == null) {
            adapter = new SimpleSpinnerAdapter(getContext(), getResources().getStringArray(R.array.color_array));
        }
        spinnerListBinding.spinnerList.setAdapter(adapter);

        updateLayout();
    }



    public void updateLayout() {
        // spinner layout size
        int width = spinner_width == -1 ? getWidth() - spinner_width_offset : spinner_width - spinner_width_offset;
        spinner.setWidth(width);

        // list height
        int height = spinner_height == -1 ? getWidth() - spinner_height_offset : spinner_height - spinner_height_offset;
        spinner.setHeight(height);

        // list background
        if (spinner_background != null) spinner.setBackgroundDrawable(spinner_background);

        // list title check
        if (TextUtils.isEmpty(spinnerListBinding.spinnerTitle.getText())) {
            spinnerListBinding.spinnerTitle.setVisibility(GONE);
        }

        // list items height
        int item_height = spinner_item_height == -1 ? getHeight() : spinner_item_height;
        adapter.setItemHeight(item_height);

        // list items line spacing

        // list items gravity
        if (spinner_list_item_gravity == -1) {
            spinner_list_item_gravity = getGravity();
        }
        adapter.setGravity(spinner_list_item_gravity);
    }

    public void setOutsideTouchable(boolean is_popup_outside_touch) {
        this.is_spinner_outside_touch = is_popup_outside_touch;
    }

    public void setPopupListTitle(String popup_list_title) {
        this.spinner_list_title = popup_list_title;
    }

    public void setPopupListTitleVisible(boolean is_popup_list_title_visible) {
        this.is_spinner_list_title_visible = is_popup_list_title_visible;
        spinnerListBinding.spinnerTitle.setVisibility(is_popup_list_title_visible? VISIBLE:GONE);
    }
}