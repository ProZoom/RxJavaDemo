package com.corewise.chenguangpim.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.corewise.chenguangpim.R;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 作者：李阳
 * 时间：2018/4/25
 * 描述：维修信息界面适配器
 */
public class MaintenanceInfosAdapter extends RecyclerView.Adapter<MaintenanceInfosAdapter.MaintenanceInfosHolder> {


    private View view;

    private ArrayList<String[]> mDatas = new ArrayList<>();
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;

    public MaintenanceInfosAdapter(ArrayList<String[]> mDatas) {
        this.mDatas = mDatas;
    }

    @Override
    public MaintenanceInfosHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_maintenance, parent, false);


        return new MaintenanceInfosHolder(view);
    }

    @Override
    public void onBindViewHolder(MaintenanceInfosHolder holder, int position) {
        if (holder == null) {
            holder = new MaintenanceInfosHolder(view);
        }

        holder.tvContent.setText("维修内容" + position);
        holder.tvContentValue.setText(mDatas.get(position)[0]);
        holder.tvTimeValue.setText(mDatas.get(position)[1]);
        holder.tvPersonLiableValue.setText(mDatas.get(position)[2]);
        holder.tvPersonLiableValue.setEnabled(false);
        holder.tvTimeValue.setEnabled(false);
        holder.tvContentValue.setEnabled(false);

        //判断是否设置了监听器
        if (mOnItemClickListener != null) {
            //为ItemView设置监听器
            final MaintenanceInfosHolder finalHolder = holder;
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = finalHolder.getLayoutPosition(); // 1
                    mOnItemClickListener.onItemClick(finalHolder.itemView, position); // 2
                }
            });
        }
        if (mOnItemLongClickListener != null) {
            final MaintenanceInfosHolder finalHolder1 = holder;
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int position = finalHolder1.getLayoutPosition();
                    mOnItemLongClickListener.onItemLongClick(finalHolder1.itemView, position);
                    //返回true 表示消耗了事件 事件不会继续传递
                    return true;
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    static class MaintenanceInfosHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.tv_content_value)
        EditText tvContentValue;
        @BindView(R.id.tv_time_value)
        EditText tvTimeValue;
        @BindView(R.id.tv_person_liable_value)
        EditText tvPersonLiableValue;

        public MaintenanceInfosHolder(View itemView) {
            super(itemView);
            tvContent = itemView.findViewById(R.id.tv_content);
            tvContentValue = itemView.findViewById(R.id.tv_content_value);
            tvTimeValue = itemView.findViewById(R.id.tv_time_value);
            tvPersonLiableValue = itemView.findViewById(R.id.tv_person_liable_value);
        }
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener mOnItemLongClickListener) {
        this.mOnItemLongClickListener = mOnItemLongClickListener;
    }
}
