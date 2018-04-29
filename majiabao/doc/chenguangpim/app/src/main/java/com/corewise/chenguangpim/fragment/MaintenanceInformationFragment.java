package com.corewise.chenguangpim.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.corewise.chenguangpim.R;
import com.corewise.chenguangpim.adapter.MaintenanceInfosAdapter;
import com.corewise.chenguangpim.adapter.SpaceItemDecoration;
import com.corewise.chenguangpim.dialog.AddMifDialog;
import com.corewise.chenguangpim.table.Product;
import com.corewise.chenguangpim.table.ProductManintenance;
import com.corewise.chenguangpim.table.ProductManu;
import com.corewise.chenguangpim.utils.Constant;
import com.corewise.chenguangpim.utils.Constants;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 作者：李阳
 * 时间：2018/4/25
 * 描述：维修信息
 */
public class MaintenanceInformationFragment extends Fragment implements View.OnClickListener {


    private static final String TAG = "MaintenanceInformationFragment";

    RecyclerView rlvMif;

    Button btnAddMif;

    MaintenanceInfosAdapter maintenanceInfosAdapter;

    AddMifDialog addMifDialog;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_mif, null);


        rlvMif = view.findViewById(R.id.rlv_mif);
        btnAddMif = view.findViewById(R.id.btn_add_mif);

        btnAddMif.setOnClickListener(this);

        initData();

        initEvent();

        return view;
    }


    private void initData() {
        //mDatas.add(new String[]{"焊缝修补1", "2017年7月15日", "张三"});

        Constants.ProductManintenanceDatasIndb.clear();
        if (Constants.isExitMain) {
            List<ProductManintenance> productManintenances = DataSupport.where("deviceid = ?", Constants.Tid).find(ProductManintenance.class);

            Log.i(TAG, "size------" + productManintenances.size());


            if (productManintenances.size() > 0) {
                Log.i(TAG, "size------" + productManintenances.get(0).toString());

                for (int i = 0; i < productManintenances.size(); i++) {
                    String[] maninfos = {productManintenances.get(i).getContent(),
                            productManintenances.get(i).getTime(),
                            productManintenances.get(i).getPerson()
                    };
                    Constants.ProductManintenanceDatasIndb.add(maninfos);
                }
            } else {
                Constants.ProductManintenanceDatasIndb.clear();

            }

        } else {
            Constants.ProductManintenanceDatasIndb.clear();
        }

        LinearLayoutManager manager = new LinearLayoutManager(getContext());

        Collections.reverse(Constants.ProductManintenanceDatasIndb);

        maintenanceInfosAdapter = new MaintenanceInfosAdapter(Constants.ProductManintenanceDatasIndb);

        rlvMif.setLayoutManager(manager);
        rlvMif.addItemDecoration(new SpaceItemDecoration(30));

        rlvMif.setAdapter(maintenanceInfosAdapter);

    }

    private void initEvent() {
        maintenanceInfosAdapter.setOnItemLongClickListener(new MaintenanceInfosAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View view, final int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
                        .setTitle("警告")
                        .setMessage("您要删除该条维修信息吗?")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //todo

                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Constants.ProductManintenanceDatasIndb.remove(position);
                                DataSupport.where("deviceid = ?",Constants.Tid).find(ProductManintenance.class).get(position).delete();
                                maintenanceInfosAdapter.notifyDataSetChanged();
                            }
                        });

                builder.create().show();

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //unbinder.unbind();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_mif:

                addMifDialog = new AddMifDialog(getActivity(), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Collections.reverse(Constants.ProductManintenanceDatasIndb);
                        String persion = addMifDialog.getString(R.id.et_mif_persion);
                        String time = addMifDialog.getString(R.id.et_mif_time);
                        String cotent = addMifDialog.getString(R.id.et_mif_content);
                        Log.i(TAG, persion + "----" + time + "-----" + cotent);
                        if (!persion.equals("")) {
                            if (!time.equals("")) {
                                if (!cotent.equals("")) {
                                    Log.i(TAG, "----------------" + Constants.ProductManintenanceDatasIndb.size());
                                    Constants.ProductManintenanceDatasIndb.add(new String[]{persion, time, cotent});
                                    // Constants.ProductManintenanceDatasAdd.add(new String[]{persion, time, cotent});
                                    Log.i(TAG, "----------------" + Constants.ProductManintenanceDatasIndb.size());

                                    //Log.i(TAG, "?????????????----" + Constants.ProductManintenanceDatasAdd.get(i)[0] + "----size:" + Constants.ProductManintenanceDatasAdd.size());
                                    ProductManintenance productManintenance = new ProductManintenance();
                                    productManintenance.setDeviceId(Constants.Tid);

                                    productManintenance.setTime(time);
                                    productManintenance.setPerson(cotent);
                                    productManintenance.setContent(persion);

                                    if(productManintenance.save()){
                                        Toast.makeText(getContext(), "维修信息保存成功！", Toast.LENGTH_SHORT).show();
                                    }


                                } else {
                                    Toast.makeText(v.getContext(), "请输入维修内容！", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(v.getContext(), "请输入维修时间！", Toast.LENGTH_SHORT).show();

                            }
                        } else {
                            Toast.makeText(v.getContext(), "请输入维修责任人！", Toast.LENGTH_SHORT).show();
                        }
                        Collections.reverse(Constants.ProductManintenanceDatasIndb);

                        maintenanceInfosAdapter.notifyDataSetChanged();
                        addMifDialog.dismiss();
                    }
                });

                addMifDialog.show();


                break;
        }
    }
}
