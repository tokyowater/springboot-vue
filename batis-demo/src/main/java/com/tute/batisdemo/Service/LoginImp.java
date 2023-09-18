package com.tute.batisdemo.Service;

import com.tute.batisdemo.entity.Workers;

public interface LoginImp {
   public Workers getall(Integer id);
    public void updatePass(String password,Integer id);
    public void CreateCount(Integer id,String password,String mobile,String email);
}
