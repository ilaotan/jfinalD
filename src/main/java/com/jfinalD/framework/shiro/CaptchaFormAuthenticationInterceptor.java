package com.jfinalD.framework.shiro;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinalD.framework.config.Constants;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

public class CaptchaFormAuthenticationInterceptor extends FormAuthenticationFilter implements Interceptor {

    private String captchaParam = "captcha";

    public String getCaptchaParam() {
        return captchaParam;
    }

    protected String getCaptcha(ServletRequest request) {
        return WebUtils.getCleanParam(request, getCaptchaParam());
    }

    protected AuthenticationToken createToken(HttpServletRequest request) {
        setUsernameParam(Constants.USERNAME);
        setPasswordParam(Constants.PASSWORD);
        String username = getUsername(request);
        String password = getPassword(request);
        String captcha = getCaptcha(request);
        boolean rememberMe = isRememberMe(request);
        String host = getHost(request);
        return new CaptchaUsernamePasswordToken(username, password, rememberMe, host, captcha);
    }

    @Override
    public void intercept(Invocation ai) {
        HttpServletRequest request = ai.getController().getRequest();
        AuthenticationToken authenticationToken = createToken(request);
        request.setAttribute("shiroToken", authenticationToken);
        ai.invoke();
    }

}
