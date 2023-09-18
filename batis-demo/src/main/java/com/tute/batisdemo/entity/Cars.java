package com.tute.batisdemo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Cars {
    @Id
    public  Integer car_id;
    public  String car_name;
    public  Integer exist_state;
    public  Integer repair_state;
    public  Integer fuel;
    public  String detail;
    public  Integer list_state;
}
