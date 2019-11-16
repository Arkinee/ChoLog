package com.makeus.Modu.src.home.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.makeus.Modu.R;
import com.makeus.Modu.src.home.HomeFragment;
import com.makeus.Modu.src.home.models.HomeItem;

import static com.makeus.Modu.src.ApplicationClass.DATE_FORMAT;
import static com.makeus.Modu.src.ApplicationClass.HOME_DAY;
import static com.makeus.Modu.src.ApplicationClass.HOME_MONTH;
import static com.makeus.Modu.src.ApplicationClass.myFormatter;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    Context mContext;
    private ArrayList<HomeItem> mHomeList;
    private HomeFragment mHomeFragment;
    private OnItemClickListener mListener = null;

    public interface OnItemClickListener {
        public void onChangeClick(View v, int pos);
        public void onCancelClick(View v, int pos);
        public void onSettingClick(View v, int pos);
        public void onAlarmClick(View v, int pos);
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
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        assert inflater != null;
        View view = inflater.inflate(R.layout.item_home, parent, false);
        ViewHolder vh = new ViewHolder(view);

        return vh;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final HomeItem homeItem = mHomeList.get(position);
        //다음 결제일, DDay 계산
        String last = homeItem.getmLast();
        String next = homeItem.getmLast();
        Calendar cal = Calendar.getInstance();
        Date lastDate;
        Date nextDate;

        Log.d("로그", "last: " + last);
        try{
            lastDate = DATE_FORMAT.parse(last);
            nextDate = DATE_FORMAT.parse(next);
            cal.setTime(nextDate);
            if(homeItem.getmDurationPer() == 0) {
                cal.add(Calendar.DAY_OF_MONTH, homeItem.getmDuration());
            }else if(homeItem.getmDurationPer() == 1) {
                cal.add(Calendar.WEEK_OF_MONTH, homeItem.getmDuration());
            }else if(homeItem.getmDurationPer() == 2) {
                cal.add(Calendar.MONTH, homeItem.getmDuration());
            }else if(homeItem.getmDurationPer() == 3) {
                cal.add(Calendar.YEAR, homeItem.getmDuration());
            }
            nextDate = new Date(cal.getTimeInMillis());
            long calDate = nextDate.getTime() - lastDate.getTime();
            long calDateDays = calDate / (24 * 60 * 60 * 1000);
            calDateDays = Math.abs(calDateDays);
        }catch (ParseException e){

        }

        if(homeItem.getmDDay() < 8){
            holder.tvHomeDDay.setTextColor(mContext.getResources().getColor(R.color.colorTextHomeItemDDayRed));
        }else{
            holder.tvHomeDDay.setTextColor(mContext.getResources().getColor(R.color.colorTextHomeItemDDayBlack));
        }

        holder.tvHomeDDay.setText(mContext.getResources().getString(R.string.tv_main_d_day).concat(String.valueOf(homeItem.getmDDay())));
        String month = HOME_MONTH.format(cal.getTime());
        String day = HOME_DAY.format(cal.getTime());
        holder.tvHomeDate.setText(month.concat(mContext.getString(R.string.tv_item_home_month)).concat(day).concat(mContext.getString(R.string.tv_item_home_day)));
        holder.tvHomePrice.setText(myFormatter.format(homeItem.getmPrice()).concat(mContext.getResources().getString(R.string.tv_main_won)));
        Glide.with(mContext).load(homeItem.getmImageUrl()).placeholder(R.drawable.ic_melon).override(100, 100).into(holder.ivHomeImage);
        holder.ivHomeImage.setBackgroundResource(R.color.colorTextHomeItemBack);
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

        if(homeItem.isChecked()){
            holder.ivHomeAlarm.setImageDrawable(mContext.getDrawable(R.drawable.ic_alarm_true));
        }else{
            holder.ivHomeAlarm.setImageDrawable(mContext.getDrawable(R.drawable.ic_alarm_false));
        }

        holder.ivHomeAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onAlarmClick(view, position);
            }
        });

    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return mHomeList.size();
    }
}
