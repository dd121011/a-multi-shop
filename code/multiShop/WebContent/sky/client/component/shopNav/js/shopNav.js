angular.module('shopNav',[])
.directive('shopNav',function(){
	return {
		restrict:'E',
		scope : {
			selectedNav : "=",
			typeList		: "=",
		},
		templateUrl : $contextPath +"/sky/client/component/shopNav/template/shopNav.html",
		link : function(scope,element,attrs){
			
		},
		controller : function($scope, $timeout, $filter, $document, clientIndexHttpService){
			//是否首页就为关于我们
			$scope.shopAbout = shopAbout;
			
			/**
			 * 鼠标移过分类对象
			 */
			$scope.mouseType = function(isOver){
				if(isOver){
					$(".sn-types").fadeIn();
				}else{
					$(".sn-types").fadeOut();
				}
			}
		}
	};
});