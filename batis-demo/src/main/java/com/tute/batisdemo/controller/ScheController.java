package com.tute.batisdemo.controller;
import com.tute.batisdemo.entity.Cars;
import com.tute.batisdemo.entity.Schedual;
import com.tute.batisdemo.entity.Workers;
import com.tute.batisdemo.mapper.CarsMapper;
import com.tute.batisdemo.mapper.ScheMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ScheController {
    @Autowired
    private CarsMapper carsMapper;
    @Autowired
    private ScheMapper scmapper;
    @PostMapping("/scheduals")
    public Map<String, Object> getallSc(@RequestBody Map params){
        Map<String, Object> datamap = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        Integer num=Integer.parseInt(params.get("num").toString());
        Integer size=Integer.parseInt(params.get("size").toString());
        Integer pagenum=(num-1)*size;
        List<Schedual> getall = scmapper.getPageSc(pagenum,size);
        List<Workers> getworkers=scmapper.getavalibleWorkers();
        Integer total=scmapper.getTotal();
        ArrayList<Map<String, java.io.Serializable>> list = new ArrayList<>();
        for (int i = 0; i <= getall.size() - 1; i++) {
            Map<String, java.io.Serializable> listmap = new HashMap<>();
            listmap.put("id", getall.get(i).id);
            listmap.put("wid", getall.get(i).wid);
            listmap.put("wname",getall.get(i).name);
            listmap.put("cid", getall.get(i).cid);
            listmap.put("cname",getall.get(i).car_name);
            listmap.put("time", getall.get(i).time);
            listmap.put("location", getall.get(i).location);
            if (getall.get(i).sc_status == 1) {
                listmap.put("state", true);
            } else if (getall.get(i).sc_status == 0) {
                listmap.put("state", false);
            }
            list.add(i, listmap);
        }
        datamap.put("scheduals", list);
        datamap.put("workerlists", getworkers);
        datamap.put("total",total);
        data.put("data", datamap);
        data.put("status", 200);
        return data;
    }

    @PostMapping("/addOrders/{wid}")
    public Map addOrder(@PathVariable("wid") Integer wid,@RequestBody Map<String,Object> params) {
        String time=params.get("time").toString();
        String location=params.get("location").toString();
        int insert=scmapper.addScheduals(wid,time,location);
        Map data=new HashMap();
        if(insert==1){
            data=this.getSc();
        }
        return  data;
    }
    @GetMapping("/editOrder/{id}")
    public Map<String, Object> getOrderById(@PathVariable("id") Integer id){
        List<Schedual> sc=scmapper.getOrderById(id);
        Map<String, java.io.Serializable> datamap=new HashMap<>();
        datamap.put("id",sc.get(0).id);
        datamap.put("wid",sc.get(0).wid);
        datamap.put("wname",sc.get(0).name);
        datamap.put("time",sc.get(0).time);
        datamap.put("location",sc.get(0).location);
        Map<String, Object> data=new HashMap<>();
        data.put("data",datamap);
        data.put("status",200);
        return data;
    }
    @PutMapping("order/put/{id}")
    public  Map updateById(@PathVariable("id") Integer id,@RequestBody Map<String,Object> parms){
        String time=parms.get("time").toString();
        Integer wid=Integer.parseInt(parms.get("wid").toString());
        String location=parms.get("location").toString();
        int update=scmapper.updateOrderById(time,location,wid,id);
        Map data=new HashMap();
        if(update==1){
            data=this.getSc();
        }
        return  data;
    }
    @DeleteMapping("/order/del/{id}")
    public Map DeleteById(@PathVariable("id") Integer id) {
        Integer delete = scmapper.deleteOrderById(id);
        Map data = new HashMap();
        if (delete == 1) {
            data = this.getSc();
        }
        return data;
    }
    @GetMapping("/orders/search/{id}")
    public Map<String, Object> getScById(@PathVariable("id") Integer id){
        Map<String, ArrayList<Map<String, java.io.Serializable>>> datamap = new HashMap<>();
        Map<String, Object> data = new HashMap<String, Object>();
        List<Schedual> getall = scmapper.getOrderByWid(id);
        ArrayList<Map<String, java.io.Serializable>> list = new ArrayList<Map<String, java.io.Serializable>>();
        for (int i = 0; i <= getall.size() - 1; i++) {
            Map<String, java.io.Serializable> listmap = new HashMap<>();
            listmap.put("id", getall.get(i).id);
            listmap.put("wid", getall.get(i).wid);
            listmap.put("wname",getall.get(i).name);
            listmap.put("cid", getall.get(i).cid);
            listmap.put("cname",getall.get(i).car_name);
            listmap.put("time", getall.get(i).time);
            listmap.put("location", getall.get(i).location);
            if (getall.get(i).sc_status == 1) {
                listmap.put("state", true);
            } else if (getall.get(i).sc_status == 0) {
                listmap.put("state", false);
            }
            list.add(i, listmap);
        }
        datamap.put("scheduals", list);
        data.put("data", datamap);
        data.put("status", 200);
        return data;
    }

    @GetMapping("/orders/search/time/{time}")
    public Map<String, Object> getScByTime(@PathVariable("time") String time){
        Map<String, ArrayList<Map<String, java.io.Serializable>>> datamap = new HashMap<>();
        Map<String, Object> data = new HashMap<String, Object>();
        List<Schedual> getall = scmapper.getscBytime(time+"%");
        Integer total=scmapper.getDateTotal(time+"%");
        ArrayList<Map<String, java.io.Serializable>> list = new ArrayList<Map<String, java.io.Serializable>>();
        for (int i = 0; i <= getall.size() - 1; i++) {
            Map<String, java.io.Serializable> listmap = new HashMap<>();
            listmap.put("id", getall.get(i).id);
            listmap.put("wid", getall.get(i).wid);
            listmap.put("wname",getall.get(i).name);
            listmap.put("cid", getall.get(i).cid);
            listmap.put("cname",getall.get(i).car_name);
            listmap.put("time", getall.get(i).time);
            listmap.put("location", getall.get(i).location);
            if (getall.get(i).sc_status == 1) {
                listmap.put("state", true);
            } else if (getall.get(i).sc_status == 0) {
                listmap.put("state", false);
            }
            list.add(i, listmap);
        }
        datamap.put("scheduals", list);
        data.put("data", datamap);
        data.put("total",total);
        data.put("status", 200);
        return data;
    }
    @PostMapping("/scheduals/{id}")
    public Map<String, Object> getPageScByWid(@PathVariable("id") Integer id, @RequestBody Map params){
        Map<String, java.io.Serializable> datamap = new HashMap<>();
        Map<String, Object> data = new HashMap<String, Object>();
        Integer num=Integer.parseInt(params.get("num").toString());
        Integer size=Integer.parseInt(params.get("size").toString());
        Integer pagenum=(num-1)*size;
        List<Schedual> getall = scmapper.getPagedOrderByWid(id,pagenum,size);
        Integer total=scmapper.getWidTotal(id);
        ArrayList<Map<String, java.io.Serializable>> list = new ArrayList<>();
        for (int i = 0; i <= getall.size() - 1; i++) {
            Map<String, java.io.Serializable> listmap = new HashMap<>();
            listmap.put("id", getall.get(i).id);
            listmap.put("wid", getall.get(i).wid);
            listmap.put("wname",getall.get(i).name);
            listmap.put("cid", getall.get(i).cid);
            listmap.put("cname",getall.get(i).car_name);
            listmap.put("time", getall.get(i).time);
            listmap.put("location", getall.get(i).location);
            if (getall.get(i).sc_status == 1) {
                listmap.put("state", true);
            } else if (getall.get(i).sc_status == 0) {
                listmap.put("state", false);
            } else if(getall.get(i).sc_status == 2){
                listmap.put("state", "working");
            }
            list.add(i, listmap);
        }
        datamap.put("scheduals", list);
        datamap.put("total",total);
        data.put("data", datamap);
        if(list.isEmpty()){
            data.put("status",400);
        } else {
            data.put("status", 200);
        }
        return data;
    }

    @GetMapping("scheduals/apply/{id}")
    public Map<String, Object> getlistByid(@PathVariable("id") Integer id){
        Map<String, Object> data = new HashMap<>();
        List<Schedual> getall = scmapper.getOrderById(id);
            Map<String, Object> listmap = new HashMap<>();
            listmap.put("id", getall.get(0).id);
            listmap.put("wid", getall.get(0).wid);
            listmap.put("wname",getall.get(0).name);
            listmap.put("cid", getall.get(0).cid);
            listmap.put("cname",getall.get(0).car_name);
            listmap.put("time", getall.get(0).time);
            listmap.put("location", getall.get(0).location);
            listmap.put("carlist",this.getcarlist());
            if (getall.get(0).sc_status == 1) {
                listmap.put("state", true);
            } else if (getall.get(0).sc_status == 0) {
                listmap.put("state", false);
            }
            if(getall.get(0).exist_state==0){
                listmap.put("exist", false);
            }else if(getall.get(0).exist_state==1){
                listmap.put("exist", true);
            }
            if(getall.get(0).repair_state==0){
                listmap.put("repair", false);
            }else if(getall.get(0).repair_state==1){
                listmap.put("repair", true);
        }
        data.put("data", listmap);
        data.put("status", 200);
        return data;
    }

    @PutMapping("scheduals/apply/put/{id}")
    public Map<String, Object> update(@PathVariable("id") Integer id, @RequestBody Map<String,Object> parms){
        Integer cid= (Integer) parms.get("cid");
        int update=scmapper.updateById(cid,id);
        int insert=scmapper.text(id);
        Map<String, Object> datamap=new HashMap<>();
        if(update==1){
            if(insert==1){
               Map data=this.getSc();
                Integer wid= (Integer) data.get("wid");
                datamap=this.getScByWid(wid);
            }
        }
        return  datamap;
    }
    @GetMapping("/scheduals/search/time/{time}")
    public Map<String, Object> getPlanByTime(@PathVariable("time") String time,@RequestParam(value = "wid") Integer wid){
        Map<String, ArrayList<Map<String, java.io.Serializable>>> datamap = new HashMap<>();
        Map<String, Object> data = new HashMap<String, Object>();
        List<Schedual> getall = scmapper.getPlanBytime(time+"%",wid);
        Integer total=scmapper.getPlanTotal(time+"%",wid);
        ArrayList<Map<String, java.io.Serializable>> list = new ArrayList<Map<String, java.io.Serializable>>();
        for (int i = 0; i <= getall.size() - 1; i++) {
            Map<String, java.io.Serializable> listmap = new HashMap<>();
            listmap.put("id", getall.get(i).id);
            listmap.put("wid", getall.get(i).wid);
            listmap.put("wname",getall.get(i).name);
            listmap.put("cid", getall.get(i).cid);
            listmap.put("cname",getall.get(i).car_name);
            listmap.put("time", getall.get(i).time);
            listmap.put("location", getall.get(i).location);
            if (getall.get(i).sc_status == 1) {
                listmap.put("state", true);
            } else if (getall.get(i).sc_status == 0) {
                listmap.put("state", false);
            } else if (getall.get(i).sc_status==2) {
                listmap.put("state", "working");
            }
            list.add(i, listmap);
        }
        datamap.put("scheduals", list);
        data.put("data", datamap);
        data.put("total",total);
        data.put("status", 200);
        return data;
    }
    public List<Cars> getcarlist(){
        List<Cars> list=carsMapper.getallCars();
        for (int i=0;i<=list.size()-1;i++){
            if(list.get(i).exist_state == 1 || list.get(i).repair_state==1){
                list.remove(i);
                i--;
            }
        }
        list.remove(0);
        return list;
    }
    public Map getSc(){
        Map datamap = new HashMap<>();
        Map data = new HashMap<>();
        List<Schedual> getall = scmapper.getallSc();
        List<Workers> getworkers=scmapper.getavalibleWorkers();
        ArrayList list = new ArrayList();
        for (int i = 0; i <= getall.size() - 1; i++) {
            Map listmap = new HashMap<>();
            listmap.put("id", getall.get(i).id);
            listmap.put("wid", getall.get(i).wid);
            listmap.put("wname",getall.get(i).name);
            listmap.put("cid", getall.get(i).cid);
            listmap.put("cname",getall.get(i).car_name);
            listmap.put("time", getall.get(i).time);
            listmap.put("location", getall.get(i).location);
            if (getall.get(i).sc_status == 1) {
                listmap.put("state", true);
            } else if (getall.get(i).sc_status == 0) {
                listmap.put("state", false);
            }
            list.add(i, listmap);
        }
        datamap.put("scheduals", list);
        datamap.put("workerlists", getworkers);
        data.put("data", datamap);
        data.put("status", 200);
        return data;
    }

    public Map<String, Object> getScByWid(@PathVariable("id") Integer id){
        Map<String, ArrayList<Map<String, java.io.Serializable>>> datamap = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        List<Schedual> getall = scmapper.getOrderByWid(id);
        ArrayList<Map<String, java.io.Serializable>> list = new ArrayList<>();
        for (int i = 0; i <= getall.size() - 1; i++) {
            Map<String, java.io.Serializable> listmap = new HashMap<>();
            listmap.put("id", getall.get(i).id);
            listmap.put("wid", getall.get(i).wid);
            listmap.put("wname",getall.get(i).name);
            listmap.put("cid", getall.get(i).cid);
            listmap.put("cname",getall.get(i).car_name);
            listmap.put("time", getall.get(i).time);
            listmap.put("location", getall.get(i).location);
            if (getall.get(i).sc_status == 1) {
                listmap.put("state", true);
            } else if (getall.get(i).sc_status == 0) {
                listmap.put("state", false);
            }
            list.add(i, listmap);
        }
        datamap.put("scheduals", list);
        data.put("data", datamap);
        data.put("status", 200);
        return data;
    }
}
