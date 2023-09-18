package com.tute.batisdemo.mapper;

import com.tute.batisdemo.entity.Free;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface FreeMapper {
    @Insert("insert into free(wid,type,reason,status,stime,etime) values(#{wid},#{type},#{reason},0,#{stime},#{etime})")
    public int createFree(Integer wid,String type,String reason,String stime,String etime);
    @Select("select * from free join workers as wk on free.wid=wk.id where free.wid=#{wid}")
    public List<Free> getListById(Integer wid);
    @Select("select * from free join workers as wk on free.wid=wk.id ")
    public List<Free> getList();
    @Update("update free set status=2 where id=#{id}")
    public int passfree(Integer id);
    @Update("update free set status=1 where id=#{id}")
    public int rejectfree(Integer id);
}
