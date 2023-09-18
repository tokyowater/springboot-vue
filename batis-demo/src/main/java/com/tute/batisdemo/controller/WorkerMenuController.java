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
public class WorkerMenuController {
    @Autowired
    private MenuMapper menuMapper;
    @GetMapping("/workerMenus")
    public Map getAll(){
        Map map=this.getLists();
        return map;
    }

    private Map getLists() {
        List<Menu> list= menuMapper.getallMenu();
       List<Menu> third=menuMapper.getThird();
       List<Menu> info=menuMapper.getWorkinfo();
       List<Menu> free=menuMapper.getWorkFree();
       List<Menu> carsinfo=menuMapper.getCarsinfo();
        Map submap=new HashMap();
        submap.put("id",list.get(5).menu_id);
        submap.put("name",list.get(5).menu_name);
        submap.put("children",third);
        Map submap1=new HashMap();
        submap1.put("id",list.get(17).menu_id);
        submap1.put("name",list.get(17).menu_name);
        submap1.put("children",info);
        Map<String, Object> submap2=new HashMap<>();
        submap2.put("id",list.get(21).menu_id);
        submap2.put("name",list.get(21).menu_name);
        submap2.put("children",free);
        Map<String, Object> submap3=new HashMap<>();
        submap3.put("id",list.get(28).menu_id);
        submap3.put("name",list.get(28).menu_name);
        submap3.put("children",carsinfo);
        Map datamap=new HashMap();
        ArrayList arrayList=new ArrayList();
        arrayList.add(0,submap);
        arrayList.add(1,submap1);
        arrayList.add(2,submap2);
        arrayList.add(3,submap3);
        datamap.put("data",arrayList);
        datamap.put("status",200);
        return datamap;
    }
}
