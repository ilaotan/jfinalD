package com.jfinalD.web.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.log.Logger;
import com.jfinalD.web.common.Constants;
import com.jfinalD.web.core.shiro.CaptchaFormAuthenticationInterceptor;
import com.jfinalD.web.core.shiro.CaptchaRender;
import com.jfinalD.web.core.shiro.CaptchaUsernamePasswordToken;
import com.jfinalD.web.core.shiro.IncorrectCaptchaException;
import com.jfinalD.web.model.User;


public class LoginController extends Controller {
	
	private static final Logger LOG = Logger.getLogger(LoginController.class);
	private static final int DEFAULT_CAPTCHA_LEN = 4;
    private static final String LOGIN_URL = "/login";
	
    
	public void login(){
//		this.createToken("loginToken");
        this.render("login.html");
	}
	
	public void img(){
		CaptchaRender img = new CaptchaRender(DEFAULT_CAPTCHA_LEN);
        this.setSessionAttr(CaptchaRender.DEFAULT_CAPTCHA_MD5_CODE_KEY, img.getMd5RandonCode());
	    render(img);
	}
	
	@Before(CaptchaFormAuthenticationInterceptor.class)
	public void doLogin(){
		try {
//			this.validateToken();
            CaptchaUsernamePasswordToken token = this.getAttr("shiroToken");
			
            Subject subject = SecurityUtils.getSubject();
            ThreadContext.bind(subject);
            //进行用用户名和密码验证
            subject.login(token);
            //获取当前用户，并将当前用户保存在Session中。
            User user = User.dao.findByUsername(token.getUsername());
            //this.setSessionAttr("user", user);
            //保存用户默认的皮肤
            if(user != null){
                this.setSessionAttr("skin", user.getStr("skin"));
            }else{
                this.setSessionAttr("skin", "flat");
            }
            //调转到主页面
            this.redirect("/admin");
        } catch (IncorrectCaptchaException e) {
        	LOG.error(e.getMessage());
        	setAttr(Constants.ERROR, "验证码错误");
            render("login.html");
        } catch (LockedAccountException e) {
        	LOG.error(e.getMessage());
        	setAttr(Constants.ERROR, "账号已被锁定");
            render("login.html");
        } catch (AuthenticationException e) {
        	LOG.error(e.getMessage());
        	setAttr(Constants.ERROR, "用户名或者密码错误");
            render("login.html");
        } catch (Exception e) {
            LOG.error(e.getMessage());
            setAttr(Constants.ERROR, "系统异常");
            render("login.html");
        }
		
	}
	
	public void test(){
		setAttr("val","这是来自/admin/test的值");
		render("index.html");
	}
}
