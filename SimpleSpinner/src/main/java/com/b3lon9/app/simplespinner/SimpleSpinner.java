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
import android.widget.BaseAdapter;
import android.widget.PopupWindow;

import androidx.appcompat.widget.AppCompatButton;

import com.b3lon9.app.simplespinner.adapter.SimpleSpinnerAdapter;
import com.b3lon9.app.simplespinner.databinding.SpinnerListBinding;
import com.b3lon9.app.simplespinner.util.Debug;

/**
 * Spinner means PopupWindow
 * you will read to under contents all to Spinner(PopupWindow)
 * I felt uncomfortable origin Spinner ViewGroup of Android Framework
 * so I decide to make useful easy Custom Spinner, which used to everyone
 * */
/**
 * @SimpleSpinner Layout Cycle :
 * OnFinishInflate() > init() > onLayout()
 */
@SuppressLint("AppCompatCustomView")
public class SimpleSpinner extends AppCompatButton implements View.OnClickListener, AdapterView.OnItemClickListener {

    /**
     * @PopupWindow popupWindow -> name : spinner
     * */
    private PopupWindow spinner;

    /**
     * @ListView Title:TextView, List:ListView
     * */
    private SpinnerListBinding spinnerListBinding;

    /**
     * @SimpleSpinnerAdapter simpleAdapter
     * */
    private SimpleSpinnerAdapter simpleSpinnerAdapter;

    /**
     * @BaseAdapter baseAdapter
     * */
    private BaseAdapter baseAdapter;

    /**
     * @Array String[] array
     * */
    private CharSequence[] arrayData;

    /*********************************************************************************************
     * Spinner MainViewGroup Properties under the this line
     * (current View is Button)
     * *******************************************************************************************/
    /**
     * @View arrow vector Image Visible
     * */
    private boolean is_arrow_visible = false;

    /**
     * @View Click arrow Up Drawable Image
     * */
    private Drawable background_up_image;

    /**
     * @View Spinner Dismiss arrow Down Drawable Image
     * */
    private Drawable background_down_image;



    /*********************************************************************************************
     * Spinner Layout Properties under the this line
     * (current View is PopupWindow)
     * *******************************************************************************************/
    /**
     * @Popup present Property
     * */
    private boolean is_spinner_outside_touch = true;

    /**
     * @Popup Width
     * */
    private int spinner_width;

    /**
     * @Popup Height
     * */
    private int spinner_height;

    private int spinner_pivot_x;

    private int spinner_pivot_y;

    private int spinner_width_offset;

    private int spinner_height_offset;

    private Drawable spinner_background;

    private Drawable spinner_background_color;

    /**
     * @PopupList Item Gravity
     * */
    private int spinner_item_gravity = -1;

    /**
     * @PopupList Item Height
     * */
    private int spinner_item_height;

    /**
     * @PopupList Item Padding
     * */
    private int spinner_item_padding_left;
    private int spinner_item_padding_top;
    private int spinner_item_padding_right;
    private int spinner_item_padding_bottom;

    private int spinner_item_text_color;
    private float spinner_item_text_size;

    /**
     * @PopupList Title
     * */
    private String spinner_item_title = "선택하세요";

    /**
     * @PopupList Title IsVisible
     * */
    private boolean is_spinner_item_title_visible;



    /*********************************************************************************************
     * SimpleSpinner Constructor
     * Programmatically & AttributeSet
     *
     * SimpleSpinner Override Method, Interface
     * [ViewGroup]
     * - onFinishInflate
     * - onLayout
     *
     * [OnClickListener]
     * - onClick
     *
     * [OnItemClickListener]
     * - onItemClick
     *
     * [OnDismissListener]
     * - onDismiss
     * *******************************************************************************************/
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
        Debug.Write("..................onLayout...........!!!");
        appear();
    }

    @Override
    public void onClick(View v) {
        spinner.showAsDropDown(this);
        setBackground(background_up_image);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (simpleSpinnerAdapter != null) {
            setText(simpleSpinnerAdapter.getItem(position));
        } else if (baseAdapter != null) {
            setText((CharSequence) baseAdapter.getItem(position));
        }
        spinner.dismiss();
    }

    private PopupWindow.OnDismissListener onDismissListener = new PopupWindow.OnDismissListener() {
        @Override
        public void onDismiss() {
            setBackground(background_down_image);
        }
    };


    /*********************************************************************************************
     * SimpleSpinner Framework inside Method
     * @setAttr settings properties from XML AttributeSet
     * @init settings eventListener, create Instances, binding popup layout
     * @appear combine adapter, update Layout
     * *******************************************************************************************/
    private void setAttr(AttributeSet attributeSet) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attributeSet, R.styleable.SimpleSpinner);

        background_down_image = typedArray.getDrawable(R.styleable.SimpleSpinner_background_down_drawable);
        background_up_image = typedArray.getDrawable(R.styleable.SimpleSpinner_background_up_drawable);

        is_spinner_item_title_visible = typedArray.getBoolean(R.styleable.SimpleSpinner_spinner_visibility, false);
        spinner_width = typedArray.getDimensionPixelSize(R.styleable.SimpleSpinner_spinner_width, -1);
        spinner_height = typedArray.getDimensionPixelSize(R.styleable.SimpleSpinner_spinner_height, -1);
        spinner_background = typedArray.getDrawable(R.styleable.SimpleSpinner_spinner_background);
        spinner_background_color = typedArray.getDrawable(R.styleable.SimpleSpinner_spinner_background_color);

        // item
        spinner_item_text_size = typedArray.getDimension(R.styleable.SimpleSpinner_spinner_items_text_size, getTextSize());
        spinner_item_text_color = typedArray.getColor(R.styleable.SimpleSpinner_spinner_items_text_color, getCurrentTextColor());
        spinner_item_height = typedArray.getDimensionPixelSize(R.styleable.SimpleSpinner_spinner_items_height, -1);
        spinner_item_gravity = typedArray.getInt(R.styleable.SimpleSpinner_spinner_items_gravity, -1);

        spinner_item_padding_left = typedArray.getDimensionPixelSize(R.styleable.SimpleSpinner_spinner_items_padding_left, 0);
        spinner_item_padding_top = typedArray.getDimensionPixelSize(R.styleable.SimpleSpinner_spinner_items_padding_top, 0);
        spinner_item_padding_right = typedArray.getDimensionPixelSize(R.styleable.SimpleSpinner_spinner_items_padding_right, 0);
        spinner_item_padding_bottom = typedArray.getDimensionPixelSize(R.styleable.SimpleSpinner_spinner_items_padding_bottom, 0);


        arrayData = typedArray.getTextArray(R.styleable.SimpleSpinner_spinner_entries);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void init() {
        setOnClickListener(this::onClick);

        background_down_image = background_up_image == null ? getResources().getDrawable(R.drawable.spinner_background_default_down) : background_down_image;
        background_up_image = background_up_image == null ? getResources().getDrawable(R.drawable.spinner_background_default_up) : background_up_image;
        setBackground(background_down_image);

        // spinner(popup) init
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        spinner = new PopupWindow(getContext());
        spinner.setOutsideTouchable(is_spinner_outside_touch);
        spinner.setAnimationStyle(R.style.animation_dropdown);

        // spinner layout
        spinnerListBinding = SpinnerListBinding.inflate(layoutInflater);
        spinner.setContentView(spinnerListBinding.getRoot());
        spinnerListBinding.spinnerList.setOnItemClickListener(this::onItemClick);

        // spinner dismiss
        spinner.setOnDismissListener(onDismissListener);
    }

    private void appear() {
        // spinner list & adapter
        if (simpleSpinnerAdapter == null) {
            simpleSpinnerAdapter = new SimpleSpinnerAdapter(getContext(), getResources().getStringArray(R.array.color_array));
            spinnerListBinding.spinnerList.setAdapter(simpleSpinnerAdapter);
            if (arrayData != null) {
                simpleSpinnerAdapter.setData(arrayData);
            }
        }

        updateLayout();
    }



    /*********************************************************************************************
     * SimpleSpinner Framework public Methods
     * same structure of AttributeSet(styleable-)
     *
     * Programmatically SimpleSpinner state setting Methods
     * *******************************************************************************************/
    public void updateLayout() {
        // list background
        if (spinner_background != null) {
            spinner.setBackgroundDrawable(spinner_background);
        } else if (spinner_background_color != null) {
            spinnerListBinding.spinnerBase.setBackground(spinner_background_color);
        }

        // spinner layout size
        int width = spinner_width == -1 ? getWidth() - spinner_width_offset : spinner_width - spinner_width_offset;
        spinner.setWidth(width);

        if (spinner_height != -1) {
            //int itemHeight = spinner_item_height * arrayData.length;
            spinner.setHeight(spinner_height - spinner_height_offset);
        }

        // list title check
        if (TextUtils.isEmpty(spinnerListBinding.spinnerTitle.getText())) {
            spinnerListBinding.spinnerTitle.setVisibility(GONE);
        }

        // list items height
        int item_height = spinner_item_height == -1 ? getHeight() : spinner_item_height;

        // list items line spacing

        // list items gravity
        if (spinner_item_gravity == -1) {
            spinner_item_gravity = getGravity();
        }

        // SimpleSpinnerAdapter init
        if (simpleSpinnerAdapter != null) {
            simpleSpinnerAdapter.setItemHeight(item_height);
            simpleSpinnerAdapter.setGravity(spinner_item_gravity);
            simpleSpinnerAdapter.setPadding(
                    spinner_item_padding_left,
                    spinner_item_padding_top,
                    spinner_item_padding_right,
                    spinner_item_padding_bottom);
            simpleSpinnerAdapter.setTextSize(spinner_item_text_size);
            simpleSpinnerAdapter.setTextColor(spinner_item_text_color);
        }
    }
    
    public void setAdapter(CharSequence[] array) {
        this.arrayData = array;
    }

    public void setAdapter(SimpleSpinnerAdapter adapter) {
        this.simpleSpinnerAdapter = adapter;
    }

    public void setAdapter(BaseAdapter adapter) {
        this.baseAdapter = adapter;
    }

    public void setSpinnerHeight(int height) {
        this.spinner_height = height;
    }



    public void setSpinnerItemHeight(int height) {
        this.spinner_item_height = height;
    }

    public void setSpinnerItemGravity(int gravity) {
        this.spinner_item_gravity = gravity;
    }

    public void setSpinnerItemPadding(int left, int top, int right, int bottom) {
        this.spinner_item_padding_left = left;
        this.spinner_item_padding_top = top;
        this.spinner_item_padding_right = right;
        this.spinner_item_padding_bottom = bottom;
    }

    public void setSpinnerItemTextSize(float size) {
        this.spinner_item_text_size = size;
    }

    public void setSpinnerItemTextColor(int color) {
        this.spinner_item_text_color = color;
    }

    public void setOutsideTouchable(boolean is_spinner_outside_touch) {
        this.is_spinner_outside_touch = is_spinner_outside_touch;
    }

    public void setSpinnerListTitle(String spinner_list_title) {
        this.spinner_item_title = spinner_list_title;
    }

    public void setSpinnerListTitleVisible(boolean is_spinner_list_title_visible) {
        this.is_spinner_item_title_visible = is_spinner_list_title_visible;
        spinnerListBinding.spinnerTitle.setVisibility(is_spinner_list_title_visible? VISIBLE:GONE);
    }
}