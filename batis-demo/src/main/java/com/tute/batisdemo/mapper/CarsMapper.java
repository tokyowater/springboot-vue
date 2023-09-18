package com.tute.batisdemo.mapper;

import com.tute.batisdemo.entity.Cars;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CarsMapper {
    @Select("SELECT count(*) as count FROM cars where car_id !=0")
    public  Integer getTotal();
    @Insert("insert into cars values(#{car_id},#{car_name},0,0,100)")
    public int addCar(Integer car_id,String car_name);
    @Select("SELECT * FROM cars where car_name like #{car_name}")
    public  List<Cars> getCarByname(String car_name);
    @Delete("delete from cars where car_id=#{car_id}")
    public int deleteCarById(Integer car_id);
    @Select("select * from cars where car_id=#{car_id}")
    public List<Cars> getCarById(Integer car_id);
    @Update("update cars set car_name=#{car_name} where car_id=#{car_id} ")
    public  int updateCarById(String car_name,Integer car_id);
    @Update("update cars set repair_state=3 where car_id=#{car_id} ")
    public  void updatecheck(Integer car_id);
    @Insert("insert into checklist(cid,wid,stime,state) values(#{cid},#{wid},#{stime},1)")
    public  int createCheck(Integer cid,Integer wid,String stime);
    @Select("SELECT * FROM cars ")
    public List<Cars> getallCars();
    @Select("SELECT * FROM cars  where cars.car_id !=0 limit #{num},#{size} ")
    public List<Cars> getPageCars(Integer num,Integer size);
    @Update("update cars set fuel=#{fuel} where car_id=#{car_id} ")
    public  int updateFuelById(Integer fuel,Integer car_id);
}
