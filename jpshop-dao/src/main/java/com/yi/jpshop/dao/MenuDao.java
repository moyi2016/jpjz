package com.yi.jpshop.dao;

import com.yi.jpshop.entity.Menu;

import java.util.List;

/**
 * Created by moyi on 2017-03-17.
 */
public interface MenuDao extends JpDao {
    /**
     * 获取菜单列表
     * @param code 菜单编码
     * @return 菜单列表
     */
    List<Menu> getMenu(String code, Integer level);
}
