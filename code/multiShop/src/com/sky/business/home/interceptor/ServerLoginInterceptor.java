package com.sky.business.home.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.sky.business.common.vo.LoginUser;
import com.sky.business.system.entity.User;
import com.sky.business.system.service.UserService;
import com.sky.contants.RightContants;
import com.sky.contants.ShopContants;

/**
 * 后台登录拦截器
 * 对访问后台的Action进行登录拦截，如果用户未登录则拦截跳转回登录页面
 * @author Sky James
 *
 */
@Component
public class ServerLoginInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 677089508318175138L;
	
	private static final Logger logger = Logger.getLogger(ServerLoginInterceptor.class);
	
	@Resource
	private UserService userService;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		logger.info("后台登录拦截器拦截");
		String result = "login";
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		
		//判断用户是否登录 或者 用户是否有登陆后台的权限 或者 用户的店铺是否已启用
		LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
		if(loginUser == null 
				|| StringUtils.isBlank(loginUser.getAllRights()) 
				|| loginUser.getAllRights().indexOf(RightContants.BACK_MANAGE)<0
				|| loginUser.getShopId()==null 
				|| (!ShopContants.SHOP_SYSTEM.equals(loginUser.getShopId()) && ShopContants.Status.USING!=loginUser.getShopStatus())) {
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

}
