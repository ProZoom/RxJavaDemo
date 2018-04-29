package com.corewise.chenguangpim.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.corewise.chenguangpim.R;
import com.corewise.chenguangpim.utils.Constant;
import com.corewise.chenguangpim.utils.Constants;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.PriorityQueue;

import butterknife.BindView;

/**
 * 作者：李阳
 * 时间：2018/4/25
 * 描述：
 */
public class InfosAdapter extends RecyclerView.Adapter<InfosAdapter.InfosViewHolder> {

    private static final String TAG = "InfosAdapter";

    private Context mContext;

    private ArrayList<String> mTypesList = new ArrayList<>();

    private ArrayList<String> mTypesValuesList = new ArrayList<>();

    public interface SaveEditListener {
        void SaveEdit(int position, String string);
    }

    private View view;

    public InfosAdapter(Context context, ArrayList<String> mTypesList, ArrayList<String> mTypesValuesList) {

        this.mTypesList = mTypesList;
        this.mTypesValuesList = mTypesValuesList;
        this.mContext = context;
        Log.i(TAG, context.toString());
    }

    @Override
    public InfosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main, parent, false);

        InfosViewHolder holder = new InfosViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(InfosViewHolder holder, int position) {

        if (holder == null) {
            holder = new InfosViewHolder(view);
        }
        holder.tvTypes.setText(mTypesList.get(position));

        if (position == 0) {
            holder.tvTypesValue.setEnabled(false);
        }
        holder.tvTypesValue.setText(mTypesValuesList.get(position));
        holder.tvTypesValue.setHint(Constants.ProductManufactureDatasHint[position]);

        holder.tvTypes.setTag(position);        //通过设置tag，防止position紊乱
        holder.tvTypesValue.setTag(position);

        //添加editText的监听事件
        holder.tvTypesValue.addTextChangedListener(new TextSwitcher(holder));

    }

    @Override
    public int getItemCount() {
        return mTypesList.size();
    }

    static class InfosViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_types)
        TextView tvTypes;
        @BindView(R.id.tv_types_value)
        EditText tvTypesValue;

        public InfosViewHolder(View view) {
            super(view);
            tvTypes = view.findViewById(R.id.tv_types);
            tvTypesValue = view.findViewById(R.id.tv_types_value);
        }
    }


    //自定义EditText的监听类
    class TextSwitcher implements TextWatcher {

        private InfosViewHolder mHolder;

        public TextSwitcher(InfosViewHolder mHolder) {
            this.mHolder = mHolder;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            //用户输入完毕后，处理输入数据，回调给主界面处理
            SaveEditListener listener = (SaveEditListener) mContext;
            if (s != null) {
                listener.SaveEdit(Integer.parseInt(mHolder.tvTypesValue.getTag().toString()), s.toString());
            }

        }
    }


}
