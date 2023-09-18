package com.tute.batisdemo.controller;


import com.tute.batisdemo.Service.LoginService;
import com.tute.batisdemo.entity.Workers;
import com.tute.batisdemo.mapper.BatisMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ManagerController {
    @Autowired
    LoginService loginService;
    @PostMapping("/login/{id}&{pwd}")
    public  Map getManagerByName(@PathVariable("id") Integer id,@PathVariable("pwd") String password) {
        Map map=new HashMap();
       Workers admin=loginService.getall(id);
      map.put("data",admin);
       if(admin.password.equals(password)){
          map.put("status",200);
       }
       else {
           map.put("status",400);
       }
       return map;
    }
    @PostMapping("/register")
    public  Map createCount(@RequestBody Map params){
        Map data=new HashMap();
        Integer id= Integer.parseInt(params.get("id").toString());
        String password=params.get("password").toString();
        String email= params.get("email").toString();
        String mobile =params.get("mobile").toString();
        Workers lists=loginService.getall(id);
        if(lists==null){
            data.put("status",400);
            data.put("msg","无匹配的员工号");
        } else{
            loginService.CreateCount(id,password,email,mobile);
            data.put("status",200);
            data.put("msg","稍后将返回登录界面");
        }
        return data;
    }
   @GetMapping("checkid/{id}")
    public  Integer checkId(@PathVariable("id") Integer id){
       Integer data=0;
        Workers user=loginService.getall(id);
        if(user==null){
            data=400;
        }
        else if(user.password!=null){
            data=300;
        } else {
            data=200;
        }
        return data;
   }
    @GetMapping("/forget/{id}")
    public  Map searchCount(@PathVariable("id")Integer id){
        Map data=new HashMap();
        Workers lists=loginService.getall(id);
        if(lists==null){
            data.put("status",400);
            data.put("msg","无匹配的员工号");
        } else{
            data.put("status",200);
        }
        return data;
    }
    @PutMapping("/forget/put")
    public Map changePass(@RequestBody Map params){
        Map data =new HashMap();
        String password=params.get("password").toString();
        Integer id=Integer.parseInt(params.get("id").toString());
        loginService.updatePass(password,id);
        data.put("status",200);
        data.put("msg","修改成功，即将返回登录页面");
        return data;
    }
}
