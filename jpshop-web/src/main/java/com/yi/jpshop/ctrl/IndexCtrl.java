package com.yi.jpshop.ctrl;

import com.jfinal.core.Controller;
import com.jfinal.kit.PropKit;
import com.yi.jpshop.dao.MenuDao;
import com.yi.jpshop.entity.Menu;
import com.yi.jpshop.util.JpDaoMapUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by moyi on 2017-03-17.
 */
public class IndexCtrl extends Controller {

    private MenuDao menuDao = (MenuDao) JpDaoMapUtil.getDao("menu");

    public void index(){
        List<Menu> menuList = menuDao.getMenu("", 1);
        setAttr("menuList", menuList);
        List<String> list = new ArrayList<String>();
        String url = PropKit.get("qiniu_url");
        list.add(url + "/summary-section03.jpg");
        list.add(url + "/summary-section03.jpg");
        list.add(url + "/summary-section03.jpg");
        setAttr("imgList", list);
        render("/index.html");
    }

    public void query(){
        String code = getPara("code","");
        setAttr("code", code);
        render("/page/goods.html");
    }

    public void register(){
        render("/page/reg.html");
    }

    public void login(){
        render("/page/login.html");
    }
}
