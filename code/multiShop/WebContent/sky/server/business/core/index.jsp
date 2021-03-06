<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    
    <!-- 后台基本css -->
	<%@ include file="/sky/server/common/server.inc-css.jsp"%>
	<!-- smartadmin css -->
	<link rel="stylesheet" href="${contextPath }/sky/server/business/core/css/smart-admin.css" />
    <!-- 导入相应的css -->
	<link rel="stylesheet" href="${contextPath }/sky/server/business/core/css/index.css" />
	
	<!-- 导入系统图标 -->
    <link rel="icon" href="${systemIcon }" type="image/x-icon">
    
    <title>${systemName }管理系统</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
    
    <!-- 百度地图js -->
  	<script type="text/javascript" src="http://api.map.baidu.com/api?v=3.0&ak=7O3offVwYRhf9ll0xafEnBQLG06YGBCe"></script>
    
  </head>
  
  <body class="server-index" data-ng-app="serverIndexApp" data-ng-controller="serverIndexCtrl">
  	<!-- HEADER -->
	<header id="header">
		<div id="logo-group">
			<!-- PLACE YOUR LOGO HERE -->
			<span id="logo"> 
				<img src="${systemLogo }" alt=""> 
				${systemName }管理系统
			</span>
			<!-- END LOGO PLACEHOLDER -->
		</div>

		<!-- pulled right: nav area -->
		<div class="pull-right">

			<!-- collapse menu button -->
			<div id="hide-menu" class="btn-header pull-right">
				<span> <a href="javascript:void(0);" title="隐藏左导航"><i class="fa fa-reorder"></i></a> </span>
			</div>
			<!-- end collapse menu -->

			<!-- logout button -->
			<div id="logout" class="btn-header transparent pull-right">
				<span> <a href="${contextPath}/home/server-logout" title="退出" data-logout-msg="您确定要退出系统吗？"><i class="fa fa-sign-out"></i></a> </span>
			</div>
			<!-- end logout button -->

			<!-- fullscreen button -->
			<div id="fullscreen" class="btn-header transparent pull-right">
				<span> <a href="javascript:void(0);" onclick="launchFullscreen(document.documentElement);" title="满屏"><i class="fa fa-arrows-alt"></i></a> </span>
			</div>
			<!-- end fullscreen button -->

		</div>
		<!-- end pulled right: nav area -->

	</header>
	<!-- END HEADER -->
	
	
	<!-- Left panel : Navigation area -->
	<!-- Note: This width of the aside area can be adjusted through LESS variables -->
	<aside id="left-panel">

		<!-- User info -->
		<div class="login-info">
			<span>
				<a href="javascript:void(0);" data-ng-click="showPersonPanel()">
					<img src="${contextPath }/sky/server/business/core/img/user.jpeg" alt="me" /> 
					<span>${loginUser.username }</span>
				</a> 
			</span>
		</div>
		<!-- end user info -->

		<nav>
			<ul>
				<li>
					<a href="#/majorIndex" title="主页"><i class="fa fa-lg fa-fw fa-home"></i> <span class="menu-item-parent">主页</span></a>
				</li>
				<li data-ng-if="currentUser.allRights.indexOf('system_manage')>-1 || currentUser.allRights.indexOf('forum_manage')>-1">
					<a href="#"><i class="fa fa-lg fa-fw fa-cogs"></i> <span class="menu-item-parent">系统管理</span></a>
					<ul>
						<li data-ng-if="currentUser.allRights.indexOf('system_manage')>-1">
							<a href="#/system">系统信息</a>
						</li>
						<li data-ng-if="currentUser.allRights.indexOf('forum_manage')>-1">
							<a href="#">论坛信息</a>
						</li>
					</ul>
				</li>
				<li data-ng-if="currentUser.allRights.indexOf('announce_manage')>-1">
					<a href="#/announce" title="公告管理"><i class="fa fa-lg fa-fw fa-bullhorn"></i> <span class="menu-item-parent">公告管理</span></a>
				</li>
				<li data-ng-if="currentUser.allRights.indexOf('message_manage')>-1 || currentUser.allRights.indexOf('report_manage')>-1">
					<a href="#"><i class="fa fa-lg fa-fw fa-comments-o"><em class="my-hidden" data-ng-class="{'my-show':(messageCount+reportCount)}">{{messageCount+reportCount}}</em></i> <span class="menu-item-parent">消息管理</span></a>
					<ul>
						<li data-ng-if="currentUser.allRights.indexOf('message_manage')>-1">
							<a href="#/message">消息列表<span class="badge pull-right inbox-badge bg-color-brightRed my-hidden" data-ng-class="{'my-show':messageCount}">{{messageCount}}</span></a>
						</li>
						<li data-ng-if="currentUser.allRights.indexOf('report_manage')>-1">
							<a href="#/report">举报列表<span class="badge pull-right inbox-badge bg-color-brightRed my-hidden" data-ng-class="{'my-show':reportCount}">{{reportCount}}</span></a>
						</li>
					</ul>
				</li>
				<li data-ng-if="currentUser.allRights.indexOf('shop_manage')>-1">
					<a href="#"><i class="fa fa-lg fa-fw fa-cubes"><em class="my-hidden" data-ng-class="{'my-show':(shopCount + evaluateCount)}">{{shopCount + evaluateCount}}</em></i> <span class="menu-item-parent">店铺管理</span></a>
					<ul>
						<li data-ng-if="currentUser.allRights.indexOf('shop_basic')>-1">
							<a href="#/shopBasic">店铺信息</a>
						</li>
						<li data-ng-if="currentUser.allRights.indexOf('shop_list')>-1">
							<a href="#/shopList">店铺列表<span class="badge pull-right inbox-badge bg-color-brightRed my-hidden" data-ng-class="{'my-show':shopCount}">{{shopCount}}</span></a>
						</li>
						<li>
							<a href="#/evaluate">店铺评论<span class="badge pull-right inbox-badge bg-color-brightRed my-hidden" data-ng-class="{'my-show':evaluateCount}">{{evaluateCount}}</span></a>
						</li>
						<li data-ng-if="currentUser.allRights.indexOf('product_manage')>-1">
							<a href="#/product">商品列表</a>
						</li>
						<li data-ng-if="currentUser.allRights.indexOf('shop_type')>-1">
							<a href="#/shopType">店铺类型</a>
						</li>
						<li data-ng-if="currentUser.allRights.indexOf('product_type')>-1">
							<a href="#/productType">商品类型</a>
						</li>
					</ul>
				</li>
				<li data-ng-if="currentUser.allRights.indexOf('user_manage')>-1">
					<a href="#/user"><i class="fa fa-lg fa-fw fa-users"></i> <span class="menu-item-parent">用户管理</span></a>
				</li>
				<li data-ng-if="currentUser.allRights.indexOf('visitor_manage')>-1">
					<a href="#/visitor"><i class="fa fa-lg fa-fw fa-laptop"></i> <span class="menu-item-parent">访客管理</span></a>
				</li>
				<li data-ng-if="currentUser.allRights.indexOf('oplog_manage')>-1">
					<a href="#/oplog"><i class="fa fa-lg fa-fw fa-calendar"></i> <span class="menu-item-parent">日志管理</span></a>
				</li>
				<li data-ng-if="currentUser.allRights.indexOf('oplog_manage')>-1">
					<!--  <a href="#"><i class="fa fa-lg fa-fw fa-calendar"></i> <span class="menu-item-parent">论坛管理</span></a>-->
					<a href="#"><i class="fa fa-lg fa-fw fa-comments-o"><em class="my-hidden" data-ng-class="{'my-show':(messageCount+reportCount)}">{{messageCount+reportCount}}</em></i> <span class="menu-item-parent">论坛管理</span></a>
					<ul>
						<li data-ng-if="currentUser.allRights.indexOf('bbs_manage')>-1">
							<a href="#/bbssection">版块管理</a>
						</li>
						<li data-ng-if="currentUser.allRights.indexOf('bbs_manage')>-1">
							<a href="#/bbstopic">帖子管理</a>
						</li>
					</ul>
				</li>
			</ul>
		</nav>
		<span class="minifyme"> <i class="fa fa-arrow-circle-left hit"></i> </span>

	</aside>
	<!-- END NAVIGATION -->
	
	
	<!-- MAIN PANEL -->
	<div id="main" role="main">

		<!-- RIBBON -->
		<div id="ribbon">

			<span class="ribbon-button-alignment"> 
				<span id="refresh" class="btn btn-ribbon" 
					data-title="refresh"  rel="tooltip" data-placement="right" 
					data-original-title="<i class='text-warning fa fa-warning'></i> 注意，重置所有的页面缓存" data-html="true">
						<i class="fa fa-refresh"></i>
				</span> 
			</span>

			<!-- breadcrumb -->
			<ol class="breadcrumb">
				<!-- This is auto generated -->
			</ol>
			<!-- end breadcrumb -->

		</div>
		<!-- END RIBBON -->
		
		<!-- 路由模块 start -->
		<ng-view></ng-view> 
		<!-- 路由模块 end -->

	</div>
	<!-- END MAIN PANEL -->
	
	<person-panel></person-panel>
  	
  </body>
  
  <!-- 后台基本js -->
  <%@ include file="/sky/server/common/server.inc-js.jsp"%>
  
  <!-- smartadmin js -->
  <script type="text/javascript" src="${contextPath }/sky/server/business/core/js/smart-admin.js"></script>
  
  <!-- 导入相应的js -->
  <script type="text/javascript" src="${contextPath }/sky/server/business/core/js/server-index.controller.js"></script>
	
</html>
