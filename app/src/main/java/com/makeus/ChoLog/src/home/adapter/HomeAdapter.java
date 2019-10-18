package com.makeus.ChoLog.src.home.adapter;

import android.content.Context;
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

    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvHomeDate;
        TextView tvHomeDDay;
        TextView tvHomePrice;
        ImageView ivHomeAlarm;
        ImageView ivHomeImage;
        TextView tvHomeCategory;
        TextView tvHomeBrand;
        TextView tvHomeWhile;

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
            tvHomeWhile = itemView.findViewById(R.id.tv_item_home_while);

        }
    }

    // 생성자에서 데이터 리스트 객체를 전달받음.
    public HomeAdapter(Context mContext, ArrayList<HomeItem> mHomeList) {
        this.mContext = mContext;
        this.mHomeList = mHomeList;
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
        holder.tvHomeDate.setText(homeItem.getmDate());
        holder.tvHomeDDay.setText("D-".concat(String.valueOf(homeItem.getmDDay())));
        holder.tvHomePrice.setText(myFormatter.format(homeItem.getmPrice()).concat("원"));
        Glide.with(mContext).load(homeItem.getmImageUrl()).placeholder(R.drawable.ic_melon).override(200, 200).into(holder.ivHomeImage);
        holder.tvHomeCategory.setText(homeItem.getmCategory());
        holder.tvHomeBrand.setText(homeItem.getmBrand());
        holder.tvHomeWhile.setText(String.valueOf(homeItem.getmWhile()).concat("개월째 구독중"));

    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return mHomeList.size();
    }
}
