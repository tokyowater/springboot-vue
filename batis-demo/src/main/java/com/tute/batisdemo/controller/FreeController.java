package com.tute.batisdemo.controller;

import com.tute.batisdemo.entity.Free;
import com.tute.batisdemo.mapper.FreeMapper;
import org.hibernate.annotations.GeneratorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class FreeController {
    @Autowired
    private FreeMapper freeMapper;
    @PostMapping("/ask")
    public  Map CreateFree(@RequestBody Map params){
        Map datamap=new HashMap();
        Integer wid=Integer.parseInt(params.get("wid").toString());
        String type=params.get("type").toString();
        String reason=params.get("reason").toString();
        String stime=params.get("stime").toString();
        String etime=params.get("etime").toString();
        int update=freeMapper.createFree(wid,type,reason,stime,etime);
        if(update==1){
            datamap.put("status",200);
            datamap.put("msg","提交成功");
        }else {
            datamap.put("status",400);
            datamap.put("msg","提交失败");
        }
        return datamap;
    }
    @GetMapping("/getfree")
    public  Map getListById(@RequestParam(value = "wid") Integer wid){
        Map datamap =new HashMap();
        List<Free> free=freeMapper.getListById(wid);
        Map data=new HashMap();
        ArrayList<Map<String, Serializable>> list = new ArrayList<>();
        for (int i = 0; i <= free.size() - 1; i++) {
            Map<String, java.io.Serializable> listmap = new HashMap<>();
            listmap.put("id", free.get(i).id);
            listmap.put("name", free.get(i).name);
            listmap.put("wid",free.get(i).wid);
            listmap.put("type", free.get(i).type);
            listmap.put("reason", free.get(i).reason);
            listmap.put("stime", free.get(i).stime);
            listmap.put("etime", free.get(i).etime);
           listmap.put("state",free.get(i).status);
            list.add(i, listmap);
        }
        data.put("frees", list);
        datamap.put("data", data);
        datamap.put("status", 200);
        return datamap;
    }
    @GetMapping("/getallFree")
    public  Map getList(){
        Map datamap =new HashMap();
        List<Free> free=freeMapper.getList();
        Map data=new HashMap();
        ArrayList<Map<String, Serializable>> list = new ArrayList<>();
        for (int i = 0; i <= free.size() - 1; i++) {
            Map<String, java.io.Serializable> listmap = new HashMap<>();
            listmap.put("id", free.get(i).id);
            listmap.put("name", free.get(i).name);
            listmap.put("wid",free.get(i).wid);
            listmap.put("type", free.get(i).type);
            listmap.put("reason", free.get(i).reason);
            listmap.put("stime", free.get(i).stime);
            listmap.put("etime", free.get(i).etime);
            listmap.put("state",free.get(i).status);
            list.add(i, listmap);
        }
        data.put("frees", list);
        datamap.put("data", data);
        datamap.put("status", 200);
        return datamap;
    }
    @PostMapping("/passfree/{id}")
    public Map pass(@PathVariable("id") Integer id){
        Map data=new HashMap();
        int pass=freeMapper.passfree(id);
        if(pass==1){
            data.put("status",200);
            data.put("msg","已通过该申请");
        }
        return data;
    }
    @PostMapping("/reject/{id}")
    public Map reject(@PathVariable("id") Integer id){
        Map data=new HashMap();
        int reject=freeMapper.rejectfree(id);
        if(reject==1){
            data.put("status",200);
            data.put("msg","已拒绝该申请");
        }
        return data;
    }
}
