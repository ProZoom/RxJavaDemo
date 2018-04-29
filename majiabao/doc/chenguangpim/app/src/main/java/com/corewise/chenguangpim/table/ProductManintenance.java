package com.corewise.chenguangpim.table;

import org.litepal.crud.DataSupport;

/**
 * 作者：李阳
 * 时间：2018/4/28
 * 描述：维修信息
 */
public class ProductManintenance extends DataSupport {


    private String deviceId;//标签id

    private String Content;
    private String Time;
    private String Person;


    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getPerson() {
        return Person;
    }

    public void setPerson(String person) {
        Person = person;
    }

    @Override
    public String toString() {
        return "ProductManintenance{" +
                "Content='" + Content + '\'' +
                ", Time='" + Time + '\'' +
                ", Person='" + Person + '\'' +
                '}';
    }

    public String[] toStringArray() {


        return new String[]{Person, Time, Content};
    }
}
