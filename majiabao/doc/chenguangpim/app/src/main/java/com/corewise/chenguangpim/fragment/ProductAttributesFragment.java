package com.corewise.chenguangpim.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.corewise.chenguangpim.R;
import com.corewise.chenguangpim.adapter.AttriAdapter;
import com.corewise.chenguangpim.adapter.InfosAdapter;
import com.corewise.chenguangpim.table.ProductAttri;
import com.corewise.chenguangpim.table.ProductManu;
import com.corewise.chenguangpim.utils.Constants;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者：李阳
 * 时间：2018/4/25
 * 描述：产品属性详情页
 */
public class ProductAttributesFragment extends Fragment {


    @BindView(R.id.rlv_paf)
    RecyclerView rlvPaf;
    Unbinder unbinder;
    private String[] mTypesString = new String[]{
            "膨胀节型式:",
            "公称直径:",
            "设计温度:",
            "设计压力:",
            "试验压力:",
            "轴向位移:",
            "横向位移:",
            "角向位移:",
            "疲劳寿命:",
            "轴向刚度:",
            "横向刚度:",
            "角向刚度:",
            "波纹管材质:"
    };

    private String[] mTypesValueString = new String[13];
/*    {
            "一次性补偿器",
            "DN1400",
            "120" + " ℃",
            "1.6" + " MPa",
            "2.4" + " MPa",
            "250" + " mm",
            "/" + " mm",
            "/" + " °",
            "100" + " 次",
            "5200" + " N/mm",
            "/" + " N/mm",
            "/" + " N/mm",
            "316L"
    };*/

    private ArrayList<String> mTypesList = new ArrayList<>();

    private ArrayList<String> mTypesValuesList = new ArrayList<>();

    private AttriAdapter infosAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_paf, null);

        unbinder = ButterKnife.bind(this, view);
        initData();
        return view;
    }


    private void initData() {
        //mTypesList= (ArrayList<String>) Arrays.asList(mTypesString);

        if (Constants.isExitAttri) {
            //mTypesValueString[0]= DataSupport.where("deviceid = ?",Constants.Tid).find(ProductManu.class).get(0).getName();
            mTypesValueString[0] = DataSupport.where("deviceid = ?", Constants.Tid).find(ProductAttri.class).get(0).getExpansionJointForm();
            mTypesValueString[1] = DataSupport.where("deviceid = ?", Constants.Tid).find(ProductAttri.class).get(0).getNominalDiameter();
            mTypesValueString[2] = DataSupport.where("deviceid = ?", Constants.Tid).find(ProductAttri.class).get(0).getTempSet();
            mTypesValueString[3] = DataSupport.where("deviceid = ?", Constants.Tid).find(ProductAttri.class).get(0).getPressureSet();
            mTypesValueString[4] = DataSupport.where("deviceid = ?", Constants.Tid).find(ProductAttri.class).get(0).getTestPressure();
            mTypesValueString[5] = DataSupport.where("deviceid = ?", Constants.Tid).find(ProductAttri.class).get(0).getAxialDisplacement();
            mTypesValueString[6] = DataSupport.where("deviceid = ?", Constants.Tid).find(ProductAttri.class).get(0).getLateralDisplacement();
            mTypesValueString[7] = DataSupport.where("deviceid = ?", Constants.Tid).find(ProductAttri.class).get(0).getAngularDisplacement();
            mTypesValueString[8] = DataSupport.where("deviceid = ?", Constants.Tid).find(ProductAttri.class).get(0).getFatigueLife();
            mTypesValueString[9] = DataSupport.where("deviceid = ?", Constants.Tid).find(ProductAttri.class).get(0).getAxialStiffness();

            mTypesValueString[10] = DataSupport.where("deviceid = ?", Constants.Tid).find(ProductAttri.class).get(0).getLateralStiffness();
            mTypesValueString[11] = DataSupport.where("deviceid = ?", Constants.Tid).find(ProductAttri.class).get(0).getAxialStiffness();
            mTypesValueString[12] = DataSupport.where("deviceid = ?", Constants.Tid).find(ProductAttri.class).get(0).getBellowsMaterial();


        } else {
            for (int i = 1; i < mTypesValueString.length; i++) {
                mTypesValueString[i] = "";
            }
        }

        mTypesList = new ArrayList<String>(Arrays.asList(mTypesString));
        mTypesValuesList = new ArrayList<>(Arrays.asList(mTypesValueString));


        infosAdapter = new AttriAdapter(getContext(), mTypesList, mTypesValuesList);


        LinearLayoutManager manager = new LinearLayoutManager(getContext());

        rlvPaf.setLayoutManager(manager);


        rlvPaf.setAdapter(infosAdapter);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
