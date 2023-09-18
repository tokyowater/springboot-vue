package com.tute.batisdemo.controller;
import com.tute.batisdemo.entity.Cars;
import com.tute.batisdemo.mapper.CarsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CarController {
    @Autowired
    CarsMapper carsMapper;
    @GetMapping()

    @PostMapping("/cars")
    public Map getAll(@RequestBody Map params) {
        Map datamap = new HashMap();
        datamap.put("total", carsMapper.getTotal());
        Map data = new HashMap();
        Integer num=Integer.parseInt(params.get("num").toString());
        Integer size=Integer.parseInt(params.get("size").toString());
        Integer pagenum=(num-1)*size;
        List<Cars> getall = carsMapper.getPageCars(pagenum,size);
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
            }else  if(getall.get(i).repair_state== 2){
                listmap.put("re_state", "loading");
            }
            else  if(getall.get(i).repair_state== 3){
                listmap.put("re_state", "checking");
            }
            list.add(i, listmap);
        }
        datamap.put("cars", list);
        data.put("data", datamap);
        data.put("status", 200);
        return data;
    }

    @PostMapping("/cars/{id}&{name}")
    public Map addCar(@PathVariable("id") Integer id, @PathVariable("name") String name) {
        Map datamap = new HashMap();
        int rookie = carsMapper.addCar(id, name);
        if (rookie == 1) {
            datamap.put("id", id);
            datamap.put("name", name);
        }
        Map data = new HashMap();
        data.put("data", datamap);
        data.put("status", 201);
        return data;
    }

    @GetMapping("/cars/{name}")
    public Map getCarsByname(@PathVariable("name") String name) {
        Map datamap = new HashMap();
        Map data = new HashMap();
        List<Cars> getall = carsMapper.getCarByname("%" + name + "%");
        datamap.put("total", getall.size());
        ArrayList list = new ArrayList();
        for (int i = 0; i <= getall.size() - 1; i++) {
            Map listmap = new HashMap();
            listmap.put("id", getall.get(i).car_id);
            listmap.put("name", getall.get(i).car_name);
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

    @DeleteMapping("/cars/del/{id}")
    public Map DeleteById(@PathVariable("id") Integer id) {
        Integer delete = carsMapper.deleteCarById(id);
        Map data = new HashMap();
        if (delete == 1) {
            data = this.getAllCars();
        }
        return data;
    }
    @GetMapping("/car/{id}")
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
    @PutMapping("car/put/{id}")
    public  Map updateById(@PathVariable("id") Integer id,@RequestBody Map<String,Object> parms){
        String name=parms.get("name").toString();
        int update=carsMapper.updateCarById(name,id);
        Map data=new HashMap();
        if(update==1){
            data=this.getAllCars();
        }
        return  data;
    }
    @PostMapping("cars/check/{id}")
    public  Map sendCheck(@PathVariable("id")Integer id,@RequestBody Map params){
        Map date=new HashMap();
        String stime=params.get("stime").toString();
        Integer wid=Integer.parseInt(params.get("wid").toString());
        int check=carsMapper.createCheck(id,wid,stime);
        if(check==1){
            carsMapper.updatecheck(id);
            date.put("status",200);
        }
       return date;
    }
    public Map getAllCars() {
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
            }else  if(getall.get(i).repair_state== 2){
                listmap.put("re_state", "loading");
            }
            list.add(i, listmap);
        }
        datamap.put("cars", list);
        data.put("data", datamap);
        data.put("status", 200);
        return data;
    }
}
