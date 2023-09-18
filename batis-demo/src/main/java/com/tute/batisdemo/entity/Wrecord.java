package com.tute.batisdemo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
@Data
@Entity
public class Wrecord {
    @Id
    public  Integer id;
    public  String rtime;
    public Integer nownum;
    public  Integer wid;
    public  Integer cid;
    public  String name;
    public  String car_name;
    public String time;
    public  String location;
   public  Integer rstate;
}
