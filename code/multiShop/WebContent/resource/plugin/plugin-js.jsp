<%@page contentType="text/html; charset=UTF-8"%>
<%
	pageContext.setAttribute("contextPath", request.getContextPath());
%>

<!-- jquery -->
<script type="text/javascript" src="${ contextPath }/resource/plugin/jquery/jquery-2.0.2.min.js"></script>
<script type="text/javascript" src="${ contextPath }/resource/plugin/jquery/jquery-ui-1.10.3.min.js"></script>
<script type="text/javascript" src="${ contextPath }/resource/plugin/jquery/jquery.fly.min.js"></script>

<!-- bootstrap -->
<script type="text/javascript" src="${ contextPath }/resource/plugin/bootstrap/js/bootstrap.min.js"></script>

<!-- My97DatePicker -->
<script type="text/javascript" src="${ contextPath }/resource/plugin/My97DatePicker/WdatePicker.js"></script>

<!-- angular -->
<%@include file="/resource/plugin/angular/angular-js.jsp" %>

<!-- select2 -->
<%@include file="/resource/plugin/angularui-select2/angularui-select2-js.jsp" %>

<!-- dropzone图片上传工具 -->
<%@include file="/resource/plugin/dropzone/dropzone-js.jsp" %>

<!-- daterangepicker日期选择器 -->
<%@include file="/resource/plugin/daterangepicker/daterangepicker-js.jsp" %>

<!-- smart-notification消息提醒框架 -->
<%@include file="/resource/plugin/smart-notification/smart-notification-js.jsp" %>

<!-- lodash -->
<%@include file="/resource/plugin/lodash/lodash-js.jsp" %>

<!-- slider轮播 -->
<%@include file="/resource/plugin/slider/slider-js.jsp" %>

<!-- kindeditor -->
<%@include file="/resource/plugin/kindeditor/kindeditor-js.jsp" %>


