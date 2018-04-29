package com.corewise.chenguangpim.table;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：李阳
 * 时间：2018/4/25
 * 描述：产品
 */
public class Product extends DataSupport{



    private String deviceId;//标签id

    private ProductManu productManu; //产品信息

    private ProductAttri productAttri; //产品属性

    private List<ProductManintenance> productManintenance; //产品维修信息

    private ProductInstallInfos productInstallInfos; //产品安装报废信息


    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }


    public ProductManu getProductManu() {
        return productManu;
    }

    public void setProductManu(ProductManu productManu) {
        this.productManu = productManu;
    }

    public ProductAttri getProductAttri() {
        return productAttri;
    }

    public void setProductAttri(ProductAttri productAttri) {
        this.productAttri = productAttri;
    }

    public List<ProductManintenance> getProductManintenance() {
        return productManintenance;
    }

    public void setProductManintenance(List<ProductManintenance> productManintenance) {
        this.productManintenance = productManintenance;
    }

    public ProductInstallInfos getProductInstallInfos() {
        return productInstallInfos;
    }

    public void setProductInstallInfos(ProductInstallInfos productInstallInfos) {
        this.productInstallInfos = productInstallInfos;
    }




        @Override
    public String toString() {
        return "Product{" +
                "productManu=" + productManu +
                ", productAttri=" + productAttri +
                ", productManintenance=" + productManintenance +
                ", productInstallInfos=" + productInstallInfos +
                '}';
    }

}
