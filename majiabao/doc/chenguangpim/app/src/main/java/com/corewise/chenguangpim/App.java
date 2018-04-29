package com.corewise.chenguangpim;

import android.Manifest;
import android.app.Application;
import android.media.MediaScannerConnection;
import android.os.Environment;
import android.util.Log;

import org.litepal.LitePal;
import org.litepal.tablemanager.Connector;

import java.io.File;
import java.io.IOException;

import kr.co.namee.permissiongen.PermissionGen;

/**
 * 作者：李阳
 * 时间：2018/4/25
 * 描述：
 */
public class App extends Application {

    private static App app;


    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);
    }

}
