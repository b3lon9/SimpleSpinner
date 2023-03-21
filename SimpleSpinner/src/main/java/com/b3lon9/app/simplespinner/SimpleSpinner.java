package com.b3lon9.app.simplespinner;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.b3lon9.app.simplespinner.adapter.SimpleSpinnerAdapter;

@SuppressLint("AppCompatCustomView")
public class SimpleSpinner extends Button implements View.OnClickListener {
    private PopupWindow popupWindow;

    /**
     * Constructor : Programmatically
     * */
    public SimpleSpinner(Context context) {
        super(context);
    }

    /**
     * Constructor : XML
     * */
    public SimpleSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        this.setOnClickListener(this);

        LayoutInflater layoutInflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        popupWindow = new PopupWindow(getContext());
        View view = layoutInflater.inflate(R.layout.popup_list, null, false);
        ListView listView = (ListView)view.findViewById(R.id.popup_list);

        SimpleSpinnerAdapter adapter = new SimpleSpinnerAdapter(getContext());
        adapter.setData(getResources().getStringArray(R.array.color_array));
        listView.setAdapter(adapter);

        popupWindow.setContentView(view);
    }

    @Override
    public void onClick(View view) {
        show();
    }

    public void show() {
        popupWindow.showAsDropDown(this);
    }
}
