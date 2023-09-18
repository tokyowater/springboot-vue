package com.tute.batisdemo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
@Data
@Entity
public class Repair {
    @Id
    public Integer id;
    public  Integer cid;
    public  String car_name;
    public  Integer wid;
    public  String name;
    public  String detail;
    public Integer list_state;
    public  String finishtime;
    public  String solve;
    public  Integer fuel;
    public  Integer price;
}
