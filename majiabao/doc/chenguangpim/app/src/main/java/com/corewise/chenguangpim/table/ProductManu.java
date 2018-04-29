package com.corewise.chenguangpim.table;

import org.litepal.crud.DataSupport;

/**
 * 作者：李阳
 * 时间：2018/4/28
 * 描述：产品制造封装
 */
public class ProductManu extends DataSupport {


    private String deviceId;//标签id


    private String name;//产品名称
    private String ExpansionJointModel;//膨胀节型号
    private String SerialNumber;//出厂编号
    private String ExecutiveStandard;//执行标准
    private String Dimensions;//外形尺寸
    private String TotalWeight;//总重量
    private String ManufacturerName;//制造厂名称
    private String ManufacturerAdress;//制造厂地址
    private String ManufacturerDate;//制造日期


    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExpansionJointModel() {
        return ExpansionJointModel;
    }

    public void setExpansionJointModel(String expansionJointModel) {
        ExpansionJointModel = expansionJointModel;
    }

    public String getSerialNumber() {
        return SerialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        SerialNumber = serialNumber;
    }

    public String getExecutiveStandard() {
        return ExecutiveStandard;
    }

    public void setExecutiveStandard(String executiveStandard) {
        ExecutiveStandard = executiveStandard;
    }

    public String getDimensions() {
        return Dimensions;
    }

    public void setDimensions(String dimensions) {
        Dimensions = dimensions;
    }

    public String getTotalWeight() {
        return TotalWeight;
    }

    public void setTotalWeight(String totalWeight) {
        TotalWeight = totalWeight;
    }

    public String getManufacturerName() {
        return ManufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        ManufacturerName = manufacturerName;
    }

    public String getManufacturerAdress() {
        return ManufacturerAdress;
    }

    public void setManufacturerAdress(String manufacturerAdress) {
        ManufacturerAdress = manufacturerAdress;
    }

    public String getManufacturerDate() {
        return ManufacturerDate;
    }

    public void setManufacturerDate(String manufacturerDate) {
        ManufacturerDate = manufacturerDate;
    }

    @Override
    public String toString() {
        return "ProductManu{" +
                "name='" + name + '\'' +
                ", ExpansionJointModel='" + ExpansionJointModel + '\'' +
                ", SerialNumber='" + SerialNumber + '\'' +
                ", ExecutiveStandard='" + ExecutiveStandard + '\'' +
                ", Dimensions='" + Dimensions + '\'' +
                ", TotalWeight='" + TotalWeight + '\'' +
                ", ManufacturerName='" + ManufacturerName + '\'' +
                ", ManufacturerAdress='" + ManufacturerAdress + '\'' +
                ", ManufacturerDate='" + ManufacturerDate + '\'' +
                '}';
    }

    public String[] toStringArray(){


        return new String[]{deviceId,name,ExpansionJointModel,SerialNumber,ExecutiveStandard,Dimensions,TotalWeight,ManufacturerName,ManufacturerAdress,ManufacturerDate};
    }
}
