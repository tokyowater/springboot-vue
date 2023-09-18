package com.tute.batisdemo.controller;

import com.tute.batisdemo.entity.Schedual;
import com.tute.batisdemo.mapper.ReturnMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ReturnController {
    @Autowired
    private ReturnMapper returnMapper;

    @PostMapping("/booklist")
    public Map<String, Object> getPagedBookedList(@RequestBody Map params) {
        Map<String, java.io.Serializable> datamap = new HashMap<String, java.io.Serializable>();
        Map<String, Object> data = new HashMap<String, Object>();
        Integer wid=Integer.parseInt(params.get("wid").toString());
        Integer num=Integer.parseInt(params.get("num").toString());
        Integer size=Integer.parseInt(params.get("size").toString());
        Integer pagenum=(num-1)*size;
        List<Schedual> lists = returnMapper.getPagedBooked(wid,pagenum,size);
        Integer total=returnMapper.getPagedBookTotal(wid);
        ArrayList<Map<String, java.io.Serializable>> list = new ArrayList<>();
        for (int i = 0; i <= lists.size() - 1; i++) {
            Map<String, java.io.Serializable> listmap = new HashMap<>();
            listmap.put("id", lists.get(i).id);
            listmap.put("wid", lists.get(i).wid);
            listmap.put("wname", lists.get(i).name);
            listmap.put("cid", lists.get(i).cid);
            listmap.put("cname", lists.get(i).car_name);
            listmap.put("time", lists.get(i).time);
            listmap.put("location", lists.get(i).location);
            listmap.put("rstate", lists.get(i).rstate);
            if (lists.get(i).rstate == 1) {
                listmap.put("state", true);
            } else if (lists.get(i).rstate == 0) {
                listmap.put("state", false);
            } else if (lists.get(i).rstate==2){
                listmap.put("state", "working");
            }
            list.add(i, listmap);
        }
        datamap.put("returnlists", list);
        datamap.put("total", total);
        data.put("data", datamap);
        data.put("status", 200);
        return data;
    }

    @PutMapping("/booklist/{lid}")
    public Map<String, Object> updateList(@PathVariable("lid") Integer lid, @RequestBody Map params) {
        Map<String, Object> data = new HashMap<>();
        String name = params.get("name").toString();
        String rtime = params.get("rtime").toString();
        Integer resource = Integer.parseInt(params.get("resource").toString());
        Integer car_id = (Integer) params.get("car_id");
        Integer wid = (Integer) params.get("wid");
        Integer update = returnMapper.updateResult(rtime, resource, lid, car_id);
        Integer allnum = returnMapper.allnum(name);
        Integer total = returnMapper.updateTotal(allnum, name);
        if (update == 1) {
            if (total == 1) {
                data = this.getBookedList(wid);
            }
        }
        return data;
    }

    @PutMapping("/booklist/wrong/{lid}")
    public Map<String, Object> updateWrongList(@PathVariable("lid") Integer lid, @RequestBody Map params) {
        Map<String, Object> data = new HashMap<>();
        Integer repair = (Integer) params.get("repair");
       String detail=params.get("detail").toString();
        String name = params.get("name").toString();
        String rtime = params.get("rtime").toString();
        Integer resource = Integer.parseInt(params.get("resource").toString());
        Integer car_id = (Integer) params.get("car_id");
        Integer wid = (Integer) params.get("wid");
        Integer update = returnMapper.updateResult(rtime, resource, lid, car_id);
        Integer allnum = returnMapper.allnum(name);
        Integer total = returnMapper.updateTotal(allnum, name);
        if (repair == 0) {
            returnMapper.insertRepair(car_id,detail);
            returnMapper.updateRepair(car_id);
        }
        if (update == 1) {
            if (total == 1) {
                data = this.getBookedList(wid);
            }
        }
        return data;
    }
    @GetMapping("/booklist/search/time/{time}")
    public Map<String, Object> getDateBookedList(@PathVariable("time") String time,@RequestParam Integer wid) {
        Map<String, java.io.Serializable> datamap = new HashMap<String, java.io.Serializable>();
        Map<String, Object> data = new HashMap<String, Object>();
        List<Schedual> lists = returnMapper.getDateBooked(time+"%",wid);
        Integer total=returnMapper.getDateBookTotal(time+"%",wid);
        ArrayList<Map<String, java.io.Serializable>> list = new ArrayList<>();
        for (int i = 0; i <= lists.size() - 1; i++) {
            Map<String, java.io.Serializable> listmap = new HashMap<>();
            listmap.put("id", lists.get(i).id);
            listmap.put("wid", lists.get(i).wid);
            listmap.put("wname", lists.get(i).name);
            listmap.put("cid", lists.get(i).cid);
            listmap.put("cname", lists.get(i).car_name);
            listmap.put("time", lists.get(i).time);
            listmap.put("location", lists.get(i).location);
            listmap.put("rstate", lists.get(i).rstate);
            if (lists.get(i).rstate == 1) {
                listmap.put("state", true);
            } else if (lists.get(i).rstate == 0) {
                listmap.put("state", false);
            } else if (lists.get(i).rstate==2){
                listmap.put("state", "working");
            }
            list.add(i, listmap);
        }
        datamap.put("returnlists", list);
        datamap.put("total", total);
        data.put("data", datamap);
        data.put("status", 200);
        return data;
    }
    public Map<String, Object> getBookedList(@RequestParam Integer wid) {
        Map<String, ArrayList<Map<String, java.io.Serializable>>> datamap = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        List<Schedual> lists = returnMapper.getBooked(wid);
        ArrayList<Map<String, java.io.Serializable>> list = new ArrayList<>();
        for (int i = 0; i <= lists.size() - 1; i++) {
            Map<String, java.io.Serializable> listmap = new HashMap<>();
            listmap.put("id", lists.get(i).id);
            listmap.put("wid", lists.get(i).wid);
            listmap.put("wname", lists.get(i).name);
            listmap.put("cid", lists.get(i).cid);
            listmap.put("cname", lists.get(i).car_name);
            listmap.put("time", lists.get(i).time);
            listmap.put("location", lists.get(i).location);
            listmap.put("rstate", lists.get(i).rstate);
            if (lists.get(i).sc_status == 1) {
                listmap.put("state", true);
            } else if (lists.get(i).sc_status == 0) {
                listmap.put("state", false);
            }
            list.add(i, listmap);
        }
        datamap.put("returnlists", list);
        data.put("data", datamap);
        data.put("status", 200);
        return data;
    }
}

