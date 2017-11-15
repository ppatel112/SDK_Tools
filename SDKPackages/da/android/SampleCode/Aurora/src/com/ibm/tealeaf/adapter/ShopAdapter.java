/*******************************************************************************
 * Licensed Materials - Property of IBM
 * (C) Copyright IBM Corp. 2015
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 ******************************************************************************/
package com.ibm.tealeaf.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ibm.tealeaf.aurora.AuroraListItem;
import com.ibm.tealeaf.aurora.R;

/**
 * Shop adapter
 * 
 * @author cyang
 * 
 */
public class ShopAdapter extends ArrayAdapter<AuroraListItem> {
    private Context mContext = null;

    public ShopAdapter(Context context, ArrayList<AuroraListItem> itemList) {
        super(context, android.R.layout.simple_list_item_1, itemList);
        mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // If we weren't given a view, inflate one
        ShopViewHolder viewHolderItem = null;

        if (convertView == null) {
            LayoutInflater li = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = li.inflate(R.layout.shop_list_entry, parent, false);

            viewHolderItem = new ShopViewHolder();
            viewHolderItem.mainImageView = (ImageView) convertView.findViewById(R.id.shop_list_item_mainImageView);
            viewHolderItem.titleTextView = (TextView) convertView.findViewById(R.id.shop_list_item_titleTextView);
            viewHolderItem.subtitleTextView = (TextView) convertView.findViewById(R.id.shop_list_item_subtitleTextView);
            convertView.setTag(viewHolderItem);
        } else {
            viewHolderItem = (ShopViewHolder) convertView.getTag();
        }

        AuroraListItem entry = getItem(position);
        viewHolderItem.titleTextView.setText(entry.getTitle());

        if (entry.getThumbnail() != null) {
            viewHolderItem.mainImageView.setImageBitmap(entry.getThumbnail());
        } else {
            viewHolderItem.mainImageView.setVisibility(View.GONE);
        }
        if (entry.getSubtitle() != null) {
            viewHolderItem.subtitleTextView.setText(entry.getSubtitle());
        } else {
            viewHolderItem.subtitleTextView.setVisibility(View.GONE);
        }
        return convertView;
    }

    /** View holder pattern class for performance */
    static class ShopViewHolder {
        ImageView mainImageView;
        TextView titleTextView;
        TextView subtitleTextView;
    }
}
