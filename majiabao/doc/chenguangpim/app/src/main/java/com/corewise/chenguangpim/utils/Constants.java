package com.corewise.chenguangpim.utils;

import java.util.ArrayList;

/**
 * 作者：李阳
 * 时间：2018/4/28
 * 描述：
 */
public class Constants {

    public static boolean isExitManu=false;
    public static boolean isExitAttri=false;
    public static boolean isExitMain=false;
    public static boolean isExitInstall=false;


    public static boolean isAddInfo=false;

    public static int itemPositionInViewpager=0;

    public static String Tid="null";

    public static String[] ProductManufactureDatas = new String[]{"","","","","","","","","",""};

    public static String[] ProductManufactureDatasHint = new String[]{
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
    };


    public static String[] ProductAttriDatas = new String[]{"","","","","","","","","","","","",""};

     public static String[] ProductAttriDatasHint = new String[]{
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
    };


    public static  ArrayList<String[]> ProductManintenanceDatasIndb = new ArrayList<String[]>();
    public static  ArrayList<String[]> ProductManintenanceDatasAdd = new ArrayList<String[]>();


     public static String[] ProductInstallInfos = new String[]{
            "2012年12月3日",
            "张三",
            "2018年4月6日",
            "李四"
    };



    public static String[] signs=new String[]{
            " Kg",
            " ℃",
            " MPa",
            " mm",
            " °",
            " 次",
            " N/mm",
            " N/mm",
            " L",
    };

}
