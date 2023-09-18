package com.tute.batisdemo;
import com.sun.org.apache.bcel.internal.generic.ANEWARRAY;
import com.tute.batisdemo.entity.Cars;
import com.tute.batisdemo.entity.Menu;
import com.tute.batisdemo.entity.Workers;
import com.tute.batisdemo.mapper.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.function.Predicate;

@SpringBootTest
class BatisDemoApplicationTests {
    @Test
    void contextLoads() {
    }
    @Autowired
   private MenuMapper menuMapper;
    @Test
    void getlist() {
       List<Menu> free=menuMapper.getallMenu();
        System.out.println(free.get(28).menu_name);
        System.out.println(free.get(28).menu_id);
    }
}



