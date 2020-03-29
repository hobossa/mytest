package com.hoboss.uicustomviews;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class TitleLayout extends LinearLayout implements View.OnClickListener{
    public TitleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.title, this);
        Button titleBack = (Button)findViewById(R.id.title_back);
        titleBack.setOnClickListener(this);
        Button titleEdit = (Button)findViewById(R.id.title_edit);
        titleEdit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back:
                ((Activity)getContext()).finish();
                break;
            case R.id.title_edit:
                break;
            default:
                break;
        }
    }
}
