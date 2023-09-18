package com.tute.batisdemo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
@Data
@Entity
public class Free {
    @Id
    public  Integer id;
    public  Integer wid;
    public String name;
    public  String type;
    public  String reason;
    public  Integer status;
    public  String stime;
    public  String etime;
}
