package com.yi.jpshop.config;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.wall.WallFilter;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.activerecord.tx.TxByMethodRegex;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.druid.DruidStatViewHandler;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;
import com.yi.jpshop.ctrl.IndexCtrl;
import com.yi.jpshop.ctrl.MenuCtrl;
import com.yi.jpshop.entity._MappingKit;
import com.yi.jpshop.util.JpDaoMapUtil;

/**
 * Created by moyi on 2017-03-17.
 */
public class JpshopConfig extends JFinalConfig {
    public void configConstant(Constants constants) {
        PropKit.use("config.properties");
        constants.setEncoding("UTF-8");
        constants.setDevMode(PropKit.getBoolean("devMode", false));
        constants.setViewType(ViewType.FREE_MARKER);
        constants.setError404View("/404.html");
    }

    public void configRoute(Routes routes) {
        routes.add("/", IndexCtrl.class);
        routes.add("/menu", MenuCtrl.class);
    }

    public void configEngine(Engine engine) {

    }

    public void configPlugin(Plugins plugins) {
        DruidPlugin druidPlugin = createDruidPlugin();

        // StatFilter提供JDBC层的统计信息
        druidPlugin.addFilter(new StatFilter());
        // WallFilter的功能是防御SQL注入攻击
        WallFilter wallDefault = new WallFilter();
        wallDefault.setDbType("mysql");
        druidPlugin.addFilter(wallDefault);
        druidPlugin.setInitialSize(PropKit.getInt("db.default.poolInitialSize"));
        druidPlugin.setMaxPoolPreparedStatementPerConnectionSize(PropKit.getInt("db.default.poolMaxSize"));
        druidPlugin.setTimeBetweenConnectErrorMillis(PropKit.getInt("db.default.connectionTimeoutMillis"));
        plugins.add(druidPlugin);

        ActiveRecordPlugin activeRecordPlugin = new ActiveRecordPlugin(druidPlugin);
        activeRecordPlugin.setShowSql(true);
        activeRecordPlugin.setDialect(new MysqlDialect());
        _MappingKit.mapping(activeRecordPlugin);
        plugins.add(activeRecordPlugin);
    }

    public void configInterceptor(Interceptors interceptors) {
        interceptors.add(new TxByMethodRegex(".*add.*|.*update.*|.*delete.*"));
    }

    public void configHandler(Handlers handlers) {
        DruidStatViewHandler dvh = new DruidStatViewHandler("/druid");
        handlers.add(dvh);
    }

    @Override
    public void afterJFinalStart() {
        JpDaoMapUtil.init();
        super.afterJFinalStart();
    }

    private static DruidPlugin createDruidPlugin(){
        return new DruidPlugin(
                PropKit.get("c3p0.url"),PropKit.get("c3p0.user"),
                PropKit.get("c3p0.password"),PropKit.get("c3p0.driverClass"));
    }

    public static void main(String[] args) {
        JFinal.start("jpshop-web/src/main/webapp", 9110, "/");//启动配置项
    }
}
