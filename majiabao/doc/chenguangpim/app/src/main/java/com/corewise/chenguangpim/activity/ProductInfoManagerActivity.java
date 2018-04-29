package com.corewise.chenguangpim.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.corewise.chenguangpim.R;
import com.corewise.chenguangpim.adapter.AttriAdapter;
import com.corewise.chenguangpim.adapter.InfosAdapter;
import com.corewise.chenguangpim.fragment.InstallInformationFragment;
import com.corewise.chenguangpim.fragment.MaintenanceInformationFragment;
import com.corewise.chenguangpim.fragment.ProductAttributesFragment;
import com.corewise.chenguangpim.fragment.ProductManufactureFragment;
import com.corewise.chenguangpim.table.ProductAttri;
import com.corewise.chenguangpim.table.ProductManintenance;
import com.corewise.chenguangpim.table.ProductManu;
import com.corewise.chenguangpim.utils.Constant;
import com.corewise.chenguangpim.utils.Constants;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：李阳
 * 时间：2018/4/25
 * 描述：
 */
public class ProductInfoManagerActivity extends AppCompatActivity implements InfosAdapter.SaveEditListener, AttriAdapter.SaveEditListener {


    private static final String TAG = "ProductInfoManagerActivity";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.iv_toolbar_back)
    ImageView ivToolbarBack;
    @BindView(R.id.tl)
    TabLayout tl;
    @BindView(R.id.vp_pim)
    ViewPager vpPim;

    private String id;


    private ArrayList<String> tabList = new ArrayList<>();
    private ArrayList<Fragment> fragmentsList = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pim);
        ButterKnife.bind(this);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        Bundle bundle = this.getIntent().getExtras();
        id = bundle.getString("id");
        //Toast.makeText(this, id, Toast.LENGTH_SHORT).show();

        initToolBar();

        initData();

        initViewPager();
    }

    private void initViewPager() {

        Bundle bundle = new Bundle();

        ProductManufactureFragment productManufactureFragment = new ProductManufactureFragment();
        bundle.putString("id", id);//这里的values就是我们要传的值
        productManufactureFragment.setArguments(bundle);


        fragmentsList.add(productManufactureFragment);

        fragmentsList.add(new ProductAttributesFragment());

        fragmentsList.add(new MaintenanceInformationFragment());

        fragmentsList.add(new InstallInformationFragment());

        tl.setupWithViewPager(vpPim);

        vpPim.setAdapter(new Adapter(getSupportFragmentManager(), fragmentsList, tabList));

        vpPim.setOffscreenPageLimit(7);

        vpPim.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Constants.itemPositionInViewpager = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initData() {
        tabList.add("产品制造");
        tabList.add("产品属性");
        tabList.add("维修信息");
        tabList.add("安装信息");

        if (Constants.isExitManu)
            Constants.ProductManufactureDatas = DataSupport.where("deviceid = ?", Constants.Tid).find(ProductManu.class).get(0).toStringArray();

        if (Constants.isExitAttri)
            Constants.ProductAttriDatas = DataSupport.where("deviceid = ?", Constants.Tid).find(ProductAttri.class).get(0).toStringArray();


        if (Constants.isExitMain) {
            int size = DataSupport.where("deviceid = ?", Constants.Tid).find(ProductManintenance.class).size();
            Log.i(TAG,"-----db----size:"+size);
            for (int i = 0; i < size; i++)
                Constants.ProductManintenanceDatasIndb.add(DataSupport.where("deviceid = ?", Constants.Tid).find(ProductManintenance.class).get(i).toStringArray());

        }

    }

    private void initToolBar() {
        setSupportActionBar(toolbar);

        toolbar.setTitle("");
        //toolbar.setLogo(R.drawable.ic_pim_title);
        ivToolbarBack.setBackground(getResources().getDrawable(R.drawable.icon_pim_title));
    }


    public class Adapter extends FragmentPagerAdapter {
        private ArrayList<Fragment> fragments;
        private ArrayList<String> list;

        public Adapter(FragmentManager fm, ArrayList<Fragment> fragments, ArrayList<String> list) {
            super(fm);
            this.fragments = fragments;
            this.list = list;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return list.get(position);
        }
    }


    @Override
    public void SaveEdit(int position, final String string) {
        Log.i(TAG, string + "---" + position);
        Constants.isAddInfo = true;

        Constants.ProductManufactureDatas[position] = string;


    }

    @Override
    public void SaveAttriEdit(int position, String string) {
        Log.i(TAG, string + "---" + position);
        Constants.isAddInfo = true;

        Constants.ProductAttriDatas[position] = string;

    }


   /* @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (Constants.isAddInfo) {
            if (keyCode == KeyEvent.KEYCODE_BACK) {


                AlertDialog.Builder builder = new AlertDialog.Builder(this)
                        .setTitle("提示")
                        .setMessage("您已修改该设备相关信息！是否更新数据库信息？")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Toast.makeText(ProductInfoManagerActivity.this, "你已取消更新数据库！", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                //todo:更新数据库操作


                                finish();
                            }
                        });

                builder.create().show();
            }


            return true;
        }
        return super.onKeyDown(keyCode, event);
    }*/
}
