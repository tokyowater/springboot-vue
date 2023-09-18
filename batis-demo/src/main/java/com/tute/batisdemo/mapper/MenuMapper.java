package com.tute.batisdemo.mapper;

import com.tute.batisdemo.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface MenuMapper {
  @Select("Select * from menu ")
    public List<Menu> getallMenu();
  @Select(" select * from menu_api as api left join menu on menu.menu_id=api.id where api.menu_cid=1")
    public  List<Menu> getfirst();
    @Select(" select * from menu_api as api left join menu on menu.menu_id=api.id where api.menu_cid=2")
    public  List<Menu> getsecond();
    @Select(" select * from menu_api as api left join menu on menu.menu_id=api.id where api.menu_cid=6")
  public List<Menu> getThird();
    @Select("select * from menu_api as api left join menu on menu.menu_id=api.id where api.menu_cid=10")
  public List<Menu> getFourth();
  @Select("select * from menu_api as api left join menu on menu.menu_id=api.id where api.menu_cid=21 limit 0,1")
  public List<Menu> getFree();
  @Select("select * from menu_api as api left join menu on menu.menu_id=api.id where api.menu_cid=23")
  public List<Menu> getWorkFree();
  @Select("select * from menu_api as api left join menu on menu.menu_id=api.id where api.menu_cid=26")
  public List<Menu> getEngFree();
    @Select("select * from menu_api as api left join menu on menu.menu_id=api.id where api.menu_cid=17")
    public List<Menu> getEnginfo();
  @Select("select * from menu_api as api left join menu on menu.menu_id=api.id where api.menu_cid=19")
  public List<Menu> getWorkinfo();
  @Select("select * from menu_api as api left join menu on menu.menu_id=api.id where api.menu_cid=30")
  public List<Menu> getCarsinfo();
  @Select("select * from menu_api as api left join menu on menu.menu_id=api.id where api.menu_cid=15")
  public List<Menu> getReport();
}
