package com.yi.jpshop.ctrl;

import com.jfinal.core.Controller;

/**
 * Created by moyi on 2017-03-17.
 */
public class IndexCtrl extends Controller {

    public void index(){
        render("/index.html");
    }
}
