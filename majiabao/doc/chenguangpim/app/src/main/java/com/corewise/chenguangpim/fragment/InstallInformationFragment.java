package com.corewise.chenguangpim.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.corewise.chenguangpim.R;
import com.corewise.chenguangpim.table.Product;
import com.corewise.chenguangpim.table.ProductAttri;
import com.corewise.chenguangpim.table.ProductInstallInfos;
import com.corewise.chenguangpim.table.ProductManintenance;
import com.corewise.chenguangpim.table.ProductManu;
import com.corewise.chenguangpim.utils.Constant;
import com.corewise.chenguangpim.utils.Constants;

import org.litepal.crud.DataSupport;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 作者：李阳
 * 时间：2018/4/25
 * 描述：安装报废信息
 */
public class InstallInformationFragment extends Fragment {


    private static final String TAG = "InstallInformationFragment";

    @BindView(R.id.tv_install_time_value)
    TextView tvInstallTimeValue;
    @BindView(R.id.tv_install_persion_value)
    TextView tvInstallPersionValue;
    @BindView(R.id.tv_uninstall_time_value)
    TextView tvUninstallTimeValue;
    @BindView(R.id.tv_uninstall_persion_value)
    TextView tvUninstallPersionValue;
    Unbinder unbinder;
    @BindView(R.id.btn_insert)
    Button btnInsert;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_iif, null);
        unbinder = ButterKnife.bind(this, view);

        if (Constants.isExitInstall) {
            btnInsert.setText("更新该设备信息");

        } else {
            btnInsert.setText("数据库无此设备信息，添加该设备信息");
        }

        initView();
        initData();

        return view;

    }


    private void initView() {
        List<ProductInstallInfos> productsInstall = DataSupport.where("deviceid = ?", Constants.Tid).find(ProductInstallInfos.class);
        if (productsInstall.size() > 0) {
            tvInstallTimeValue.setText(productsInstall.get(0).getInstallTime());
            tvInstallPersionValue.setText(productsInstall.get(0).getInstallPerson());
            tvUninstallTimeValue.setText(productsInstall.get(0).getUnInstallTime());
            tvUninstallPersionValue.setText(productsInstall.get(0).getUnInstallPerson());
        } else {

        }
    }

    private void initData() {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btn_insert)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_insert:
                //更新该设备所有数据

                ProductManu productManu = new ProductManu();
                productManu.setDeviceId(Constants.Tid);
                productManu.setName(Constants.ProductManufactureDatas[1]);
                productManu.setExpansionJointModel(Constants.ProductManufactureDatas[2]);
                productManu.setSerialNumber(Constants.ProductManufactureDatas[3]);
                productManu.setExecutiveStandard(Constants.ProductManufactureDatas[4]);
                productManu.setDimensions(Constants.ProductManufactureDatas[5]);
                productManu.setTotalWeight(Constants.ProductManufactureDatas[6]);
                productManu.setManufacturerName(Constants.ProductManufactureDatas[7]);
                productManu.setManufacturerAdress(Constants.ProductManufactureDatas[8]);
                productManu.setManufacturerDate(Constants.ProductManufactureDatas[9]);
                if (Constants.isExitManu) {//存在数据，更新设备
                    productManu.updateAll("deviceid = ?", Constants.Tid);
                } else {//不存在，添加数据
                    if (productManu.save()) {
                        Toast.makeText(getContext(), "该设备信息添加成功！", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "该设备信息添加失败！", Toast.LENGTH_SHORT).show();
                    }
                    Constants.isExitManu = true;
                }

                ProductAttri productAttri = new ProductAttri();
                productAttri.setDeviceId(Constants.Tid);
                productAttri.setExpansionJointForm(Constants.ProductAttriDatas[0]);
                productAttri.setNominalDiameter(Constants.ProductAttriDatas[1]);
                productAttri.setTempSet(Constants.ProductAttriDatas[2]);
                productAttri.setPressureSet(Constants.ProductAttriDatas[3]);
                productAttri.setTestPressure(Constants.ProductAttriDatas[4]);
                productAttri.setAxialDisplacement(Constants.ProductAttriDatas[5]);
                productAttri.setLateralDisplacement(Constants.ProductAttriDatas[6]);
                productAttri.setAngularDisplacement(Constants.ProductAttriDatas[7]);
                productAttri.setFatigueLife(Constants.ProductAttriDatas[8]);
                productAttri.setAxialStiffness(Constants.ProductAttriDatas[9]);
                productAttri.setLateralStiffness(Constants.ProductAttriDatas[10]);
                productAttri.setAngularStiffness(Constants.ProductAttriDatas[11]);
                productAttri.setBellowsMaterial(Constants.ProductAttriDatas[12]);
                if (Constants.isExitAttri) {//存在数据，更新设备
                    productAttri.updateAll("deviceid = ?", Constants.Tid);
                    Toast.makeText(getContext(), "该设备信息更新成功！", Toast.LENGTH_SHORT).show();

                } else {//不存在，添加数据
                    if (productAttri.save()) {
                        Toast.makeText(getContext(), "该设备信息添加成功1！", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "该设备信息添加失败1！", Toast.LENGTH_SHORT).show();
                    }
                    Constants.isExitAttri = true;
                }




                ProductInstallInfos productInstallInfos = new ProductInstallInfos();
                productInstallInfos.setDeviceId(Constants.Tid);
                productInstallInfos.setInstallTime(tvInstallTimeValue.getText().toString());
                productInstallInfos.setInstallPerson(tvInstallPersionValue.getText().toString());
                productInstallInfos.setUnInstallTime(tvUninstallTimeValue.getText().toString());
                productInstallInfos.setUnInstallPerson(tvUninstallPersionValue.getText().toString());

                if (Constants.isExitInstall) {//存在数据，更新设备
                    productInstallInfos.updateAll("deviceid = ?", Constants.Tid);
                } else {//不存在，添加数据
                    if (productInstallInfos.save()) {
                        Toast.makeText(getContext(), "该设备信息添加成功2！", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "该设备信息添加失败2！", Toast.LENGTH_SHORT).show();
                    }
                    Constants.isExitInstall = true;
                }


                /*Product product = new Product();
                product.setDeviceId(Constants.Tid);
                product.setProductManu(productManu);
                product.setProductAttri(productAttri);
                product.setProductInstallInfos(productInstallInfos);
                if (Constants.isExitInstall) {//存在数据，更新设备
                    product.updateAll("deviceid = ?", Constants.Tid);
                } else {//不存在，添加数据
                    if (product.save()) {
                        Toast.makeText(getContext(), "该设备信息添加成功2！", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "该设备信息添加失败2！", Toast.LENGTH_SHORT).show();
                    }
                    Constants.isExitInstall = true;
                }*/


                Log.i(TAG, Arrays.toString(Constants.ProductManufactureDatas) + "\n");

                Log.i(TAG, Arrays.toString(Constants.ProductAttriDatas) + "\n");

                //Log.i(TAG,Arrays.toString(Constants.ProductManufactureDatas));


              /*  if (Constants.isExitInstall) {//存在数据，更新设备
                    product.updateAll("deviceid = ?", Constants.Tid);
                    productManu.updateAll("deviceid = ?", Constants.Tid);


                    productInstallInfos.updateAll("deviceid = ?", Constants.Tid);
                    product.updateAll("deviceid = ?", Constants.Tid);
                    Toast.makeText(getContext(), "该设备信息更新成功！", Toast.LENGTH_SHORT).show();

                } else {//不存在，添加数据
                    boolean flag = true;
                    for (int i = 0; i < productManintenancesMDatas.size(); i++) {
                        flag = flag && productManintenancesMDatas.get(i).save();
                    }

                    if (flag && product.save() && productManu.save() && productAttri.save() && productInstallInfos.save()) {
                        Toast.makeText(getContext(), "该设备信息添加成功！", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "该设备信息添加失败！", Toast.LENGTH_SHORT).show();
                    }
                    Constants.isExitInstall = true;
                }*/


                break;
        }
    }
}
