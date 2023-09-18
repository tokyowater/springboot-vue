package com.tute.batisdemo.mapper;

import com.tute.batisdemo.entity.Workers;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UsersMapper {
    @Select("SELECT * FROM workers LEFT JOIN role  ON workers.role_id = role.role_id limit #{num},#{size}")
    public List<Workers> getall(Integer num,Integer size);
    @Select("SELECT count(*) as count FROM workers")
    public  Integer getTotal();
    @Insert("insert into workers(id,name,role_id,status) values(#{id},#{name},#{role_id},0)")
    public int addWorker(Integer id,String name,Integer role_id);
    @Select("SELECT * FROM workers LEFT JOIN role  ON workers.role_id = role.role_id where workers.name like #{name}")
      public  List<Workers> getWorkerByname(String name);
    @Delete("delete from workers where id=#{id}")
    public int deleteWorkerById(Integer id);
    @Select("select * from workers LEFT JOIN role  ON workers.role_id = role.role_id where workers.id =#{id}")
    public List<Workers> getWorkerById(Integer id);
    @Update("update workers LEFT JOIN role  ON workers.role_id = role.role_id  set workers.email=#{email},workers.mobile=#{mobile} where workers.id=#{id} ")
    public  int updateWorkerById(String email,String mobile,Integer id);
    @Update("update workers LEFT JOIN role  ON workers.role_id = role.role_id  set workers.url=#{url},workers.picname=#{picname} where workers.id=#{id} ")
    public  int updatePicById(String url,String picname,Integer id);
    @Select("SELECT * FROM workers LEFT JOIN role  ON workers.role_id = role.role_id")
    public List<Workers> getallWorkers();
    @Select("SELECT * FROM workers LEFT JOIN role  ON workers.role_id = role.role_id where workers.id=#{id}")
    public List<Workers> getEngWorkers(Integer id);
    @Update("update  workers LEFT JOIN role  ON workers.role_id = role.role_id set workers.name=#{name},workers.email=#{email},workers.mobile=#{mobile} where workers.id=#{id}")
    public  int updateEngInfo(String name,String email,String mobile,Integer id);
    @Select("SELECT * FROM workers LEFT JOIN role  ON workers.role_id = role.role_id where workers.id=#{id}")
    public List<Workers> getWorkers(Integer id);
    @Update("update  workers LEFT JOIN role  ON workers.role_id = role.role_id set workers.name=#{name},workers.email=#{email},workers.mobile=#{mobile} where workers.id=#{id}")
    public  int updateWorkInfo(String name,String email,String mobile,Integer id);
    @Update("update  workers LEFT JOIN role  ON workers.role_id = role.role_id set workers.role_id=#{role_id} where workers.id=#{id}")
    public  int updateWorkerRole(Integer role_id,Integer id);
    @Update("update workers LEFT JOIN role  ON workers.role_id = role.role_id set workers.status=#{status} where workers.id=#{id}")
    public  int updateWorkerState(Integer status,Integer id);
}
