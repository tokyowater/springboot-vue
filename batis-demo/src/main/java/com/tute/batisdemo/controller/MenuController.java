package com.tute.batisdemo.controller;

import com.tute.batisdemo.entity.Menu;
import com.tute.batisdemo.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MenuController {
    @Autowired
    MenuMapper menuMapper;

    @GetMapping("/menus")
    public Map<String, java.io.Serializable> getAll(){
    Map<String, java.io.Serializable> map=this.getLists();
       return map;
    }

    public Map<String, java.io.Serializable> getLists(){
        List<Menu> list= menuMapper.getallMenu();
        List<Menu> first=  menuMapper.getfirst();
        List<Menu> second= menuMapper.getsecond();
        List<Menu> report= menuMapper.getReport();
        List<Menu> free=menuMapper.getFree();
        Map<String, Object> submap= new HashMap<>();
        submap.put("id",list.get(0).menu_id);
        submap.put("name",list.get(0).menu_name);
        submap.put("children",first);
        Map<String, Object> submap1=new HashMap<>();
        submap1.put("id",list.get(1).menu_id);
        submap1.put("name",list.get(1).menu_name);
        submap1.put("children",second);
        Map<String, Object> submap2=new HashMap<>();
        submap2.put("id",list.get(13).menu_id);
        submap2.put("name",list.get(13).menu_name);
        submap2.put("children",report);
        Map<String, Object> submap3=new HashMap<>();
        submap3.put("id",list.get(19).menu_id);
        submap3.put("name",list.get(19).menu_name);
        submap3.put("children",free);
        Map<String, java.io.Serializable> datamap=new HashMap<>();
        ArrayList<Map<String, Object>> arrayList=new ArrayList<>();
        arrayList.add(0,submap);
        arrayList.add(1,submap1);
        arrayList.add(2,submap2);
        arrayList.add(3,submap3);
        datamap.put("data",arrayList);
        datamap.put("status",200);
       return datamap;
    }
}
