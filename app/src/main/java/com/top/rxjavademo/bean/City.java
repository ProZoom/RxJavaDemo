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
public class City {

    private String name; //市
    private List<String> area; //区县
    public void setName(String name) {
         this.name = name;
     }
     public String getName() {
         return name;
     }

    public void setArea(List<String> area) {
         this.area = area;
     }
     public List<String> getArea() {
         return area;
     }

}