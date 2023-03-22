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

@SuppressLint("AppCompatCustomView")
public class SimpleSpinner extends Button implements View.OnClickListener {
    private Button main;
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
        main = this;
        this.setOnClickListener(this);

        LayoutInflater layoutInflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        popupWindow = new PopupWindow(getContext());
        View popup_view = layoutInflater.inflate(R.layout.popup_list, null, false);
        ListView listView = (ListView)popup_view.findViewById(R.id.popup_list);

        SimpleSpinnerAdapter adapter = new SimpleSpinnerAdapter(getContext());
        adapter.setData(getResources().getStringArray(R.array.color_array));
        listView.setAdapter(adapter);
        popupWindow.setContentView(popup_view);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                main.setText(adapter.getItem(position));
            }
        });
    }

    @Override
    public void onClick(View view) {
        show();
    }

    public void show() {
        popupWindow.showAsDropDown(this);
    }

}
