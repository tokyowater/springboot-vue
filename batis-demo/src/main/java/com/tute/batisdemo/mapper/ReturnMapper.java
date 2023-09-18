package com.tute.batisdemo.mapper;

import com.tute.batisdemo.entity.Schedual;
import com.tute.batisdemo.entity.Wrecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface ReturnMapper {
    @Select("select * from schedual as sc LEFT JOIN workers  ON workers.id = sc.wid join cars on sc.cid=cars.car_id join result as rs on sc.id=rs.lid where sc.wid=#{wid}")
    public List<Schedual> getBooked(Integer wid);
    @Update("update result as rs join schedual as sc on rs.lid=sc.id join workers as wk on wk.id=sc.wid,cars set rs.rtime=#{rtime},rs.rstate=1,rs.nownum=#{resource},wk.status=0,sc.sc_status=1,cars.exist_state=0 where rs.lid=#{lid} and cars.car_id=#{car_id}")
    public  Integer updateResult(String rtime,Integer resource,Integer lid,Integer car_id);
    @Select("select sum(nownum) as totalnum from schedual as sc join result as rs on sc.id=rs.lid join location on sc.location=location.name where sc.location=#{name}")
    public  Integer allnum(String name);
    @Update("update location set resource=#{resource} where location.name=#{name}")
    public  Integer updateTotal(Integer resource,String name);
    @Update("update cars set repair_state=1 where cars.car_id=#{car_id}")
    public  void updateRepair(Integer car_id);
    @Insert("insert into repairlist(cid,detail,list_state) values(#{cid},#{detail},0)")
    public void insertRepair(Integer cid,String detail);
    @Select("select * from schedual as sc join result as rs on sc.id=rs.lid join cars as car on sc.cid=car.car_id join workers as wk on sc.wid=wk.id")
    public  List<Wrecord> getWorkedRecord();
    @Select("select * from schedual as sc join result as rs on sc.id=rs.lid join cars as car on sc.cid=car.car_id join workers as wk on sc.wid=wk.id limit #{num},#{size}")
    public  List<Wrecord> getPagedRecord(Integer num,Integer size);
    @Select("SELECT count(*) as count FROM schedual as sc join result as rs on sc.id=rs.lid join cars as car on sc.cid=car.car_id join workers as wk on sc.wid=wk.id")
    public Integer getTotal();
    @Select("SELECT * FROM schedual as sc join result as rs on sc.id=rs.lid join cars as car on sc.cid=car.car_id join workers as wk on sc.wid=wk.id where sc.wid=#{wid}" )
    public  List<Wrecord> getRecordByWid(Integer wid);
    @Select("SELECT * FROM schedual as sc join result as rs on sc.id=rs.lid join cars as car on sc.cid=car.car_id join workers as wk on sc.wid=wk.id where sc.time like #{time}" )
    public  List<Wrecord> getRecordByTime(String time);
    @Select("select count(*) from schedual as sc join result as rs on sc.id=rs.lid join cars as car on sc.cid=car.car_id join workers as wk on sc.wid=wk.id where sc.time like #{time}")
    public  Integer getRecordCount(String time);
    @Select("select * from schedual as sc LEFT JOIN workers  ON workers.id = sc.wid join cars on sc.cid=cars.car_id join result as rs on sc.id=rs.lid where sc.wid=#{wid}  limit #{num},#{size}")
    public List<Schedual> getPagedBooked(Integer wid,Integer num,Integer size);
    @Select("SELECT count(*) as count from schedual as sc LEFT JOIN workers  ON workers.id = sc.wid join cars on sc.cid=cars.car_id join result as rs on sc.id=rs.lid where sc.wid=#{wid}")
    public  Integer getPagedBookTotal(Integer wid);
    @Select("select * from schedual as sc LEFT JOIN workers  ON workers.id = sc.wid join cars on sc.cid=cars.car_id join result as rs on sc.id=rs.lid where sc.time like #{time} and sc.wid=#{wid}")
    public List<Schedual> getDateBooked(String time,Integer wid);
    @Select("SELECT count(*) as count from schedual as sc LEFT JOIN workers  ON workers.id = sc.wid join cars on sc.cid=cars.car_id join result as rs on sc.id=rs.lid where sc.time like #{time} and sc.wid=#{wid}")
    public  Integer getDateBookTotal(String time,Integer wid);
}
