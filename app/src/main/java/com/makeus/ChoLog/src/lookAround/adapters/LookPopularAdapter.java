package com.makeus.ChoLog.src.lookAround.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.makeus.ChoLog.R;
import com.makeus.ChoLog.src.lookAround.models.LookItem;

import java.util.ArrayList;

import static com.makeus.ChoLog.src.ApplicationClass.myFormatter;


public class LookPopularAdapter extends RecyclerView.Adapter<LookPopularAdapter.ViewHolder> {

    Context mContext;
    private ArrayList<LookItem> mPopularList;

    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvLookService;
        TextView tvLookCategory;
        TextView tvLookPrice;
        ImageView ivLookImage;

        ViewHolder(View itemView) {
            super(itemView);
            // 뷰 객체에 대한 참조. (hold strong reference)
            tvLookService = itemView.findViewById(R.id.tv_item_look_service_name);
            tvLookPrice = itemView.findViewById(R.id.tv_item_look_service_fee);
            tvLookCategory = itemView.findViewById(R.id.tv_item_look_category);
            ivLookImage = itemView.findViewById(R.id.iv_item_look_image);

        }
    }

    // 생성자에서 데이터 리스트 객체를 전달받음.
    public LookPopularAdapter(Context mContext, ArrayList<LookItem> mPupularList) {
        this.mContext = mContext;
        this.mPopularList = mPupularList;
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.item_look_around, parent, false);
        ViewHolder vh = new ViewHolder(view);

        return vh;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final LookItem lookItem = mPopularList.get(position);
        holder.tvLookService.setText(lookItem.getmBrand());
        holder.tvLookCategory.setText(lookItem.getmCategory());
        holder.tvLookPrice.setText(myFormatter.format(lookItem.getmPrice()).concat("원"));
        Glide.with(mContext).load(lookItem.getmImageUrl()).placeholder(R.drawable.ic_adobe_cloud).override(200, 200).into(holder.ivLookImage);

    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return mPopularList.size();
    }
}
