package com.yi.jpshop.util;

import com.yi.jpshop.dao.JpDao;
import com.yi.jpshop.dao.impl.MenuDaoImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by moyi on 2017-03-17.
 */
public class JpDaoMapUtil {
    private static JpDao menuDao = new MenuDaoImpl();
    private static Map<String, JpDao> map = new HashMap<String, JpDao>();

    public static void init(){
        map.put("menu", menuDao);
    }


    public static JpDao getDao(String name){
        return map.get(name);
    }
}
