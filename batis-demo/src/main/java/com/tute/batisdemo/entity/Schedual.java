package com.tute.batisdemo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Schedual {
    @Id
    public  Integer id;
    public  Integer wid;
    public  Integer cid;
    public  String location;
    public  String time;
    public  Integer sc_status;
    public  String name;
    public  String car_name;
    public Integer exist_state;
    public Integer repair_state;
    public  Integer rstate;

}
