package com.jfinalD.framework.config;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.wall.WallFilter;
import com.jfinal.config.Constants;
import com.jfinal.config.*;
import com.jfinal.ext.handler.UrlSkipHandler;
import com.jfinal.ext.interceptor.SessionInViewInterceptor;
import com.jfinal.ext.plugin.route.AutoBindRoutes;
import com.jfinal.ext.plugin.shiro.ShiroInterceptor;
import com.jfinal.ext.plugin.shiro.ShiroPlugin;
import com.jfinal.json.FastJsonFactory;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.druid.DruidStatViewHandler;
import com.jfinal.plugin.druid.IDruidStatViewAuth;
import com.jfinal.plugin.ehcache.EhCachePlugin;
import com.jfinal.plugin.redis.RedisPlugin;
import com.jfinal.template.Engine;
import com.jfinal.weixin.sdk.api.ApiConfigKit;
import com.jfinal.weixin.sdk.cache.RedisAccessTokenCache;
import com.jfinalD.application.system.model._MappingKit;
import com.jfinalD.framework.handler.SessionIdHandler;
import com.jfinalD.framework.handler.XssHandler;
import com.jfinalD.framework.interceptor.ExceptionIntoLogInterceptor;
import com.jfinalD.framework.interceptor.GlobalInterceptor;
import com.jfinalD.framework.log.LogBackLogFactory;
import com.jfinalD.framework.tio.HelloServerStarter;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.beetl.core.GroupTemplate;
import org.beetl.ext.jfinal3.JFinal3BeetlRenderFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Create by tanliansheng on 2015年10月29日
 */
public class MyConfig extends JFinalConfig {

    /**
     * 供Shiro插件使用。
     */
    private Routes routes;

    private GroupTemplate gt;

    private WallFilter wallFilter;

    @Override
    public void configConstant(Constants me) {
        PropKit.use("config/config.properties");

        me.setDevMode(PropKit.getBoolean("devMode", true));
        me.setUrlParaSeparator("-");//设置参数分隔符

//		me.setBaseViewPath("/view"); //  已经在beetl的配置文件里定义好位置了
        JFinal3BeetlRenderFactory rf = new JFinal3BeetlRenderFactory();
//        rf.config();
        rf.config("config/beetl.properties");
        me.setRenderFactory(rf);

        gt = rf.groupTemplate;
        //根据gt可以添加扩展函数，格式化函数，共享变量等，
        Map<String, Object> shared = new HashMap<>();
        shared.put("name", "beetl");
        gt.setSharedVars(shared);

        // test
//		Template t = gt.getTemplate("/org/beetl/sample/s0208/t1.txt");
//		String str = t.render();
//		System.out.println(str);
//		t = gt.getTemplate("/org/beetl/sample/s0208/t2.txt");
//		str = t.render();
//		System.out.println(str);


        me.setError401View("/view/error/401.html");//没有身份验证时
        me.setError403View("/view/error/403.html");//没有权限时
        me.setError404View("/view/error/404.html");
        me.setError500View("/view/error/500.html");


        me.setViewExtension(".html");

        // for wx
        ApiConfigKit.setDevMode(me.getDevMode());
        //2.1新功能
        //me.setJsonFactory(new JacksonFactory());
        // 默认使用的jackson，下面示例是切换到fastJson
        me.setJsonFactory(new FastJsonFactory());

        me.setLogFactory(new LogBackLogFactory());

    }

    @Override
    public void configRoute(Routes me) {
        //给shiro用的
        this.routes = me;
//		me.setBaseViewPath("/view");

        me.add(new AutoBindRoutes());


        //me.add("/", IndexController.class, "/front");
        //me.add("/wx/msg", WeixinMsgController.class);
        //me.add("/wx/api", WeixinApiController.class, "/api");
        //me.add("/admin", IndexAdminController.class, "admin");
        //me.add("/admin/login", LoginAdminController.class, "front");
        //me.add("/admin/menu", MenuAdminController.class, "admin/menu");
        //me.add("/admin/role", RoleAdminController.class, "admin/role");
        //me.add("/admin/user", UserAdminController.class, "admin/user");

    }

    @Override
    public void configEngine(Engine engine) {

    }

    public static DruidPlugin getDruidPlugin() {
        return new DruidPlugin(PropKit.get("jdbc.url"), PropKit.get("jdbc.username"), PropKit.get("jdbc.password"));
    }

    @Override
    public void configPlugin(Plugins me) {

        //这是默认的
//		DruidPlugin dpOne = new DruidPlugin(getProperty("jdbc.url"), getProperty("jdbc.username"), getProperty("jdbc.password"));
//		WallFilter wallOne = new WallFilter();
//		wallOne.setDbType("mysql");
//		dpOne.addFilter(wallOne);
//		dpOne.addFilter(new StatFilter());
//		me.add(dpOne);
//		ActiveRecordPlugin arpOne = new ActiveRecordPlugin(dpOne);
//		me.add(arpOne);
//		if (isDevMode())arpOne.setShowSql(true);
///////////////////////////
//		arpOne.addMapping("system_res", Res.class);
//		arpOne.addMapping("system_role", Role.class);
//		arpOne.addMapping("system_user", User.class);
///////////////////////////
//
//		DruidPlugin dpTwo = new DruidPlugin(getProperty("jdbc.url2"), getProperty("jdbc.username"), getProperty("jdbc.password"));
//		WallFilter wallTwo = new WallFilter();
//		wallTwo.setDbType("mysql");
//		dpTwo.addFilter(wallTwo);
//		dpTwo.addFilter(new StatFilter());
//		me.add(dpTwo);
//		ActiveRecordPlugin arpTwo = new ActiveRecordPlugin("main2",dpTwo);
//		me.add(arpTwo);
//		arpTwo.setTransactionLevel(2);
//		if (isDevMode())arpTwo.setShowSql(true);
/////////////////////////////
//		arpTwo.addMapping("test", Test.class);
//////////////////////////////

        // 多数据源
        DruidPlugin dp = getDruidPlugin();
        wallFilter = new WallFilter();
        wallFilter.setDbType("mysql");
        dp.addFilter(wallFilter);
        dp.addFilter(new StatFilter());

        me.add(dp);

//		/*
//		 * 默认的单@Before(Tx.class)只对主数据源的事务有效 如果希望这个db2也支持事务 需要使用@TxConfig("db2")指定配置 这两个一块用
//		 * or 使用Db.use(dsName).tx(...)-
//		 * */
//		DruidPlugin dp2 = new DruidPlugin(PropKit.get("jdbc.url2"), PropKit.get("jdbc.username"), PropKit.get("jdbc.password"));
//		WallFilter wall2 = new WallFilter();
//		wall2.setDbType("mysql");
//		dp2.addFilter(wall2);
//		dp2.addFilter(new StatFilter());
//		me.add(dp2);
//		AutoTableBindPlugin atbp2 = new AutoTableBindPlugin("db2",dp2);
//		if (isDevMode()) atbp2.setShowSql(true);
//		atbp2.autoScan(false);
//		me.add(atbp2);
//
        //加载Shiro插件
        me.add(new ShiroPlugin(this.routes));
        //加载Ecache插件
        me.add(new EhCachePlugin());
        //加载Redis插件
        RedisPlugin redisPlugin = new RedisPlugin("myRedis", "127.0.0.1", 6379, 0);
        // todo 需要自定义 序列化方式
//		redisPlugin.setSerializer(new FstSerializer());
        me.add(redisPlugin);
//		me.add(new RedisPlugin("myRedis","127.0.0.1", 6379,0,"notblank",0));

        // 配置ActiveRecord插件
        ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);
        // todo 注意Reids的实现是Jfianl自己撸的一套 保存时都转成字节了 导致用软件查看时部分文字看不清
        // todo 默认如果不指定缓存接口 是使用ehcache的
//		arp.setCache(new MyRedisCache());
        arp.setShowSql(true);
        ApiConfigKit.setAccessTokenCache(new RedisAccessTokenCache("wuyuwechat"));
        me.add(arp);

        // 所有配置在 MappingKit 中搞定
        _MappingKit.mapping(arp);

        me.add(new HelloServerStarter());

    }

    @Override
    public void configHandler(Handlers me) {

        //无cookie时,会在url上添加;sessionId 这里做下判断,去除
        me.add(new SessionIdHandler());

        me.add(new XssHandler("/admin")); // `/admin*`为排除的目录

        //访问路径是/druid/index.ftl
        DruidStatViewHandler dvh = new DruidStatViewHandler("/druid", new IDruidStatViewAuth() {

            @Override
            public boolean isPermitted(HttpServletRequest request) {//获得查看权限
                Subject subject = SecurityUtils.getSubject();
                if (subject.isPermitted("druid:*")) {
                    return true;
                }
                //if(subject.isAuthenticated()){
                // return true;
                //}
                return false;
            }
        });
        me.add(dvh);

        me.add(new UrlSkipHandler("/assets", false));
    }

    /*
     * 只有全局的拦截器在这里配置
     */
    @Override
    public void configInterceptor(Interceptors me) {
        //添加shiro的全局变量
        me.addGlobalActionInterceptor(new ShiroInterceptor());

        me.addGlobalActionInterceptor(new GlobalInterceptor());

        me.addGlobalActionInterceptor(new ExceptionIntoLogInterceptor());

        me.addGlobalActionInterceptor(new SessionInViewInterceptor());//解决session在freemarker中不能取得的问题 获取方法：${session["manager"].username}
    }

    @Override
    public void afterJFinalStart() {
        //FreeMarkerRender.getConfiguration().setSharedVariable("shiro", new ShiroTags());
//		FreeMarkerRender.getConfiguration().setSharedVariable("myKit", new ShiroTags());
//		super.afterJFinalStart();
        // 让 druid 允许在 sql 中使用 union
        // https://github.com/alibaba/druid/wiki/%E9%85%8D%E7%BD%AE-wallfilter
        wallFilter.getConfig().setSelectUnionCheck(false);
    }

    @Override
    public void beforeJFinalStop() {
        super.beforeJFinalStop();
    }

    //判断是哪种环境
    private boolean isDevMode() {
        String osName = System.getProperty("os.name");
        return osName.contains("Windows");
    }

    /**
     * 如果生产环境配置文件存在，则优先加载该配置，否则加载开发环境配置文件
     *
     * @param pro 生产环境配置文件
     * @param dev 开发环境配置文件
     */
    private void loadProp(String pro, String dev) {
        try {
            PropKit.use(pro);
        } catch (Exception e) {
            PropKit.use(dev);
        }
    }

}
