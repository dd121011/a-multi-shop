package com.sky.business.home.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.sky.business.common.vo.LoginUser;
import com.sky.business.system.entity.User;
import com.sky.business.system.service.UserService;

/**
 * 前台登录拦截器
 * 对访问前台的Action进行登录拦截，如果用户未登录则拦截跳转回登录页面
 * @author Sky James
 *
 */
@Component
public class ClientLoginInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 677089508318175138L;
	
	private static final Logger logger = Logger.getLogger(ClientLoginInterceptor.class);
	
	@Resource
	private UserService userService;
	
	//没有登陆
	private boolean noLogin = true;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		logger.info("前台登录拦截器拦截");
		String result = "json";
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		
		//判断用户是否登录
		LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
		if(loginUser == null) {
			return result;
		}
		
		//判断数据库中是否存在该用户，或者该用户的密码是否被修改了
		User user = userService.findByID(User.class, loginUser.getUserId());
		if(user == null || !user.getPasswd().equals(loginUser.getUserPwd())) {
			session.invalidate();
			return result;
		}
		
		result = invocation.invoke();
		
		return result;
	}

	public boolean isNoLogin() {
		return noLogin;
	}

	public void setNoLogin(boolean noLogin) {
		this.noLogin = noLogin;
	}

}
