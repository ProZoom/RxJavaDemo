package com.corewise.chenguangpim.table;

import org.litepal.crud.DataSupport;

/**
 * 作者：李阳
 * 时间：2018/4/28
 * 描述：安装卸载信息
 */
public class ProductInstallInfos extends DataSupport {


    private String deviceId;//标签id

    private String InstallTime;//安装时间

    private String InstallPerson;//安装责任人

    private String UnInstallTime;//卸载时间

    private String UnInstallPerson;//卸载责任人

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getInstallTime() {
        return InstallTime;
    }

    public void setInstallTime(String installTime) {
        InstallTime = installTime;
    }

    public String getInstallPerson() {
        return InstallPerson;
    }

    public void setInstallPerson(String installPerson) {
        InstallPerson = installPerson;
    }

    public String getUnInstallTime() {
        return UnInstallTime;
    }

    public void setUnInstallTime(String unInstallTime) {
        UnInstallTime = unInstallTime;
    }

    public String getUnInstallPerson() {
        return UnInstallPerson;
    }

    public void setUnInstallPerson(String unInstallPerson) {
        UnInstallPerson = unInstallPerson;
    }

    @Override
    public String toString() {
        return "ProductInstallInfos{" +
                "InstallTime='" + InstallTime + '\'' +
                ", InstallPerson='" + InstallPerson + '\'' +
                ", UnInstallTime='" + UnInstallTime + '\'' +
                ", UnInstallPerson='" + UnInstallPerson + '\'' +
                '}';
    }
}
