package com.makeus.Modu.src.lookAround.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.makeus.Modu.R;
import com.makeus.Modu.src.lookAround.models.LookItem;

import java.util.ArrayList;

import static com.makeus.Modu.src.ApplicationClass.myFormatter;


public class LookAdapter extends RecyclerView.Adapter<LookAdapter.ViewHolder> {

    Context mContext;
    private ArrayList<LookItem> mPopularList;

    public interface OnItemClickListener {
        void onItemClick(View V, int pos);
    }

    private OnItemClickListener mListener = null;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvLookService;
        TextView tvLookCategory;
        TextView tvLookPrice;
        ImageView ivLookImage;

        ViewHolder(final View itemView) {
            super(itemView);
            // 뷰 객체에 대한 참조. (hold strong reference)
            tvLookService = itemView.findViewById(R.id.tv_item_look_service_name);
            tvLookPrice = itemView.findViewById(R.id.tv_item_look_service_fee);
            tvLookCategory = itemView.findViewById(R.id.tv_item_look_category);
            ivLookImage = itemView.findViewById(R.id.iv_item_look_image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        if (mListener != null) {
                            mListener.onItemClick(itemView, pos);
                        }
                    }
                }
            });

        }
    }

    // 생성자에서 데이터 리스트 객체를 전달받음.
    public LookAdapter(Context mContext, ArrayList<LookItem> mPupularList) {
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
        holder.tvLookCategory.setText(lookItem.getmCategory().concat(mContext.getString(R.string.middleDot)).concat(lookItem.getmServiceName()));

        if (lookItem.getmPrice() == 0) {
            holder.tvLookPrice.setText(mContext.getString(R.string.tv_look_offline_service));
        } else {
            holder.tvLookPrice.setText(myFormatter.format(lookItem.getmPrice())
                    .concat(mContext.getResources().getString(R.string.tv_won)));
        }

        String imageUrl = "";
        if (lookItem.getmImageUrl() != null) {
            imageUrl = lookItem.getmImageUrl().replace("drive.google.com/open", "docs.google.com/uc");
        }
        Glide.with(mContext).load(imageUrl).placeholder(R.drawable.ic_default).override(50, 50).into(holder.ivLookImage);

    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return mPopularList.size();
    }
}
