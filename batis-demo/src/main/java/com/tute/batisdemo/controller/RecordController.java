package com.tute.batisdemo.controller;

import com.tute.batisdemo.entity.Repair;
import com.tute.batisdemo.entity.Wrecord;
import com.tute.batisdemo.mapper.RepairMapper;
import com.tute.batisdemo.mapper.ReturnMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class RecordController {
    @Autowired
    private RepairMapper repairMapper;
    @Autowired
    private ReturnMapper returnMapper;

    @PostMapping("/records")
    public Map<String, Object> getPage(@RequestBody Map params) {
        Map<String, java.io.Serializable> datamap = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        Integer num=Integer.parseInt(params.get("num").toString());
        Integer size=Integer.parseInt(params.get("size").toString());
        Integer pagenum=(num-1)*size;
        List<Repair> getall = repairMapper.getPageRepaired(pagenum,size);
        Integer total=repairMapper.getTotal();
        ArrayList<Map<String, java.io.Serializable>> list = new ArrayList<>();
        for (int i = 0; i <= getall.size() - 1; i++) {
            Map<String, java.io.Serializable> listmap = new HashMap<>();
            listmap.put("id", getall.get(i).id);
            listmap.put("cid", getall.get(i).cid);
            listmap.put("cname", getall.get(i).car_name);
            listmap.put("wid", getall.get(i).wid);
            listmap.put("wname", getall.get(i).name);
            listmap.put("detail", getall.get(i).detail);
            listmap.put("fuel", getall.get(i).fuel);
            listmap.put("finishtime", getall.get(i).finishtime);
            listmap.put("solve", getall.get(i).solve);
            if (getall.get(i).list_state == 1) {
                listmap.put("state", true);
            } else if (getall.get(i).list_state == 0) {
                listmap.put("state", false);
            } else if(getall.get(i).list_state==2){
                listmap.put("state","finished");
            }
            list.add(i, listmap);
        }
        datamap.put("repairlist", list);
        datamap.put("total",total);
        data.put("data", datamap);
        data.put("status", 200);
        return data;
    }

    @GetMapping("/records/{id}")
    public Map<String, Object> getRecordById(@PathVariable("id") Integer cid) {
        Map<String, ArrayList<Map<String, java.io.Serializable>>> datamap = new HashMap<>();
        Map<String, Object> data = new HashMap<String, Object>();
        List<Repair> getall = repairMapper.getRepairedById(cid);
        ArrayList<Map<String, java.io.Serializable>> list = new ArrayList<Map<String, java.io.Serializable>>();
        for (int i = 0; i <= getall.size() - 1; i++) {
            Map<String, java.io.Serializable> listmap = new HashMap<>();
            listmap.put("id", getall.get(i).id);
            listmap.put("cid", getall.get(i).cid);
            listmap.put("cname", getall.get(i).car_name);
            listmap.put("wid", getall.get(i).wid);
            listmap.put("wname", getall.get(i).name);
            listmap.put("detail", getall.get(i).detail);
            listmap.put("fuel", getall.get(i).fuel);
            listmap.put("finishtime", getall.get(i).finishtime);
            listmap.put("solve", getall.get(i).solve);
            list.add(i, listmap);
        }
        datamap.put("repairlist", list);
        data.put("data", datamap);
        data.put("status", 200);
        return data;
    }

    @PostMapping("/workerecord")
    public Map<String, Object> getWorkercords(@RequestBody Map params) {
        Map<String, java.io.Serializable> datamap = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        Integer num=Integer.parseInt(params.get("num").toString());
        Integer size=Integer.parseInt(params.get("size").toString());
        Integer pagenum=(num-1)*size;
        List<Wrecord> getall = returnMapper.getPagedRecord(pagenum,size);
        Integer total=returnMapper.getTotal();
        ArrayList<Map<String, java.io.Serializable>> list = new ArrayList<>();
        for (int i = 0; i <= getall.size() - 1; i++) {
            Map<String, java.io.Serializable> listmap = new HashMap<>();
            listmap.put("id", getall.get(i).id);
            listmap.put("cid", getall.get(i).cid);
            listmap.put("cname", getall.get(i).car_name);
            listmap.put("wid", getall.get(i).wid);
            listmap.put("wname", getall.get(i).name);
            listmap.put("nownum", getall.get(i).nownum);
            listmap.put("rtime", getall.get(i).rtime);
            listmap.put("time", getall.get(i).time);
            listmap.put("location", getall.get(i).location);
            if(getall.get(i).rstate==0){
                listmap.put("state",false);
            } else if(getall.get(i).rstate==1){
                listmap.put("state",true);
            } else if(getall.get(i).rstate==2){
                listmap.put("state","working");
            }
            list.add(i, listmap);
        }
        datamap.put("recordlist", list);
        datamap.put("total",total);
        data.put("data", datamap);
        data.put("status", 200);
        return data;
    }
    @GetMapping("/workerecord/search/{id}")
    public Map<String, Object> getScById(@PathVariable("id") Integer id){
        Map<String, ArrayList<Map<String, java.io.Serializable>>> datamap = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        List<Wrecord> getall = returnMapper.getRecordByWid(id);
        ArrayList<Map<String, java.io.Serializable>> list = new ArrayList<>();
        for (int i = 0; i <= getall.size() - 1; i++) {
            Map<String, java.io.Serializable> listmap = new HashMap<>();
            listmap.put("id", getall.get(i).id);
            listmap.put("cid", getall.get(i).cid);
            listmap.put("cname", getall.get(i).car_name);
            listmap.put("wid", getall.get(i).wid);
            listmap.put("wname", getall.get(i).name);
            listmap.put("nownum", getall.get(i).nownum);
            listmap.put("rtime", getall.get(i).rtime);
            listmap.put("time", getall.get(i).time);
            listmap.put("location", getall.get(i).location);
            if(getall.get(i).rstate==0){
                listmap.put("state",false);
            } else if(getall.get(i).rstate==1){
                listmap.put("state",true);
            } else if(getall.get(i).rstate==2){
                listmap.put("state","working");
            }
            list.add(i, listmap);
        }
        datamap.put("scheduals", list);
        data.put("data", datamap);
        data.put("status", 200);
        return data;
    }

    @GetMapping("workerecord/search/time/{time}")
    public Map<String, Object> getScByTime(@PathVariable("time") String time){
        Map<String, ArrayList<Map<String, java.io.Serializable>>> datamap = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        List<Wrecord> getall = returnMapper.getRecordByTime(time+"%");
        Integer total=returnMapper.getRecordCount(time+"%");
        ArrayList<Map<String, java.io.Serializable>> list = new ArrayList<>();
        for (int i = 0; i <= getall.size() - 1; i++) {
            Map<String, java.io.Serializable> listmap = new HashMap<>();
            listmap.put("id", getall.get(i).id);
            listmap.put("cid", getall.get(i).cid);
            listmap.put("cname", getall.get(i).car_name);
            listmap.put("wid", getall.get(i).wid);
            listmap.put("wname", getall.get(i).name);
            listmap.put("nownum", getall.get(i).nownum);
            listmap.put("rtime", getall.get(i).rtime);
            listmap.put("time", getall.get(i).time);
            listmap.put("location", getall.get(i).location);
            if(getall.get(i).rstate==0){
                listmap.put("state",false);
            } else if(getall.get(i).rstate==1){
                listmap.put("state",true);
            } else if(getall.get(i).rstate==2){
                listmap.put("state","working");
            }
            list.add(i, listmap);
        }
        datamap.put("returnlist", list);
        data.put("data", datamap);
        data.put("total",total);
        data.put("status", 200);
        return data;
    }
}
