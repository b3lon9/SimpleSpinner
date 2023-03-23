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
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.PopupWindow;

import com.b3lon9.app.simplespinner.adapter.SimpleSpinnerAdapter;
import com.b3lon9.app.simplespinner.databinding.PopupListBinding;

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
     * @PopupWindow popupWindow
     * */
    private PopupWindow popupWindow;

    /**
     * @ListView Title:TextView, List:ListView
     * */
    private PopupListBinding popupListBinding;

    /**
     * @Popup present Property
     * */
    private boolean is_popup_outside_touch = true;

    /**
     * @PopupList Title
     * */
    private String popup_list_title = "선택하세요";

    /**
     * @PopupList Title IsVisible
     * */
    private boolean is_popup_list_title_visible = false;

    /**
     * @PopupList Item Gravity
     * */
    private int popup_list_item_gravity = -1;



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

        // Popup init
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        popupWindow = new PopupWindow(getContext());
        popupWindow.setOutsideTouchable(is_popup_outside_touch);
        popupWindow.setAnimationStyle(R.style.animation_dropdown);

        // Popup layout
        popupListBinding = PopupListBinding.inflate(layoutInflater);
        popupWindow.setContentView(popupListBinding.getRoot());
        popupListBinding.popupList.setOnItemClickListener(this);
    }

    private void appear() {
        // Popup list & adapter
        if (adapter == null) {
            adapter = new SimpleSpinnerAdapter(getContext(), getResources().getStringArray(R.array.color_array));
        }
        popupListBinding.popupList.setAdapter(adapter);

        // popup gravity
        if (popup_list_item_gravity == -1) {
            popup_list_item_gravity = getGravity();
        }
        adapter.setGravity(popup_list_item_gravity);

        updateLayout();
    }



    public void updateLayout() {
        // popup layout size
        popupWindow.setWidth(getWidth());

        // popup list height
        adapter.setItemHeight(getHeight());

        // popup list title check
        if (TextUtils.isEmpty(popupListBinding.popupTitle.getText())) {
            popupListBinding.popupTitle.setVisibility(GONE);
        }
    }

    public void setOutsideTouchable(boolean is_popup_outside_touch) {
        this.is_popup_outside_touch = is_popup_outside_touch;
    }

    public void setPopupListTitle(String popup_list_title) {
        this.popup_list_title = popup_list_title;
    }

    public void setPopupListTitleVisible(boolean is_popup_list_title_visible) {
        this.is_popup_list_title_visible = is_popup_list_title_visible;
        popupListBinding.popupTitle.setVisibility(is_popup_list_title_visible? VISIBLE:GONE);
    }
}