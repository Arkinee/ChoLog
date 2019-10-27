package com.makeus.ChoLog.src.home.adapter;

import android.content.Context;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.makeus.ChoLog.R;
import com.makeus.ChoLog.src.home.HomeFragment;
import com.makeus.ChoLog.src.home.models.HomeItem;

import static com.makeus.ChoLog.src.ApplicationClass.myFormatter;

import java.util.ArrayList;


public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    Context mContext;
    private ArrayList<HomeItem> mHomeList;
    private HomeFragment mHomeFragment;
    private OnItemClickListener mListener = null;

    public interface OnItemClickListener {
        public void onChangeClick(View v, int pos);
        public void onCancelClick(View v, int pos);
        public void onSettingClick(View v, int pos);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvHomeDate;
        TextView tvHomeDDay;
        TextView tvHomePrice;
        ImageView ivHomeAlarm;
        ImageView ivHomeImage;
        TextView tvHomeCategory;
        TextView tvHomeBrand;

        TextView tvHomeChange;
        TextView tvHomeCancel;
        TextView tvHomeSetting;

        ViewHolder(View itemView) {
            super(itemView);
            // 뷰 객체에 대한 참조. (hold strong reference)
            tvHomeDate = itemView.findViewById(R.id.tv_item_home_date);
            tvHomeDDay = itemView.findViewById(R.id.tv_item_home_dday);
            tvHomePrice = itemView.findViewById(R.id.tv_item_home_price);
            ivHomeAlarm = itemView.findViewById(R.id.iv_item_home_alarm);
            ivHomeImage = itemView.findViewById(R.id.iv_item_home_image);
            tvHomeCategory = itemView.findViewById(R.id.tv_item_home_category);
            tvHomeBrand = itemView.findViewById(R.id.tv_item_home_brand);

            tvHomeChange = itemView.findViewById(R.id.tv_item_home_change);
            tvHomeCancel = itemView.findViewById(R.id.tv_item_home_cancel);
            tvHomeSetting = itemView.findViewById(R.id.tv_item_home_setting);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    int pos = getAdapterPosition();
//                    if (pos != RecyclerView.NO_POSITION) {
//                        if (mListener != null) {
//                            mListener.onItemClick(view, pos);
//                        }
//                    }
//                }
//            });

        }
    }

    // 생성자에서 데이터 리스트 객체를 전달받음.
    public HomeAdapter(Context mContext, ArrayList<HomeItem> mHomeList, OnItemClickListener listener) {
        this.mContext = mContext;
        this.mHomeList = mHomeList;
        this.mListener = listener;
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.item_home, parent, false);
        ViewHolder vh = new ViewHolder(view);

        return vh;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final HomeItem homeItem = mHomeList.get(position);
//        holder.tvHomeDate.setText(homeItem.getmDate());
        holder.tvHomeDDay.setText(mContext.getResources().getString(R.string.tv_main_d_day).concat(String.valueOf(homeItem.getmDDay())));
        holder.tvHomePrice.setText(myFormatter.format(homeItem.getmPrice()).concat(mContext.getResources().getString(R.string.tv_main_won)));
        Glide.with(mContext).load(homeItem.getmImageUrl()).placeholder(R.drawable.ic_melon).override(200, 200).into(holder.ivHomeImage);
        holder.tvHomeCategory.setText(homeItem.getmCategory());
        holder.tvHomeBrand.setText(homeItem.getmBrand());

        holder.tvHomeChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onChangeClick(view, position);
            }
        });

        holder.tvHomeCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onCancelClick(view, position);
            }
        });

        holder.tvHomeSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onSettingClick(view, position);
            }
        });

    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return mHomeList.size();
    }
}
