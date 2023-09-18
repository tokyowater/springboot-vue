package com.tute.batisdemo.controller;

import com.tute.batisdemo.entity.Location;
import com.tute.batisdemo.entity.Workers;
import com.tute.batisdemo.mapper.ReportMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ReportController {
    @Autowired
    private ReportMapper reportMapper;
    @GetMapping("/report")
    public Map<String, Object> getResources(){
        Map<String, ArrayList<Object>> datamap = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        List<Location> lists = reportMapper.getResources();
        ArrayList<Object> list = new ArrayList<>();
        ArrayList<Object> worknamelist= new ArrayList<>();
        ArrayList<java.io.Serializable> namelist=new ArrayList<>();
        ArrayList<java.io.Serializable> idlist=new ArrayList<>();
        ArrayList<java.io.Serializable> resourcelist= new ArrayList<>();
        ArrayList<Object> datelist=this.getDatelist();
        ArrayList<Object> workerlist=this.getWorkerProfit();
        for (int i = 0; i <= lists.size() - 1; i++) {
            namelist.add(i,lists.get(i).name);
            idlist.add(i, lists.get(i).id);
            resourcelist.add(i, lists.get(i).resource);
        }
        List<Workers> workers=reportMapper.getworkers();
        for (int i=0;i<=workers.size()-1;i++){
            worknamelist.add(i,workers.get(i).name);
        }
        list.add(0,namelist);
        list.add(1,idlist);
        list.add(2,resourcelist);
        datamap.put("reportlist", list);
        datamap.put("namelists",worknamelist);
        datamap.put("datelist",datelist);
        datamap.put("workerlist",workerlist);
        data.put("data", datamap);
        data.put("status", 200);
        return data;
    }
    public ArrayList<Object> getDatelist(){
        ArrayList<java.io.Serializable> firstlist=new ArrayList<java.io.Serializable>();
        firstlist.add(0,reportMapper.getPreset1());
        firstlist.add(1,reportMapper.getLast1());
        firstlist.add(2,reportMapper.getPreLast1());
        ArrayList<java.io.Serializable> secondlist=new ArrayList<>();
        secondlist.add(0,reportMapper.getPreset2());
        secondlist.add(1,reportMapper.getLast2());
        secondlist.add(2,reportMapper.getPreLast2());
        ArrayList<java.io.Serializable> thirdlist=new ArrayList<>();
        thirdlist.add(0,reportMapper.getPreset3());
        thirdlist.add(1,reportMapper.getLast3());
        thirdlist.add(2,reportMapper.getPreLast3());
        ArrayList<Object> datalist=new ArrayList<>();
        datalist.add(0,firstlist);
        datalist.add(1,secondlist);
        datalist.add(2,thirdlist);
        return  datalist;
    }

    public ArrayList<Object> getWorkerProfit(){
        ArrayList<String> locations=new ArrayList<>();
        locations.add(0,"矿坑1号");
        locations.add(1,"矿坑2号");
        locations.add(2,"矿坑3号");
        List<Workers> workers=reportMapper.getworkers();
        ArrayList<Integer> data=new ArrayList<>();
        ArrayList<Integer> data2=new ArrayList<>();
        ArrayList<Integer> data3=new ArrayList<>();
        ArrayList<Object> datas=new ArrayList<>();
        for(int j=0;j<=workers.size()-1;j++){
            Integer profit=reportMapper.getFirstProfit(workers.get(j).id);
            Integer profit2=reportMapper.getSecnondProfit(workers.get(j).id);
            Integer profit3=reportMapper.getThirdProfit(workers.get(j).id);
            data.add(j,profit);
            data2.add(j,profit2);
            data3.add(j,profit3);
        }
        datas.add(0,data);
        datas.add(1,data2);
        datas.add(2,data3);
       return datas;
    }
}
