package com.yi.jpshop.dao.impl;

import com.yi.jpshop.dao.MenuDao;
import com.yi.jpshop.entity.Menu;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Created by moyi on 2017-03-17.
 */
public class MenuDaoImpl implements MenuDao{


    public List<Menu> getMenu(String code, Integer level) {
        String sql = "select * from menu ";
        if (StringUtils.isBlank(code)){
            sql += " where level = "+level;
        }else {
            sql += " where level = "+level+" and code like '"+code+"%'";
        }
        return Menu.dao.find(sql+" order by sort asc");
    }
}
