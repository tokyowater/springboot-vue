package com.tute.batisdemo.Service;

import com.tute.batisdemo.entity.Workers;
import com.tute.batisdemo.mapper.BatisMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements LoginImp {
    @Autowired
    private BatisMapper batisMapper;

    @Override
    public Workers getall(Integer id) {
        return batisMapper.getWorkersById(id);
    }

    @Override
    public void updatePass(String password, Integer id) {
        batisMapper.UpdatePass(password,id);
    }

    @Override
    public void CreateCount(Integer id, String password, String mobile, String email) {
        batisMapper.CreateCount(id,password,mobile,email);
    }
}
