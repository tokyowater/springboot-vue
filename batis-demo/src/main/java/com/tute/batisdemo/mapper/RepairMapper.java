package com.tute.batisdemo.mapper;

import com.tute.batisdemo.entity.Cars;
import com.tute.batisdemo.entity.CheckCar;
import com.tute.batisdemo.entity.Repair;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface RepairMapper {
    @Select("select * from repairlist as repair join cars as car on repair.cid=car.car_id where car.car_id=#{car_id} and repair.list_state=0")
    public List<Cars> getListById(Integer car_id);
    @Update("update repairlist as re join cars as car on re.cid=car.car_id,workers as wk set re.wid=#{wid},re.list_state=1,car.repair_state=2,wk.status=2 where re.cid=#{cid} and wk.id=#{wid} and re.list_state=0")
    public int updateList(Integer wid,Integer cid);
    @Select("select * from repairlist as repair join cars as car on repair.cid=car.car_id join workers on repair.wid=workers.id where repair.wid=#{wid}")
    public List<Repair> getListByWId( Integer wid);
    @Select("select * from repairlist as repair join cars as car on repair.cid=car.car_id join workers on repair.wid=workers.id where repair.id=#{id}")
    public List<Repair> getRepairCar(Integer id);
    @Update("update repairlist as repair join cars as car on repair.cid=car.car_id join workers as wk on repair.wid=wk.id set repair.list_state=2,repair.finishtime=#{finishtime},repair.solve=#{solve},repair.price=#{price},car.fuel=#{fuel},car.repair_state=0,wk.status=0 where repair.id=#{id}")
    public void updateCars(String finishtime,String solve,Integer price,Integer fuel,Integer id);
    @Select("select * from repairlist as repair join cars as car on repair.cid=car.car_id join workers on repair.wid=workers.id where repair.list_state=0")
    public List<Repair> getallRepaired();
    @Select("select * from repairlist as repair join cars as car on repair.cid=car.car_id join workers on repair.wid=workers.id where repair.list_state=0 and repair.cid=#{cid}")
    public List<Repair> getRepairedById(Integer cid);
    @Select("select * from repairlist as repair join cars as car on repair.cid=car.car_id join workers on repair.wid=workers.id  limit #{num},#{size}")
    public List<Repair> getPageRepaired(Integer num,Integer size);
    @Select("SELECT count(*) as count FROM repairlist ")
    public  Integer getTotal();
    @Select("select * from repairlist as repair join cars as car on repair.cid=car.car_id join workers on repair.wid=workers.id where repair.wid=#{wid} limit #{num},#{size}")
    public List<Repair> getPagedListByWId(Integer wid,Integer num,Integer size);
    @Select("select * from checklist as checks join cars as car on checks.cid=car.car_id join workers on checks.wid=workers.id where checks.wid=#{wid} limit #{num},#{size}")
    public List<CheckCar> getPagedCheckListByWId(Integer wid, Integer num, Integer size);
    @Select("select * from checklist as checks join cars as car on checks.cid=car.car_id join workers on checks.wid=workers.id where checks.id=#{id}")
    public List<CheckCar> getCheckCar(Integer id);
    @Update("update checklist as checks join cars as car on checks.cid=car.car_id join workers as wk on checks.wid=wk.id set checks.state=2,checks.etime=#{etime},checks.solve=#{solve},checks.price=#{price},car.fuel=#{fuel},car.repair_state=0,wk.status=0 where checks.id=#{id}")
    public void updateCheckCars(String etime,String solve,Integer price,Integer fuel,Integer id);
    @Select("SELECT count(*) from checklist as checks join cars as car on checks.cid=car.car_id join workers on checks.wid=workers.id where checks.wid=#{wid}")
    public  Integer  getCheckListTotal(Integer wid);
    @Select("SELECT count(*) from repairlist as repair join cars as car on repair.cid=car.car_id join workers on repair.wid=workers.id where repair.wid=#{wid}")
    public  Integer  getListTotal(Integer wid);
}
