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
public class EnginMenuController {
    @Autowired
    private MenuMapper menuMapper;
    @GetMapping("/enginMenus")
    public Map getAll(){
        Map map=this.getLists();
        return map;
    }

    private Map getLists() {
        List<Menu> list= menuMapper.getallMenu();
        List<Menu> fourth=menuMapper.getFourth();
        List<Menu> enginfo=menuMapper.getEnginfo();
        List<Menu> free=menuMapper.getEngFree();
        Map submap=new HashMap();
        submap.put("id",list.get(8).menu_id);
        submap.put("name",list.get(8).menu_name);
        submap.put("children",fourth);
        Map submap1=new HashMap();
        submap1.put("id",list.get(15).menu_id);
        submap1.put("name",list.get(15).menu_name);
        submap1.put("children",enginfo);
        Map<String, Object> submap2=new HashMap<>();
        submap2.put("id",list.get(24).menu_id);
        submap2.put("name",list.get(24).menu_name);
        submap2.put("children",free);
        Map datamap=new HashMap();
        ArrayList arrayList=new ArrayList();
        arrayList.add(0,submap);
        arrayList.add(1,submap1);
        arrayList.add(2,submap2);
        datamap.put("data",arrayList);
        datamap.put("status",200);
        return datamap;
    }
}
