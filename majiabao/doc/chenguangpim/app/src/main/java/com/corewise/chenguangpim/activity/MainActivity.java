package com.corewise.chenguangpim.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.corewise.chenguangpim.R;
import com.corewise.chenguangpim.table.Product;
import com.corewise.chenguangpim.table.ProductAttri;
import com.corewise.chenguangpim.table.ProductInstallInfos;
import com.corewise.chenguangpim.table.ProductManintenance;
import com.corewise.chenguangpim.table.ProductManu;
import com.corewise.chenguangpim.utils.Bytes;
import com.corewise.chenguangpim.utils.Constant;
import com.corewise.chenguangpim.utils.Constants;
import com.corewise.chenguangpim.utils.DataUtils;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;

import android_serialport_api.SerialPort;
import android_serialport_api.SerialPortManager;
import android_serialport_api.UHFHXAPI;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kr.co.namee.permissiongen.PermissionGen;


/**
 * 作者：李阳
 * 时间：2018/4/24
 * 描述：
 */
public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";

    ProgressDialog progressDialog;
    String epc;


    boolean isSuccess = false;
    byte[] arguments;

    UHFHXAPI api;

    @BindView(R.id.btn_scan)
    Button btnScan;
    @BindView(R.id.toolbar)
    Toolbar toolbar;


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case Constant.READ_UHFHX_FAILED:
                    progressDialog.dismiss();
                    Toast.makeText(MainActivity.this, "读取标签失败！", Toast.LENGTH_SHORT).show();

                    break;
                case Constant.READ_UHFHX_SUCCESS:
                    //progressDialog.dismiss();
                    isSuccess = true;
                    if(Constants.isExitMain||Constants.isExitAttri||Constants.isExitMain||Constants.isExitInstall)
                        Toast.makeText(MainActivity.this, "读取标签成功！数据库存在此设备！", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(MainActivity.this, "读取标签成功！数据库无此设备！", Toast.LENGTH_SHORT).show();

                    //finish();
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            Intent intent = new Intent(MainActivity.this, ProductInfoManagerActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("id", Constants.Tid);
                            intent.putExtras(bundle);
                            startActivity(intent);

                        }
                    }, 500);

                    break;

                case Constant.READ_UHFHX_TIMEOUT:
                    //progressDialog.dismiss();
                    Toast.makeText(MainActivity.this, "读取标签时间超时！", Toast.LENGTH_SHORT).show();
                    break;
                case Constant.READ_UHFHX_END:
                    //progressDialog.dismiss();
                    break;

                case Constant.READ_UHFHX_START:
                    break;

                case Constant.READ_UHFHX_PREPARE:
                    progressDialog = progressDialog.show(MainActivity.this, "读取标签信息", "请将设备靠近标签！", true, false);

                    break;
            }
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//全屏

        api = new UHFHXAPI();


        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(true);
        initToolBar();

        initPermission();


    }

    private void initPermission() {
        PermissionGen.with(this)
                .addRequestCode(100)
                .permissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .request();

        //Connector.getDatabase();

    }

    @Override
    protected void onResume() {
        super.onResume();
        isSuccess = false;

        if (!SerialPortManager.getInstance().openSerialPort()) {
            //Toast.makeText(this, "OpenSerialPort--Failed!", Toast.LENGTH_SHORT).show();
            Log.i(TAG, "OpenSerialPort--Failed!");
        } else {
            //Toast.makeText(this, "OpenSerialPort--Success!", Toast.LENGTH_SHORT).show();
            Log.i(TAG, "OpenSerialPort--Success!");
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (SerialPortManager.getInstance().isOpen()) {
            SerialPortManager.getInstance().closeSerialPort();
            Log.i(TAG, "SerialPort--Closed!");

        }
        progressDialog.dismiss();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (SerialPortManager.getInstance().isOpen()) {
            SerialPortManager.getInstance().closeSerialPort();
            Log.i(TAG, "SerialPort--Closed!");

        }
        progressDialog.dismiss();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        PermissionGen.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }


    private void initToolBar() {
        setSupportActionBar(toolbar);
        //toolbar.setLogo(getResources().getDrawable(R.drawable.logo));
        toolbar.setTitle("");
        //toolbar.setSubtitle(getResources().getString(R.string.MaintoolbarSubtitle));
        //toolbar.setBackground(getResources().getDrawable(R.drawable.icon_title));
        //toolbar.setSubtitleTextColor(getResources().getColor(R.color.white));
        //toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        //toolbar.setTitleMargin(60, 0, 0, 0);
        //toolbar.setTitleTextAppearance(this, R.style.AppTheme);
        //toolbar.set
    }

    @OnClick({R.id.btn_scan})
    public void onViewClicked(View view) {


        switch (view.getId()) {
            case R.id.btn_scan:

                mHandler.sendEmptyMessage(Constant.READ_UHFHX_PREPARE);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (!isSuccess) {
                            scanLabel();
                        }
                    }
                }).start();


                break;
        }
    }

    private void scanLabel() {
        Log.i(TAG, "扫描标签线程中.....");
        api.open();
        api.startAutoRead2A(0x22, new byte[]{0x00, 0x01}, new UHFHXAPI.AutoRead() {
            @Override
            public void start() {
                mHandler.sendEmptyMessage(Constant.READ_UHFHX_START);
            }

            @Override
            public void processing(byte[] data) {
                epc = DataUtils.toHexString(data).substring(4);
                Log.i(TAG, "读取标签成功！epc=" + epc);

                arguments = Bytes.concat(new byte[][]{DataUtils.hexStringTobyte("00000000"),
                        DataUtils.short2byte((short) (epc.length() / 2)),
                        DataUtils.hexStringTobyte(epc), new byte[]{(byte) 2},
                        DataUtils.short2byte((short) 0), DataUtils.short2byte((short) 6)});

                Constants.Tid = readTag(arguments);
                Log.i(TAG, "读取标签成功！tid=" + Constants.Tid);

                if (!Constants.Tid.equals("null")) {
                    isSuccess = true;
                    List<Product> products = DataSupport.where("deviceid = ?", Constants.Tid).find(Product.class);
                    List<ProductManintenance> productManintenances = DataSupport.where("deviceid = ?", Constants.Tid).find(ProductManintenance.class);
                    List<ProductAttri> productAttris = DataSupport.where("deviceid = ?", Constants.Tid).find(ProductAttri.class);
                    List<ProductInstallInfos> productInstallInfos = DataSupport.where("deviceid = ?", Constants.Tid).find(ProductInstallInfos.class);
                    List<ProductManu> productManus = DataSupport.where("deviceid = ?", Constants.Tid).find(ProductManu.class);
                    Log.i(TAG,products.size()+"------"+productManintenances.size()+"------"+productInstallInfos.size()+"-------"+productManus.size());

                    Constants.isExitManu= productManus.size() > 0;
                    Constants.isExitAttri=productAttris.size()>0;
                    Constants.isExitMain= productManintenances.size() > 0;
                    Constants.isExitInstall=productInstallInfos.size()>0;

                    mHandler.sendEmptyMessage(Constant.READ_UHFHX_SUCCESS);

                } else {
                    isSuccess = false;

                    //Toast.makeText(MainActivity.this, "识别标签失败！", Toast.LENGTH_SHORT).show();
                    Log.i(TAG, "识别标签失败！tid=" + Constants.Tid);

                }
            }

            @Override
            public void end() {
                mHandler.sendEmptyMessage(Constant.READ_UHFHX_END);
            }

            @Override
            public void timeout() {
                Log.i(TAG, "读取标签时间超时！");
                mHandler.sendEmptyMessage(Constant.READ_UHFHX_TIMEOUT);
            }
        });

    }


    public String readTag(byte[] args) {
        UHFHXAPI.Response response = api.readTypeCTagData(args);
        if (response.result == UHFHXAPI.Response.RESPONSE_PACKET
                && response.data != null) {
            return DataUtils.toHexString(response.data);
        }
        return "null";
    }


}
