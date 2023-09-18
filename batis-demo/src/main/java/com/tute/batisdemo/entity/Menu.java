package com.tute.batisdemo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.criteria.CriteriaBuilder;
@Data
@Entity
public class Menu {
    @Id
    public Integer menu_id;
    public String menu_name;
    public Integer menu_cid;
    public String path;
}
