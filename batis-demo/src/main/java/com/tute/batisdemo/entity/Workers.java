package com.tute.batisdemo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.criteria.CriteriaBuilder;

@Data
@Entity
public class Workers {
    @Id
    public Integer id;
    public  String name;
    public String mobile;
    public String email;
    public Integer status;
    public  Integer role_id;
    public  String role_name;
    public  String picname;
    public  String url;
    public  String password;
}

