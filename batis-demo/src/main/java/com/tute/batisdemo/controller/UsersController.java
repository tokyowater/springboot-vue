package com.tute.batisdemo.controller;

import com.tute.batisdemo.entity.Workers;
import com.tute.batisdemo.mapper.UsersMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
@RestController
public class UsersController {
    @Autowired
    UsersMapper usersMapper;

    @PostMapping("/users")
    public Map<String, Object> getAll(@RequestBody Map params) {
        Map<String, java.io.Serializable> datamap = new HashMap<>();
        Integer num=Integer.parseInt(params.get("num").toString());
        Integer size=Integer.parseInt(params.get("size").toString());
        Integer pagenum=(num-1)*size;
        datamap.put("total", usersMapper.getTotal());
        Map<String, Object> data = new HashMap<>();
        List<Workers> getall = usersMapper.getall(pagenum,size);
        ArrayList<Map<String, java.io.Serializable>> list = new ArrayList<>();
        for (int i = 0; i <= getall.size() - 1; i++) {
            Map<String, java.io.Serializable> listmap = new HashMap<>();
            listmap.put("id", getall.get(i).id);
            listmap.put("name", getall.get(i).name);
            listmap.put("email", getall.get(i).email);
            listmap.put("mobile", getall.get(i).mobile);
            listmap.put("role_name", getall.get(i).role_name);
            if (getall.get(i).status == 2) {
                listmap.put("state", true);
            } else if (getall.get(i).status == 0) {
                listmap.put("state", false);
            }
            list.add(i, listmap);
        }
        datamap.put("users", list);
        data.put("data", datamap);
        data.put("status", 200);
        return data;
    }

    @PostMapping("/users/add/{id}")
    public Map<String, Object> addUser(@PathVariable("id") Integer id, @RequestBody Map params) {
        Map<String, java.io.Serializable> datamap = new HashMap<>();
        String name=params.get("name").toString();
        Integer role =Integer.parseInt(params.get("role").toString());
        int rookie = usersMapper.addWorker(id, name, role);
        if (rookie == 1) {
            datamap.put("id", id);
            datamap.put("name", name);
            datamap.put("role", role);
        }
        Map<String, Object> data = new HashMap<>();
        data.put("data", datamap);
        data.put("status", 201);
        return data;
    }

    @GetMapping("/users/{name}")
    public Map<String, Object> getUsersByname(@PathVariable("name") String name) {
        Map<String, java.io.Serializable> datamap = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        List<Workers> getall = usersMapper.getWorkerByname(name + "%");
        datamap.put("total", getall.size());
        ArrayList<Map<String, java.io.Serializable>> list = new ArrayList<>();
        for (int i = 0; i <= getall.size() - 1; i++) {
            Map<String, java.io.Serializable> listmap = new HashMap<>();
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
        datamap.put("users", list);
        data.put("data", datamap);
        data.put("status", 200);
        return data;
    }

    @DeleteMapping("/users/del/{id}")
    public Map<String, Object> DeleteById(@PathVariable("id") Integer id) {

        Integer delete = usersMapper.deleteWorkerById(id);
        Map<String, Object> data = new HashMap<>();
        if (delete == 1) {
            data = this.getAllusers();
        }
        return data;
    }
    @GetMapping("/user/{id}")
    public Map<String, Object> getUserById(@PathVariable("id") Integer id){
        List<Workers> workers=usersMapper.getWorkerById(id);
        Map<String, java.io.Serializable> datamap= new HashMap<>();
        datamap.put("id",workers.get(0).id);
        datamap.put("name",workers.get(0).name);
        datamap.put("email",workers.get(0).email);
        datamap.put("mobile",workers.get(0).mobile);
        Map<String, Object> data=new HashMap<>();
        data.put("data",datamap);
        data.put("status",200);
        return data;
    }
    @GetMapping("/user/role/{id}")
    public Map<String, Object> getUserRoleById(@PathVariable("id") Integer id){
        List<Workers> workers=usersMapper.getWorkerById(id);
        Map<String, java.io.Serializable> datamap= new HashMap<>();
        datamap.put("id",workers.get(0).id);
        datamap.put("role_id",workers.get(0).role_id);
        datamap.put("role_name",workers.get(0).role_name);
        Map<String, Object> data= new HashMap<>();
        data.put("data",datamap);
        data.put("status",200);
        return data;
    }
    @GetMapping("/user/pic/{id}")
    public Map<String, Object> getUserPic(@PathVariable("id") Integer id){
        List<Workers> workers=usersMapper.getWorkerById(id);
        Map<String, java.io.Serializable> datamap=new HashMap<>();
        Map<String, String> picmap=new HashMap<>();
        ArrayList<Map<String, String>> piclist=new ArrayList<>();
        datamap.put("id",workers.get(0).id);
        datamap.put("name",workers.get(0).name);
        picmap.put("name",workers.get(0).picname);
        picmap.put("url",workers.get(0).url);
        piclist.add(0,picmap);
        datamap.put("fileList",piclist);
        Map<String, Object> data=new HashMap<>();
        data.put("data",datamap);
        data.put("status",200);
        return data;
    }
    @PutMapping("users/picture/{id}")
    public Map uploadUserPic(@PathVariable("id") Integer id, @RequestBody Map params){
        String url=params.get("url").toString();
        String picname=params.get("picname").toString();
        int update=usersMapper.updatePicById(url,picname,id);
        ArrayList list = new ArrayList();
        Map data=new HashMap();
        if(update==1) {
            Map datamap = new HashMap();
            datamap.put("url", url);
            datamap.put("name", picname);
            list.add(0, datamap);
            data.put("data",list);
            data.put("status",200);
        }
     return data;
    }
    @PutMapping("user/put/user/{id}")
    public Map<String, Object> updateById(@PathVariable("id") Integer id, @RequestBody Map<String,Object> parms){
       String email=parms.get("email").toString();
       String mobile=parms.get("mobile").toString();
        int update=usersMapper.updateWorkerById(email,mobile,id);
        Map<String, Object> data=new HashMap<String, Object>();
        if(update==1){
            data=this.getAllusers();
        }
        return  data;
    }
    @PutMapping("user/put/role/{id}")
    public Map<String, Object> updateRole(@PathVariable("id") Integer id, @RequestBody Map<String,Object> parms){
        Integer role_id= (Integer) parms.get("role_id");
        int update=usersMapper.updateWorkerRole(role_id,id);
        Map<String, Object> data=new HashMap<String, Object>();
        if(update==1){
            data=this.getAllusers();
        }
        return  data;
    }
    @PutMapping(value = "users/put/state/{id}")
    public Map<String, Object> updateState(@RequestBody Map<Object,Object> map){
        Integer id= (Integer) map.get("id");
        boolean state= (boolean) map.get("state");
        int status=0;
        if(!state){
               status=0;
        }else if(state){
            status=1;
        }
        int update=usersMapper.updateWorkerState(status,id);
        Map<String, Object> data=new HashMap<>();
        if(update==1){
            data=this.getAllusers();
        }
        return  data;
    }
    @GetMapping("/workinfo")
    public Map<String, Object> getWorkinfo(@RequestParam(value = "id") Integer id){
        Map<String, ArrayList<Map<String, java.io.Serializable>>> datamap = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        List<Workers> getall = usersMapper.getWorkers(id);
        ArrayList<Map<String, java.io.Serializable>> list = new ArrayList<>();
        for (int i = 0; i <= getall.size() - 1; i++) {
            Map<String, java.io.Serializable> listmap = new HashMap<>();
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
    @GetMapping("/workinfo/{id}")
    public Map<String, Object> showWorkinfo(@PathVariable(value = "id") Integer id){
        Map<String, Object> data = new HashMap<>();
        List<Workers> getall = usersMapper.getWorkers(id);
        Map<String, java.io.Serializable> listmap = new HashMap<>();
        listmap.put("id", getall.get(0).id);
        listmap.put("name", getall.get(0).name);
        listmap.put("email", getall.get(0).email);
        listmap.put("mobile", getall.get(0).mobile);
        listmap.put("role_name", getall.get(0).role_name);
        data.put("data", listmap);
        data.put("status", 200);
        return data;
    }

    @PutMapping("/workinfo/put/{id}")
    public Map<String, Object> updateInfoById(@PathVariable("id") Integer id, @RequestBody Map<String,Object> parms){
        String name=parms.get("name").toString();
        String email=parms.get("email").toString();
        String mobile=parms.get("mobile").toString();
        int update=usersMapper.updateWorkInfo(name,email,mobile,id);
        Map<String, Object> data= new HashMap<>();
        if(update==1){
            data=this.getAllusers();
        }
        return  data;
    }

    public Map<String, Object> getAllusers(){
        Map<String, java.io.Serializable> datamap = new HashMap<>();
        datamap.put("total", usersMapper.getTotal());
        Map<String, Object> data = new HashMap<>();
        List<Workers> getall = usersMapper.getallWorkers();
        ArrayList<Map<String, java.io.Serializable>> list = new ArrayList<>();
        for (int i = 0; i <= getall.size() - 1; i++) {
            Map<String, java.io.Serializable> listmap = new HashMap<>();
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



