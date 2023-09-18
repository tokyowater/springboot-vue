package com.tute.batisdemo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Location {
    @Id
    public Integer id;
    public  String name;
    public  Integer resource;
}
