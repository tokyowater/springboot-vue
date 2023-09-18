package com.tute.batisdemo.controller;

import com.tute.batisdemo.entity.Cars;
import com.tute.batisdemo.entity.CheckCar;
import com.tute.batisdemo.entity.Repair;
import com.tute.batisdemo.entity.Workers;
import com.tute.batisdemo.mapper.CarsMapper;
import com.tute.batisdemo.mapper.RepairMapper;
import com.tute.batisdemo.mapper.UsersMapper;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.GeneratorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class EngineerController {
    @Autowired
  private   CarsMapper carsMapper;
    @Autowired
    private RepairMapper repairMapper;
    @Autowired
    private UsersMapper usersMapper;
    @GetMapping("/engineer/{id}")
    public Map getCarById(@PathVariable("id") Integer id){
        List<Cars> cars=carsMapper.getCarById(id);
        Map datamap=new HashMap();
        datamap.put("id",cars.get(0).car_id);
        datamap.put("name",cars.get(0).car_name);
        datamap.put("fuel",cars.get(0).fuel);
        Map data=new HashMap();
        data.put("data",datamap);
        data.put("status",200);
        return data;
    }
    @PutMapping("/engineer/put/{id}")
    public  Map updateById(@PathVariable("id") Integer id,@RequestBody Map<String,Object> parms){
        Integer fuel= Integer.parseInt(parms.get("fuel").toString());
        int update=carsMapper.updateFuelById(fuel,id);
        Map data=new HashMap();
        if(update==1){
            data=this.getAll();
        }
        return  data;
    }
    @GetMapping("/repair/{id}")
    public Map getRepairById(@PathVariable("id") Integer id){
        List<Cars> cars=repairMapper.getListById(id);
        Map datamap=new HashMap();
        datamap.put("id",cars.get(0).car_id);
        datamap.put("name",cars.get(0).car_name);
        datamap.put("fuel",cars.get(0).fuel);
        datamap.put("detail",cars.get(0).detail);
        Map data=new HashMap();
        data.put("data",datamap);
        data.put("status",200);
        return data;
    }
    @PutMapping("/repair/put/{id}")
    public  Map updateRepair(@PathVariable("id") Integer id, @RequestBody Map<String,Object> params){
        Integer wid= Integer.parseInt(params.get("wid").toString());
        int update=repairMapper.updateList(wid,id);
        Map data=new HashMap();
        if(update==1){
            data=this.getAll();
        }
        return  data;
    }
    @PostMapping("/repaircars")
    public  Map getPagedRepairCars(@RequestBody Map params){
        Map datamap = new HashMap();
        Map data = new HashMap();
        Integer num=Integer.parseInt(params.get("num").toString());
        Integer size=Integer.parseInt(params.get("size").toString());
        Integer pagenum=(num-1)*size;
        Integer wid=Integer.parseInt(params.get("wid").toString());
        Integer total=repairMapper.getListTotal(wid);
        List<Repair> getall = repairMapper.getPagedListByWId(wid,pagenum,size);
        ArrayList list = new ArrayList();
        for (int i = 0; i <= getall.size() - 1; i++) {
            Map listmap = new HashMap();
            listmap.put("id", getall.get(i).id);
            listmap.put("cid",getall.get(i).cid);
            listmap.put("cname", getall.get(i).car_name);
            listmap.put("wid", getall.get(i).wid);
            listmap.put("wname", getall.get(i).name);
            listmap.put("detail", getall.get(i).detail);
            listmap.put("fuel", getall.get(i).fuel);
            if (getall.get(i).list_state == 1) {
                listmap.put("state", true);
            } else if (getall.get(i).list_state == 0) {
                listmap.put("state", false);
            } else if(getall.get(i).list_state==2){
                listmap.put("state","finished");
            }
            listmap.put("price",getall.get(i).price);
            list.add(i, listmap);
        }
        datamap.put("repairlist", list);
        datamap.put("total",total);
        data.put("data", datamap);
        data.put("status", 200);
        return data;
    }
    @GetMapping("/repaircars/{id}")
    public  Map getRepairCarsById(@PathVariable Integer id){
        List<Repair> cars=repairMapper.getRepairCar(id);
        Map datamap=new HashMap();
        datamap.put("id", cars.get(0).id);
        datamap.put("cid",cars.get(0).cid);
        datamap.put("cname", cars.get(0).car_name);
        datamap.put("wname", cars.get(0).name);
        datamap.put("detail", cars.get(0).detail);
        datamap.put("fuel", cars.get(0).fuel);
        Map data=new HashMap();
        data.put("data",datamap);
        data.put("status",200);
        return data;
    }
    @PutMapping("/repaircars/put/{id}")
    public  Map updateRepairCars(@PathVariable("id") Integer id, @RequestBody Map<String,Object> params){
        Integer fuel= Integer.parseInt(params.get("fuel").toString());
        String solve=params.get("solve").toString();
        String finishtime=params.get("finishtime").toString();
        Integer price=Integer.parseInt(params.get("price").toString());
       repairMapper.updateCars(finishtime,solve,price,fuel,id);
           Map data=this.getAll();
        return  data;
    }

    public  Map getRepairCars(@RequestParam(value = "wid") Integer wid){
        Map datamap = new HashMap();
        Map data = new HashMap();
        List<Repair> getall = repairMapper.getListByWId(wid);
        ArrayList list = new ArrayList();
        for (int i = 0; i <= getall.size() - 1; i++) {
            Map listmap = new HashMap();
            listmap.put("id", getall.get(i).id);
            listmap.put("cid",getall.get(i).cid);
            listmap.put("cname", getall.get(i).car_name);
            listmap.put("wid", getall.get(i).wid);
            listmap.put("wname", getall.get(i).name);
            listmap.put("detail", getall.get(i).detail);
            listmap.put("fuel", getall.get(i).fuel);
            if (getall.get(i).list_state == 1) {
                listmap.put("state", true);
            } else if (getall.get(i).list_state == 0) {
                listmap.put("state", false);
            }
            list.add(i, listmap);
        }
        datamap.put("repairlist", list);
        data.put("data", datamap);
        data.put("status", 200);
        return data;
    }
    @GetMapping("/enginfo")
    public Map getEnginfo(@RequestParam(value = "id") Integer id){
        Map datamap = new HashMap();
        Map data = new HashMap();
        List<Workers> getall = usersMapper.getEngWorkers(id);
        ArrayList list = new ArrayList();
        for (int i = 0; i <= getall.size() - 1; i++) {
            Map listmap = new HashMap();
            listmap.put("id", getall.get(i).id);
            listmap.put("name", getall.get(i).name);
            listmap.put("email", getall.get(i).email);
            listmap.put("mobile", getall.get(i).mobile);
            listmap.put("role_name", getall.get(i).role_name);
            list.add(i, listmap);
        }
        datamap.put("info", list);
        data.put("data", datamap);
        data.put("status", 200);
        return data;
    }
    @GetMapping("/enginfo/{id}")
    public Map showEnginfo(@PathVariable(value = "id") Integer id){
        Map data = new HashMap();
        List<Workers> getall = usersMapper.getEngWorkers(id);
        Map listmap = new HashMap();
        listmap.put("id", getall.get(0).id);
        listmap.put("name", getall.get(0).name);
        listmap.put("email", getall.get(0).email);
        listmap.put("mobile", getall.get(0).mobile);
        listmap.put("role_name", getall.get(0).role_name);
        data.put("data", listmap);
        data.put("status", 200);
        return data;
    }

    @PutMapping("/enginfo/put/{id}")
    public  Map updateInfoById(@PathVariable("id") Integer id,@RequestBody Map<String,Object> parms){
        String name=parms.get("name").toString();
        String email=parms.get("email").toString();
        String mobile=parms.get("mobile").toString();
        int update=usersMapper.updateEngInfo(name,email,mobile,id);
        Map data=new HashMap();
        if(update==1){
            data=this.getAllusers();
        }
        return  data;
    }
    @PostMapping("/checkcars")
    public  Map getPagedCheckCars(@RequestBody Map params){
        Map datamap = new HashMap();
        Map data = new HashMap();
        Integer num=Integer.parseInt(params.get("num").toString());
        Integer size=Integer.parseInt(params.get("size").toString());
        Integer pagenum=(num-1)*size;
        Integer wid=Integer.parseInt(params.get("wid").toString());
        Integer total=repairMapper.getCheckListTotal(wid);
        List<CheckCar> getall = repairMapper.getPagedCheckListByWId(wid,pagenum,size);
        ArrayList list = new ArrayList();
        for (int i = 0; i <= getall.size() - 1; i++) {
            Map listmap = new HashMap();
            listmap.put("id", getall.get(i).id);
            listmap.put("cid",getall.get(i).cid);
            listmap.put("cname", getall.get(i).car_name);
            listmap.put("wid", getall.get(i).wid);
            listmap.put("wname", getall.get(i).name);
            listmap.put("solve", getall.get(i).solve);
            listmap.put("fuel", getall.get(i).fuel);
            if (getall.get(i).state == 1) {
                listmap.put("state", true);
            } else if (getall.get(i).state == 0) {
                listmap.put("state", false);
            } else if(getall.get(i).state==2){
                listmap.put("state","finished");
            }
            listmap.put("price",getall.get(i).price);
            list.add(i, listmap);
        }
        datamap.put("checklist", list);
        datamap.put("total",total);
        data.put("data", datamap);
        data.put("status", 200);
        return data;
    }
    @GetMapping("/checkcars/{id}")
    public  Map getCheckCarsById(@PathVariable Integer id){
        List<CheckCar> cars=repairMapper.getCheckCar(id);
        Map datamap=new HashMap();
        datamap.put("id", cars.get(0).id);
        datamap.put("cid",cars.get(0).cid);
        datamap.put("cname", cars.get(0).car_name);
        datamap.put("wname", cars.get(0).name);
        datamap.put("fuel", cars.get(0).fuel);
        datamap.put("price",cars.get(0).price);
        Map data=new HashMap();
        data.put("data",datamap);
        data.put("status",200);
        return data;
    }
    @PutMapping("/checkcars/put/{id}")
    public  Map updateCheckCars(@PathVariable("id") Integer id, @RequestBody Map<String,Object> params){
        Integer fuel= Integer.parseInt(params.get("fuel").toString());
        String solve=params.get("solve").toString();
        String etime=params.get("etime").toString();
        Integer price= Integer.parseInt(params.get("price").toString());
        repairMapper.updateCheckCars(etime,solve,price,fuel,id);
        Map data=this.getAll();
        return  data;
    }
    public Map getAll() {
        Map datamap = new HashMap();
        datamap.put("total", carsMapper.getTotal());
        Map data = new HashMap();
        List<Cars> getall = carsMapper.getallCars();
        getall.remove(0);
        ArrayList list = new ArrayList();
        for (int i = 0; i <= getall.size() - 1; i++) {
            Map listmap = new HashMap();
            listmap.put("id", getall.get(i).car_id);
            listmap.put("name", getall.get(i).car_name);
            listmap.put("fuel",getall.get(i).fuel);
            if (getall.get(i).exist_state == 1) {
                listmap.put("ex_state", true);
            } else if (getall.get(i).exist_state == 0) {
                listmap.put("ex_state", false);
            }
            if (getall.get(i).repair_state == 1) {
                listmap.put("re_state", true);
            } else if (getall.get(i).repair_state == 0) {
                listmap.put("re_state", false);
            }
            list.add(i, listmap);
        }
        datamap.put("cars", list);
        data.put("data", datamap);
        data.put("status", 200);
        return data;
    }
    public Map getAllusers(){
        Map datamap = new HashMap();
        datamap.put("total", usersMapper.getTotal());
        Map data = new HashMap();
        List<Workers> getall = usersMapper.getallWorkers();
        ArrayList list = new ArrayList();
        for (int i = 0; i <= getall.size() - 1; i++) {
            Map listmap = new HashMap();
            listmap.put("id", getall.get(i).id);
            listmap.put("name", getall.get(i).name);
            listmap.put("email", getall.get(i).email);
            listmap.put("mobile", getall.get(i).mobile);
            listmap.put("role_name", getall.get(i).role_name);
            if (getall.get(i).status == 1) {
                listmap.put("state", true);
            } else if (getall.get(i).status == 0) {
                listmap.put("state", false);
            }
            list.add(i, listmap);
        }
        datamap.put("uesrs", list);
        data.put("data", datamap);
        data.put("status", 200);
        return data;
    }
}
