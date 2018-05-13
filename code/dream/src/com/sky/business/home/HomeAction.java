package com.sky.business.home;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Result;

import com.sky.business.common.BaseAction;
import com.sky.business.common.vo.LoginUser;
import com.sky.business.common.vo.Pager;
import com.sky.business.common.vo.ServiceException;
import com.sky.business.oplog.entity.Oplog;
import com.sky.business.oplog.service.OplogService;
import com.sky.business.system.service.UserService;
import com.sky.business.visitor.service.VisitorService;
import com.sky.contants.CodeMescContants;
import com.sky.contants.EntityContants;
import com.sky.util.IpProcessUtil;

/**
 * 系统后台主页
 * @author Sky James
 *
 */
@InterceptorRefs({@InterceptorRef("defaultStack")})
public class HomeAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Resource(name = "userService")
	private UserService userService;
	
	@Resource(name = "oplogService")
	private OplogService oplogService;
	
	@Resource(name = "visitorService")
	private VisitorService visitorService;
	
	private LoginUser loginUser;
	
	private Integer type;
	
	private String code;
	
	//action
	
	/**
	 * 后台登录界面
	 * @return
	 */
	@Action(value = "login-page",results = @Result(location = "/sky/server/business/login/login.jsp"))
	public String loginPage() {
		logger.info("进入后台系统登录页面");
		return SUCCESS;
	}
	
	/**
	 * 登录
	 * @return
	 */
	@Action(value = "home-login", 
			results = {
				@Result(type = "redirectAction", params = {
						"actionName", "home-index",
						"namespace", "/home"
				}),
				@Result(name="input",location="/sky/server/business/login/login.jsp")
			}
		)
	public String login() throws Exception {
		try {
			isNull(loginUser);
			loginUser.setUserIp(IpProcessUtil.getIpAddr(req));
			loginUser = userService.checkForLogin(loginUser);
			session.setAttribute("loginUser", loginUser);
			
			String opDetail = "IP地址:" + loginUser.getUserIp() + "。" + "用户" + loginUser.getUsername() + "（" + loginUser.getUserId() + "）登录后台系统";
			Oplog log = Oplog.newOpUserInstance(loginUser.getUserId(), EntityContants.OplogContants.actionMaps.get("home-login"), opDetail, loginUser.getUserIp());
			oplogService.save(log);
			logger.info("用户" + loginUser.getUsername() + "（" + loginUser.getUserId() + "）" + "登录后台系统");
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, e.getErrorCode());
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, e.getErrorMsg());
			return INPUT;
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, CodeMescContants.CodeContants.LOGIN_ERROR);
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, CodeMescContants.MessageContants.LOGIN_ERROR);
			return INPUT;
		}
		
		return SUCCESS;
	}
	
	/**
	 * 退出
	 * @return
	 * @throws Exception
	 */
	@Action(value = "home-logout",interceptorRefs = {@InterceptorRef("serverStack"),@InterceptorRef("baseStack")})
	public String logout() throws Exception {
		session.invalidate();
		logger.info("退出后台系统");
		return LOGIN;
	}
	
	/**
	 * 后台主页面
	 * @return
	 */
	@Action(value = "home-index", results = @Result(location = "/sky/server/business/core/index.jsp"), interceptorRefs = {@InterceptorRef("serverStack"),@InterceptorRef("baseStack")})
	public String index() {
		logger.info("进入后台系统主页面");
		return SUCCESS;
	}
	
	/**
	 * 前端主页面
	 * @return
	 */
	@Action(value = "client-index", results = @Result(location = "/sky/client/business/core/index.jsp"), interceptorRefs = {@InterceptorRef("clientStack"),@InterceptorRef("baseStack")})
	public String clientIndex() {
		logger.info("进入前端主页面");
		return SUCCESS;
	}
	
	/**
	 * 前端关于页面
	 * @return
	 */
	@Action(value = "client-about", results = @Result(location = "/sky/client/business/about/about.jsp", params = {"type","${type}","code","${code}"}), interceptorRefs = {@InterceptorRef("clientStack"),@InterceptorRef("baseStack")})
	public String clientAbout() {
		logger.info("进入前端关于页面");
		return SUCCESS;
	}
	
	/**
	 * 前端新闻页面
	 * @return
	 */
	@Action(value = "client-news", results = @Result(location = "/sky/client/business/news/news.jsp", params = {"type","${type}","code","${code}"}), interceptorRefs = {@InterceptorRef("clientStack"),@InterceptorRef("baseStack")})
	public String clientNews() {
		logger.info("进入前端新闻页面");
		return SUCCESS;
	}
	
	/**
	 * 前端产品页面
	 * @return
	 */
	@Action(value = "client-product", results = @Result(location = "/sky/client/business/product/product.jsp", params = {"type","${type}","code","${code}"}), interceptorRefs = {@InterceptorRef("clientStack"),@InterceptorRef("baseStack")})
	public String clientProduct() {
		logger.info("进入前端产品页面");
		return SUCCESS;
	}
	
	/**
	 * 前端联系页面
	 * @return
	 */
	@Action(value = "client-contact", results = @Result(location = "/sky/client/business/contact/contact.jsp", params = {"type","${type}","code","${code}"}), interceptorRefs = {@InterceptorRef("clientStack"),@InterceptorRef("baseStack")})
	public String clientContact() {
		logger.info("进入前端联系页面");
		return SUCCESS;
	}
	

	//Getters and Setters
	
	public LoginUser getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(LoginUser loginUser) {
		this.loginUser = loginUser;
	}
	
	@Override
	public Map<String, Object> getResultMap() {
		return resultMap;
	}
	
	@Override
	public Pager getPager() {
		return pager;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}

