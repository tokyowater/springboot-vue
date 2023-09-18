package com.tute.batisdemo.mapper;


import com.tute.batisdemo.entity.Workers;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface BatisMapper {
    @Select("select * from workers where id=#{id}")
    public Workers getWorkersById(Integer id);
    @Update("update workers set mobile=#{mobile},email=#{email},password=#{password} where id=#{id}")
    public void CreateCount(Integer id,String password,String mobile,String email);
    @Update("update workres set password=#{password} where id=#{id}")
    public void UpdatePass(String password,Integer id);

}
