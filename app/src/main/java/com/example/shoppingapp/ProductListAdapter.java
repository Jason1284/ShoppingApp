package com.example.shoppingapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;



/**
 * Created by Jason on 11/29/2017.
 * Adapted from: http://www.learn-android.com/2011/11/22/lots-of-lists-custom-adapter/
 */

public final class ProductListAdapter extends ArrayAdapter<ProductList> {

    private final int productListLayoutResource;

    public ProductListAdapter(final Context context, final int newsItemLayoutResource) {
        super(context, 0);
        this.productListLayoutResource = newsItemLayoutResource;
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {

        // We need to get the best view (re-used if possible) and then
        // retrieve its corresponding ViewHolder, which optimizes lookup efficiency
        final View view = getWorkingView(convertView);
        final ViewHolder viewHolder = getViewHolder(view);
        final ProductList entry = getItem(position);

        // Setting the title view is straightforward
        viewHolder.priceTextView.setText(entry.getPrice());

        viewHolder.productTextView.setText(entry.getProduct());

        // Setting image view is also simple
        viewHolder.quantityTextView.setText(entry.getQuantity());

        viewHolder.aisleTextView.setText(entry.getAisle());

        viewHolder.checkBox1.setChecked(entry.getValue());


        return view;
    }

    private View getWorkingView(final View convertView) {
        // The workingView is basically just the convertView re-used if possible
        // or inflated new if not possible
        View workingView = null;

        if(null == convertView) {
            final Context context = getContext();
            final LayoutInflater inflater = (LayoutInflater)context.getSystemService
                    (Context.LAYOUT_INFLATER_SERVICE);

            workingView = inflater.inflate(productListLayoutResource, null);
        } else {
            workingView = convertView;
        }

        return workingView;
    }

    private ViewHolder getViewHolder(final View workingView) {
        // The viewHolder allows us to avoid re-looking up view references
        // Since views are recycled, these references will never change
        final Object tag = workingView.getTag();
        ViewHolder viewHolder = null;


        if(null == tag || !(tag instanceof ViewHolder)) {
            viewHolder = new ViewHolder();

            viewHolder.priceTextView = (TextView) workingView.findViewById(R.id.textViewPrice);
            viewHolder.productTextView = (TextView) workingView.findViewById(R.id.textViewProduct);
            viewHolder.quantityTextView = (TextView) workingView.findViewById(R.id.textViewQuantity);
            viewHolder.aisleTextView = (TextView) workingView.findViewById(R.id.textViewAisle);
            viewHolder.checkBox1 = (CheckBox) workingView.findViewById(R.id.checkBox1);

            workingView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) tag;
        }

        return viewHolder;
    }

    private static class ViewHolder {
        public TextView priceTextView;
        public TextView productTextView;
        public TextView quantityTextView;
        public TextView aisleTextView;
        public CheckBox checkBox1;
    }
}