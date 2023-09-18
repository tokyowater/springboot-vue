package com.tute.batisdemo.mapper;

import com.tute.batisdemo.entity.Location;
import com.tute.batisdemo.entity.Workers;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ReportMapper {
    @Select("select * from location ")
    public List<Location> getResources();
    @Select("select sum(nownum) as total from result as rs join schedual as sc on rs.lid=sc.id where location='矿坑1号' and rtime like '2020%'")
    public  Integer getPreset1();
    @Select("select sum(nownum) as total from result as rs join schedual as sc on rs.lid=sc.id where location='矿坑1号' and rtime like '2019%'")
    public  Integer getLast1();
    @Select("select sum(nownum) as total from result as rs join schedual as sc on rs.lid=sc.id where location='矿坑1号' and rtime like '2018%'")
    public  Integer getPreLast1();
    @Select("select sum(nownum) as total from result as rs join schedual as sc on rs.lid=sc.id where location='矿坑2号' and rtime like '2020%'")
    public  Integer getPreset2();
    @Select("select sum(nownum) as total from result as rs join schedual as sc on rs.lid=sc.id where location='矿坑2号' and rtime like '2019%'")
    public  Integer getLast2();
    @Select("select sum(nownum) as total from result as rs join schedual as sc on rs.lid=sc.id where location='矿坑2号' and rtime like '2018%'")
    public  Integer getPreLast2();
    @Select("select sum(nownum) as total from result as rs join schedual as sc on rs.lid=sc.id where location='矿坑3号' and rtime like '2020%'")
    public  Integer getPreset3();
    @Select("select sum(nownum) as total from result as rs join schedual as sc on rs.lid=sc.id where location='矿坑3号' and rtime like '2019%'")
    public  Integer getLast3();
    @Select("select sum(nownum) as total from result as rs join schedual as sc on rs.lid=sc.id where location='矿坑3号' and rtime like '2018%'")
    public  Integer getPreLast3();
    @Select("select sum(nownum) as total from result as rs join schedual as sc on rs.lid=sc.id where location='矿坑1号' and wid=#{wid}")
    public  Integer getFirstProfit(Integer wid);
    @Select("select sum(nownum) as total from result as rs join schedual as sc on rs.lid=sc.id where location='矿坑2号' and wid=#{wid}")
    public  Integer getSecnondProfit(Integer wid);
    @Select("select sum(nownum) as total from result as rs join schedual as sc on rs.lid=sc.id where location='矿坑3号' and wid=#{wid}")
    public  Integer getThirdProfit(Integer wid);
    @Select("select * from workers where role_id=111")
    public  List<Workers> getworkers();
}
