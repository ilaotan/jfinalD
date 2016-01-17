package com.jfinalD.application.system.controller;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.log.Log;
import com.jfinalD.application.system.Validator.LoginValidator;
import com.jfinalD.application.system.model.User;
import com.jfinalD.framework.config.Constants;
import com.jfinalD.framework.shiro.CaptchaFormAuthenticationInterceptor;
import com.jfinalD.framework.shiro.CaptchaRender;
import com.jfinalD.framework.shiro.CaptchaUsernamePasswordToken;
import com.jfinalD.framework.shiro.IncorrectCaptchaException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;

/** 
 * Create by tanliansheng on 2015年10月29日
 */
public class LoginAdminController extends Controller {

	static Log LOG = Log.getLog(LoginAdminController.class);
	private static final int DEFAULT_CAPTCHA_LEN = 4;
    
	public void index(){
		 Subject subject = SecurityUtils.getSubject();
		 if(subject.isAuthenticated()){
			 this.redirect("/admin");
		 }else{
			 this.render("login.ftl");
		 }
		
	}
	
	public void img(){
		CaptchaRender img = new CaptchaRender(DEFAULT_CAPTCHA_LEN);
        this.setSessionAttr(CaptchaRender.DEFAULT_CAPTCHA_MD5_CODE_KEY, img.getMd5RandonCode());
	    render(img);
	}
	
	@Before({LoginValidator.class,CaptchaFormAuthenticationInterceptor.class})
	public void doLogin(){
		try {
            CaptchaUsernamePasswordToken token = this.getAttr("shiroToken");
			
            //可以将值映射到model里
            User user = getModel(User.class);
            
            Subject subject = SecurityUtils.getSubject();
            ThreadContext.bind(subject);
            //进行用用户名和密码验证
            subject.login(token);
//            //获取当前用户，并将当前用户保存在Session中。
//            User user = User.dao.findByUsername(token.getUsername());
//            //this.setSessionAttr("user", user);
//            //保存用户默认的皮肤
//            if(user != null){
//                this.setSessionAttr("skin", user.getStr("skin"));
//            }else{
//                this.setSessionAttr("skin", "flat");
//            }
            //调转到主页面
            this.redirect("/admin");
        } catch (IncorrectCaptchaException e) {
        	LOG.error("验证码错误"+e.getMessage());
        	setAttr(Constants.ERROR, "验证码错误");
            render("login.ftl");
        } catch (LockedAccountException e) {
        	LOG.error("账号已被锁定"+e.getMessage());
        	setAttr(Constants.ERROR, "账号已被锁定");
            render("login.ftl");
        } catch (AuthenticationException e) {
        	LOG.error("用户名或者密码错误"+e.getMessage());
        	setAttr(Constants.ERROR, "用户名或者密码错误");
            render("login.ftl");
        } catch (Exception e) {
            LOG.error("系统异常"+e.getMessage());
            setAttr(Constants.ERROR, "系统异常");
            render("login.ftl");
        }
		
	}
	public void logout(){
		Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            subject.logout();
        }
	}
}
