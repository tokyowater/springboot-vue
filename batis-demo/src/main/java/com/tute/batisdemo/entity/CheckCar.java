package com.tute.batisdemo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CheckCar {
    @Id
    public  Integer id;
    public  Integer cid;
    public  Integer wid;
    public  String name;
    public  String car_name;
    public String stime;
    public  String etime;
    public  String solve;
    public Integer state;
    public  Integer fuel;
    public  Integer price;
}
