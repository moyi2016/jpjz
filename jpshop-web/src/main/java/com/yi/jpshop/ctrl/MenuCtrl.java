package com.yi.jpshop.ctrl;

import com.jfinal.core.Controller;
import com.yi.jpshop.dao.MenuDao;
import com.yi.jpshop.util.JpDaoMapUtil;

/**
 * Created by moyi on 2017-03-17.
 */
public class MenuCtrl extends Controller{

    private MenuDao menuDao = (MenuDao) JpDaoMapUtil.getDao("menu");

    /**
     * 获取菜单列表
     */
    public void list(){
        String code =  getPara("code", "");
        Integer level = getParaToInt("level", 1);
        renderJson(menuDao.getMenu(code, level));
    }
}
