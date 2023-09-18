package com.tute.batisdemo.mapper;

import com.tute.batisdemo.entity.Schedual;
import com.tute.batisdemo.entity.Workers;
import org.apache.ibatis.annotations.*;
import org.omg.CORBA.INTERNAL;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface ScheMapper {

    @Select("SELECT * FROM schedual as sc LEFT JOIN workers  ON workers.id = sc.wid join cars on sc.cid=cars.car_id")
    public List<Schedual> getallSc();
    @Select("SELECT count(*) as count FROM schedual")
    public Integer getTotal();
    @Select("select * from  workers where workers.role_id=111")
    public  List<Workers> getavalibleWorkers();
    @Insert("insert into schedual(wid,cid,time,location,sc_status) values(#{wid},0,#{time},#{location},0)")
    public int addScheduals(Integer wid,String time,String location);
    @Select("SELECT * FROM schedual as sc LEFT JOIN workers  ON workers.id = sc.wid join cars on sc.cid=cars.car_id where sc.id=#{id}" )
    public  List<Schedual> getOrderById(Integer id);
    @Update("update schedual as sc LEFT JOIN workers  ON workers.id = sc.wid join cars on sc.cid=cars.car_id set sc.time=#{time},sc.location=#{location}, sc.wid=#{wid} where sc.id=#{id} ")
    public  int updateOrderById(String time,String location,Integer wid,Integer id);
    @Delete("delete from schedual where id=#{id}")
    public int deleteOrderById(Integer id);
    @Select("SELECT * FROM schedual as sc LEFT JOIN workers  ON workers.id = sc.wid join cars on sc.cid=cars.car_id where sc.wid=#{wid}" )
    public  List<Schedual> getOrderByWid(Integer id);
    @Insert("insert into result(lid,rstate) values(#{lid},2)")
    public Integer text(Integer lid);
    @Update("update schedual as sc join workers as wk on sc.wid=wk.id,cars as car set sc.cid=#{cid},car.exist_state=1,sc.sc_status=2,wk.status=2 where sc.id=#{id} and car.car_id=#{cid}")
   public Integer updateById(Integer cid, Integer id);
    @Select("SELECT * FROM schedual as sc LEFT JOIN workers  ON workers.id = sc.wid join cars on sc.cid=cars.car_id limit #{num},#{size}")
    public List<Schedual> getPageSc(Integer num,Integer size);
    @Select("SELECT * FROM schedual as sc LEFT JOIN workers  ON workers.id = sc.wid join cars on sc.cid=cars.car_id where sc.wid=#{wid} limit #{num},#{size}" )
    public  List<Schedual> getPagedOrderByWid(Integer wid, Integer num,Integer size);
    @Select("SELECT count(*) as count from schedual as sc LEFT JOIN workers  ON workers.id = sc.wid join cars on sc.cid=cars.car_id where sc.wid=#{wid}")
    public  Integer getWidTotal(Integer wid);
    @Select("select * from schedual as sc LEFT JOIN workers  ON workers.id = sc.wid join cars on sc.cid=cars.car_id where sc.time like #{time}")
    public List<Schedual> getscBytime(String time);
    @Select("SELECT count(*) as count from schedual as sc LEFT JOIN workers  ON workers.id = sc.wid join cars on sc.cid=cars.car_id where sc.time like #{time}")
    public  Integer getDateTotal(String time);
    @Select("select * from schedual as sc LEFT JOIN workers  ON workers.id = sc.wid join cars on sc.cid=cars.car_id where sc.time like #{time} and sc.wid=#{wid}")
    public List<Schedual> getPlanBytime(String time,Integer wid);
    @Select("SELECT count(*) as count from schedual as sc LEFT JOIN workers  ON workers.id = sc.wid join cars on sc.cid=cars.car_id where sc.time like #{time} and sc.wid=#{wid}")
    public  Integer getPlanTotal(String time,Integer wid);
}
