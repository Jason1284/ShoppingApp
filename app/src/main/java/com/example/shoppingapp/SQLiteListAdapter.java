package com.example.shoppingapp;

/**
 * Created by SG0216351 on 11/21/2017.
 * https://www.android-examples.com/display-multiple-data-from-sqlite-database-inside-listview/
 */

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SQLiteListAdapter extends BaseAdapter {

    Context context;
    ArrayList<String> UserID;
    ArrayList<String> UserName;
    ArrayList<String> UserQuantity;
    ArrayList<String> UserPrice;
    ArrayList<String> UserValue;


    public SQLiteListAdapter(
            Context context2,
            ArrayList<String> id,
            ArrayList<String> name,
            ArrayList<String> quantity,
            ArrayList<String> price,
            ArrayList<String> value

    )
    {

        this.context = context2;
        this.UserID = id;
        this.UserName = name;
        this.UserQuantity = quantity;
        this.UserPrice = price;
        this.UserValue = value;
    }

    public int getCount() {
        // TODO Auto-generated method stub
        return UserID.size();
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    public View getView(int position, View child, ViewGroup parent) {

        Holder holder;

        LayoutInflater layoutInflater;

        if (child == null) {
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            child = layoutInflater.inflate(R.layout.listviewdatalayout, null);

            holder = new Holder();

            holder.textviewid = (TextView) child.findViewById(R.id.textViewID);
            holder.textviewname = (TextView) child.findViewById(R.id.textViewNAME);
            holder.textviewquantity = (TextView) child.findViewById(R.id.textViewQUANTITY);
            holder.textviewprice = (TextView) child.findViewById(R.id.textViewPRICE);
            holder.textviewvalue = (TextView) child.findViewById(R.id.textViewVALUE);

            child.setTag(holder);

        } else {

            holder = (Holder) child.getTag();
        }
        holder.textviewid.setText(UserID.get(position));
        holder.textviewname.setText(UserName.get(position));
        holder.textviewquantity.setText(UserQuantity.get(position));
        holder.textviewprice.setText(UserPrice.get(position));
        holder.textviewvalue.setText(UserValue.get(position));

        return child;
    }

    public class Holder {
        TextView textviewid;
        TextView textviewname;
        TextView textviewquantity;
        TextView textviewprice;
        TextView textviewvalue;
    }

}
