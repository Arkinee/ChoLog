package com.makeus.Modu.src.product.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.makeus.Modu.R;
import com.makeus.Modu.src.product.model.ProductItem;

import java.util.ArrayList;
import java.util.List;


public class ProductAdapter extends ArrayAdapter<ProductItem> {

    private Context mContext;
    private List<ProductItem> mProductList;
    private List<ProductItem> mProductAll;
    private int mResourceId;
//    private LayoutInflater mInflater;


    public ProductAdapter(Context context, int resource, List<ProductItem> departments) {
        super(context, resource, departments);
        this.mContext = context;
        this.mResourceId = resource;
        this.mProductList = new ArrayList<>(departments);
        this.mProductAll = new ArrayList<>(departments);
    }

    public int getCount() {
        return mProductList.size();
    }

    public ProductItem getItem(int position) {
        return mProductList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        try {
            if (convertView == null) {
                LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
                convertView = inflater.inflate(mResourceId, parent, false);
            }
            ProductItem productItem = getItem(position);
            TextView textView = convertView.findViewById(R.id.tv_item_product_service_name);
            ImageView imageView = convertView.findViewById(R.id.iv_item_product_image);

            assert productItem != null;
            textView.setText(productItem.getmBrand());
//            Log.d("로그", "adapter: " + productItem.getmImageUrl());

            String imageUrl = productItem.getmImageUrl().replace("drive.google.com/open", "docs.google.com/uc");
            Glide.with(mContext).load(imageUrl).placeholder(R.drawable.ic_adobe_cloud).override(200, 200).into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return convertView;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            public String convertResultToString(Object resultValue) {
                return ((ProductItem) resultValue).getmBrand();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                List<ProductItem> departmentsSuggestion = new ArrayList<>();
                if (constraint != null) {
                    for (ProductItem department : mProductAll) {
                        if (department.getmBrand().toLowerCase().startsWith(constraint.toString().toLowerCase())) {
                            departmentsSuggestion.add(department);
                        }
                    }
                    filterResults.values = departmentsSuggestion;
                    filterResults.count = departmentsSuggestion.size();
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mProductList.clear();
                if (results != null && results.count > 0) {
                    // avoids unchecked cast warning when using mDepartments.addAll((ArrayList<Department>) results.values);
                    for (Object object : (List<?>) results.values) {
                        if (object instanceof ProductItem) {
                            mProductList.add((ProductItem) object);
                        }
                    }
                    notifyDataSetChanged();
                } else if (constraint == null) {
                    // no filter, add entire original list back in
                    mProductList.addAll(mProductAll);
                    notifyDataSetInvalidated();
                }
            }
        };
    }


}
