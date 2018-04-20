/**
  * Copyright 2018 bejson.com 
  */
package com.top.rxjavademo.bean;
import java.util.List;

/**
 * Auto-generated: 2018-04-20 17:8:57
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class ProvinceBean {

    private String name;//省市自治区
    private List<City> city;//该省市自治区下的城市

    public void setName(String name) {
         this.name = name;
     }
     public String getName() {
         return name;
     }

    public void setCity(List<City> city) {
         this.city = city;
     }
     public List<City> getCity() {
         return city;
     }

}