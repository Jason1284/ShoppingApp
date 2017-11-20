package com.example.shoppingapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jason on 11/13/2017.
 */
public class ViewHolder {
    private TextView textView;
    private CheckBox checkBox;
    public View base;

    public ViewHolder(View base){
        this.base = base;
    }

    public TextView getTextView() {
        return textView;
    }   

    public void setTextView(TextView textView) {
        this.textView = textView;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }

}
