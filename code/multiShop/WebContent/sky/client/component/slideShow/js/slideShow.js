angular.module('slideShow',[])
.directive('slideShow',function(){
	return {
		restrict:'E',
		scope : {
			tableName		: '@',
			typeList			: '=',
			slideList		: '=',
			slideHrefList	: '=',
			indexAns			: '=',
		},
		templateUrl : $contextPath +"/sky/client/component/slideShow/template/slideShow.html",
		link : function(scope,element,attrs){
			scope.twoTypeList = [];
		},
		controller : function($scope, $timeout, $filter, $document){
			/**
			 * 初始化轮播图片
			 */
			$scope.initSlider = function(){
				var bannerSlider = new Slider($('#banner_tabs'), {
					time: 5000,
					delay: 400,
					event: 'hover',
					auto: true,
					mode: 'fade',
					controller: $('#bannerCtrl'),
					activeControllerCls: 'active'
				});
				$('#banner_tabs .flex-prev').click(function() {
					bannerSlider.prev();
				});
				$('#banner_tabs .flex-next').click(function() {
					bannerSlider.next();
				});
			};
			
			/**
			 * 跳转链接
			 */
			$scope.toUrl = function(url){
				window.location.href = url;
			};
			
			/**
			 * 跳到搜索页面
			 */
			$scope.toSearchPage = function(selectedType){
				if($scope.tableName && $scope.tableName=="tb_shop"){
					window.location.href = $contextPath + "/home/shop-search?type=" + selectedType.id;
				}
			};
			
			/**
			 * 初始化函数
			 */
			$scope.initFunc = function(){
				//初始化轮播图片
				$timeout($scope.initSlider, 1000);
			};
			$document.ready($scope.initFunc);
		}
	};
});