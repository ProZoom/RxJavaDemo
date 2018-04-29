package com.corewise.chenguangpim.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.corewise.chenguangpim.R;
import com.corewise.chenguangpim.adapter.InfosAdapter;
import com.corewise.chenguangpim.table.Product;
import com.corewise.chenguangpim.table.ProductManu;
import com.corewise.chenguangpim.utils.Constant;
import com.corewise.chenguangpim.utils.Constants;

import org.litepal.crud.DataSupport;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者：李阳
 * 时间：2018/4/25
 * 描述：产品制造详情页面
 */
public class ProductManufactureFragment extends Fragment {


    private static final String TAG = "ProductManufactureFragment";

    @BindView(R.id.rlv_pm)
    RecyclerView rlvPm;
    Unbinder unbinder;

    private String[] mTypesString = new String[]{
            "设备ID:",
            "产品名称:",
            "膨胀节型号:",
            "出厂编号:",
            "执行标准:",
            "外形尺寸:",
            "总质量:",
            "制造厂名称:",
            "制造厂地址:",
            "制造日期:"
    };


    private String[] mTypesValueString = new String[10];
   /* {
            "1234567890",
            "一次性补偿器",
            "HTCG1400/120",
            "20170610000",
            "GB/T 12777-2008",
            "1500×1500×1000",
            "15000 Kg",
            "航天晨光股份有限公司",
            "江苏南京",
            "2017年6月12日"
    };*/


    private ArrayList<String> mTypesList = new ArrayList<>();

    private ArrayList<String> mTypesValuesList = new ArrayList<>();

    private InfosAdapter infosAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pmf, null);
        unbinder = ButterKnife.bind(this, view);

        //获得窗体传递来的数据
        Bundle bundle = ProductManufactureFragment.this.getArguments();
        //显示传递来的数据
        mTypesValueString[0] = bundle.getString("id");

        initData();


        return view;
    }

    private void initData() {
        //mTypesList= (ArrayList<String>) Arrays.asList(mTypesString);


        if (Constants.isExitManu) {
            //mTypesValueString[0]= DataSupport.where("deviceid = ?",Constants.Tid).find(ProductManu.class).get(0).getName();
            mTypesValueString[1] = DataSupport.where("deviceid = ?", Constants.Tid).find(ProductManu.class).get(0).getName();
            mTypesValueString[2] = DataSupport.where("deviceid = ?", Constants.Tid).find(ProductManu.class).get(0).getExpansionJointModel();
            mTypesValueString[3] = DataSupport.where("deviceid = ?", Constants.Tid).find(ProductManu.class).get(0).getSerialNumber();
            mTypesValueString[4] = DataSupport.where("deviceid = ?", Constants.Tid).find(ProductManu.class).get(0).getExecutiveStandard();
            mTypesValueString[5] = DataSupport.where("deviceid = ?", Constants.Tid).find(ProductManu.class).get(0).getDimensions();
            mTypesValueString[6] = DataSupport.where("deviceid = ?", Constants.Tid).find(ProductManu.class).get(0).getTotalWeight();
            mTypesValueString[7] = DataSupport.where("deviceid = ?", Constants.Tid).find(ProductManu.class).get(0).getManufacturerName();
            mTypesValueString[8] = DataSupport.where("deviceid = ?", Constants.Tid).find(ProductManu.class).get(0).getManufacturerAdress();
            mTypesValueString[9] = DataSupport.where("deviceid = ?", Constants.Tid).find(ProductManu.class).get(0).getManufacturerDate();
        } else {
            for (int i = 1; i < mTypesValueString.length; i++) {
                mTypesValueString[i] = "";
            }
        }


        mTypesList = new ArrayList<String>(Arrays.asList(mTypesString));
        mTypesValuesList = new ArrayList<>(Arrays.asList(mTypesValueString));

        infosAdapter = new InfosAdapter(getActivity(), mTypesList, mTypesValuesList);


        LinearLayoutManager manager = new LinearLayoutManager(getContext());

        rlvPm.setLayoutManager(manager);


        rlvPm.setAdapter(infosAdapter);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
