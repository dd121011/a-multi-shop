package com.sky.business.shop;

import java.util.Calendar;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;

import com.sky.business.common.BaseAction;
import com.sky.business.common.vo.ServiceException;
import com.sky.business.shop.dao.ShopDao;
import com.sky.business.shop.entity.Shop;
import com.sky.business.shop.service.ShopService;
import com.sky.contants.CodeMescContants;
import com.sky.contants.EntityContants;
import com.sky.util.JsonUtil;

/**
 * 店铺action（访客访问）
 * @author Sky James
 *
 */
@InterceptorRefs({@InterceptorRef("visitorStack")})
public class ShopVisitAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Resource(name = "shopService")
	private ShopService shopService;
	
	@Resource(name = "shopDao")
	private ShopDao shopDao;
	
	private String shopId;
	
	
	/**
	 * 获取店铺数量
	 * @return
	 * @throws Exception
	 */
	public String count() throws Exception {
		try{
			Map<String,Object> condition = JsonUtil.getJsonToMap(conditionJson);
			Integer count = shopService.getCount(shopDao, Shop.class, condition);
			
			resultMap.put("count", count);
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, "200");
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, "成功获取店铺数量");
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, CodeMescContants.CodeContants.ERROR_COMMON);
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, CodeMescContants.MessageContants.ERROR_COMMON);
		}
		return RESULT_MAP;
	}
	
	/**
	 * 分页获取店铺列表
	 * @return
	 * @throws Exception
	 */
	public String paged() throws Exception {
		try{
			Map<String,Object> condition = JsonUtil.getJsonToMap(conditionJson);
			pager = shopService.pagedList(shopDao, Shop.class, condition);
			
			resultMap.put("pager", pager);
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, "200");
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, "成功获取店铺列表");
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, CodeMescContants.CodeContants.ERROR_COMMON);
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, CodeMescContants.MessageContants.ERROR_COMMON);
		}
		return RESULT_MAP;
	}
	
	
	/**
	 * 根据店铺ID获取店铺信息
	 * @return
	 * @throws Exception
	 */
	public String getShopById() throws Exception {
		try{
			Shop shop = shopService.findByID(Shop.class, shopId);
			
			if(shop.getOverTime()!=null && shop.getOverTime().before(Calendar.getInstance().getTime())) {
				throw new ServiceException(CodeMescContants.CodeContants.ERROR_COMMON, shop.getName() + "店铺已经到期");
			}
			
			resultMap.put("shop", shop);
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, "200");
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, "成功获取店铺信息");
		} catch (ServiceException se) {
			logger.error(ExceptionUtils.getStackTrace(se));
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, se.getErrorCode());
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, se.getErrorMsg());
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, CodeMescContants.CodeContants.ERROR_COMMON);
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, CodeMescContants.MessageContants.ERROR_COMMON);
		}
		return RESULT_MAP;
	}
	
	/**
	 * 增加店铺人气值
	 * @return
	 */
	public String addPopularity(){
		try{
			shopService.addPopularity(shopId);
			
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, "200");
		} catch (ServiceException e) {
			logger.error(e);
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, e.getErrorCode());
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, e.getErrorMsg());
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			resultMap.put(EntityContants.ResultMapContants.STATUS_CODE, CodeMescContants.CodeContants.ERROR_COMMON);
			resultMap.put(EntityContants.ResultMapContants.MESSAGE, CodeMescContants.MessageContants.ERROR_COMMON);
		}
		return RESULT_MAP;
	}
	
	//Getters and Setters
	@Override
	public Map<String, Object> getResultMap() {
		return resultMap;
	}
	
	@Override
	public String getConditionJson() {
		return conditionJson;
	}
	
	@Override
	public void setConditionJson(String conditionJson) {
		this.conditionJson = conditionJson;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

}
