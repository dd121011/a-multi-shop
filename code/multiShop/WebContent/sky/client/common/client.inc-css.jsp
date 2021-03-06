<%@page import="com.sky.contants.UserContants"%>
<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="com.sky.util.SysParameterUtil" %>
<%@page import="com.sky.contants.EntityContants" %>
<%@page import="com.sky.util.JsonUtil" %>
<%@page import="com.sky.contants.UserContants" %>
<%
	pageContext.setAttribute("contextPath", request.getContextPath());
	
	String systemName = SysParameterUtil.getStringValue("system_name", "");
	pageContext.setAttribute("systemName", systemName);
	
	String systemIcon = SysParameterUtil.getStringValue("system_icon", "");
	pageContext.setAttribute("systemIcon", StringUtils.isNotBlank(systemIcon)?systemIcon:(request.getContextPath() + "/sky/common/core/img/system_icon.ico"));
	
	String systemLogo = SysParameterUtil.getStringValue("system_logo", "");
	pageContext.setAttribute("systemLogo", StringUtils.isNotBlank(systemLogo)?systemLogo:(request.getContextPath() + "/sky/common/core/img/system_logo.png"));
	
	String systemPicture = SysParameterUtil.getStringValue("system_picture", "");
	pageContext.setAttribute("systemPicture", systemPicture);
	
	String systemPictureHref = SysParameterUtil.getStringValue("system_picture_href", "");
	pageContext.setAttribute("systemPictureHref", systemPictureHref);
	
	String companyName = SysParameterUtil.getStringValue("company_name", "");
	pageContext.setAttribute("companyName", companyName);
	
	String companyAddress = SysParameterUtil.getStringValue("company_address", "");
	pageContext.setAttribute("companyAddress", companyAddress);
	
	String companyRecord = SysParameterUtil.getStringValue("company_record", "");
	pageContext.setAttribute("companyRecord", companyRecord);
	
	String copyRight = SysParameterUtil.getStringValue("copy_right", "");
	pageContext.setAttribute("copyRight", copyRight);
	
	String telephone = SysParameterUtil.getStringValue("telephone", "");
	pageContext.setAttribute("telephone", telephone);
	
	String phone = SysParameterUtil.getStringValue("phone", "");
	pageContext.setAttribute("phone", phone);
	
	String email = SysParameterUtil.getStringValue("email", "");
	pageContext.setAttribute("email", email);
	
	String qq = SysParameterUtil.getStringValue("qq", "");
	pageContext.setAttribute("qq", qq);
	
	String wechatPic = SysParameterUtil.getStringValue("wechat_pic", "");
	pageContext.setAttribute("wechatPic", StringUtils.isNotBlank(wechatPic)?wechatPic:(request.getContextPath() + "/sky/client/common/img/wechat.png"));
	
	String serviceTime = SysParameterUtil.getStringValue("service_time", "");
	pageContext.setAttribute("serviceTime", serviceTime);
	
	//默认百度地图经纬度
	String defaultBmap = SysParameterUtil.getStringValue("default_bmap", "");
	pageContext.setAttribute("defaultBmap", StringUtils.isNotBlank(defaultBmap)?defaultBmap:"113.273154,23.146502");
	
	//用户的默认初始密码
	String defaultPasswd = UserContants.defaultPasswd;
	pageContext.setAttribute("defaultPasswd", defaultPasswd);
	
%>
<!-- 初始化全局参数 -->
<script type="text/javascript">
var $contextPath = '${ contextPath }';//上下文地址
var $currentUser = JSON.parse('${ JsonUtil.toJson(loginUser) }');//当前登录用户

var $systemName = '${ systemName }';//系统名称
var $systemLogo = '${ systemLogo }';//系统logo
var $systemPicture = '${ systemPicture }';//系统轮播图
var $systemPictureHref = '${ systemPictureHref }';//系统轮播图链接

var $companyName = '${ companyName }';//公司名称
var $companyAddress = '${ companyAddress }';//公司地址
var $companyRecord = '${ companyRecord }';//公司备案号
var $copyRight = '${ copyRight }';//版权有效期
var $telephone = '${ telephone }';//联系手机
var $phone = '${ phone }';//联系电话
var $email = '${ email }';//邮箱地址
var $qq = '${ qq }';//qq号码
var $wechatPic = '${ wechatPic }';//微信公众号二维码
var $serviceTime = '${ serviceTime }';//服务时间
var $defaultBmaps = '${ defaultBmap }'.split(",");//默认百度地图经纬度
var $defaultPasswd = '${ defaultPasswd }';//用户的默认初始密码
var $qqLoginBtn = $contextPath + "/sky/common/core/img/qq_login.png";//qq登录按钮图片url
var $currentUrl = window.location.href;//当前页面url

var $isWechat = navigator.userAgent.toLowerCase().indexOf('micromessenger')>-1;//是否微信浏览器

//是否需要页面跳转（是否微信浏览器）
if(!$currentUser && $isWechat){
	window.location.href = $contextPath + "/common/wechat!webLogin?reUrl=" + $currentUrl;
}
</script>

<!-- 导入外部插件 -->
<%@include file="/resource/plugin/plugin-css.jsp"%>

<!-- 导入公共的css -->
<link rel="stylesheet" href="${contextPath }/sky/common/core/css/common.css" />
<!-- 导入公共的组件css -->
<%@include file="/sky/common/component/component-css.jsp"%>

<!-- 导入公共client的css -->
<link rel="stylesheet" href="${contextPath }/sky/client/common/css/clientCommon.css" />
<!-- 导入client组件css -->
<%@include file="/sky/client/component/component-css.jsp"%>
