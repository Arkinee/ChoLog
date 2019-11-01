package com.makeus.ChoLog.src.myInfo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.makeus.ChoLog.R;
import com.makeus.ChoLog.src.home.HomeFragment;
import com.makeus.ChoLog.src.home.models.HomeItem;
import com.makeus.ChoLog.src.myInfo.MyInfoFragment;
import com.makeus.ChoLog.src.myInfo.models.MyInfoItem;

import java.util.ArrayList;

import static com.makeus.ChoLog.src.ApplicationClass.myFormatter;


public class MyInfoAdapter extends RecyclerView.Adapter<MyInfoAdapter.ViewHolder> {

    Context mContext;
    private ArrayList<MyInfoItem> mMyInfoList;
    private MyInfoFragment mMyInfoFragment;

    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvMyInfoCategory;
        TextView tvMyInfoPrice;
        TextView tvMyInfoPercent;
        ProgressBar pbMyInfo;

        ViewHolder(View itemView) {
            super(itemView);
            // 뷰 객체에 대한 참조. (hold strong reference)
            tvMyInfoCategory = itemView.findViewById(R.id.tv_item_my_info_category);
            tvMyInfoPrice = itemView.findViewById(R.id.tv_item_my_info_price);
            tvMyInfoPercent = itemView.findViewById(R.id.tv_item_my_info_percent);
            pbMyInfo = itemView.findViewById(R.id.pb_item_my_info);

        }
    }

    // 생성자에서 데이터 리스트 객체를 전달받음.
    public MyInfoAdapter(Context mContext, ArrayList<MyInfoItem> myInfoItems, MyInfoFragment myInfoFragment) {
        this.mContext = mContext;
        this.mMyInfoList = myInfoItems;
        this.mMyInfoFragment = myInfoFragment;
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.item_my_info, parent, false);
        ViewHolder vh = new ViewHolder(view);

        return vh;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final MyInfoItem myInfoItem = mMyInfoList.get(position);
        holder.tvMyInfoCategory.setText(myInfoItem.getmCategory());
        holder.tvMyInfoPrice.setText(myFormatter.format(myInfoItem.getmPrice()).concat("원"));
        holder.tvMyInfoPercent.setText(String.valueOf(myInfoItem.getmPercent()).concat("%"));
        holder.pbMyInfo.setProgress(myInfoItem.getmPercent());
    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return mMyInfoList.size();
    }
}
