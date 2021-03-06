package com.sky.business.oplog.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.sky.business.common.dao.impl.BaseDaoImpl;
import com.sky.business.oplog.dao.OplogDao;
import com.sky.util.DateUtil;

/**
 * 操作日志Dao实现类
 * @author Sky James
 *
 */
@Service("oplogDao")
public class OplogDaoImpl extends BaseDaoImpl implements OplogDao {

	@Override
	public StringBuffer getPackageHql(StringBuffer hqlBuffer, List<Object> values, Map<String, Object> condition) {
		String sort = "opTime desc";
		
		//排序
		if(condition.containsKey("sort") && StringUtils.isNotBlank((String)condition.get("sort"))){
			sort = (String)condition.get("sort");
		}
		
		//关键字
		if(condition.containsKey("keywords") && StringUtils.isNotBlank((String)condition.get("keywords"))){
			String keywords = (String)condition.get("keywords");
			hqlBuffer.append(" and (opDetail like ?) ");
			values.add("%" + keywords + "%");
		}
		
		//日志类型
		if(condition.containsKey("opType") && StringUtils.isNotBlank((String)condition.get("opType"))){
			String opType = (String)condition.get("opType");
			hqlBuffer.append(" and opType = ? ");
			values.add(opType);
		}
		
		//操作时间
		if(condition.containsKey("opTimeA") && StringUtils.isNotBlank((String)condition.get("opTimeA"))
				&& condition.containsKey("opTimeZ") && StringUtils.isNotBlank((String)condition.get("opTimeZ"))){
			Date opTimeA = DateUtil.convertStr2Date((String)condition.get("opTimeA"));
			Date opTimeZ = DateUtil.convertStr2Date((String)condition.get("opTimeZ"));
			hqlBuffer.append(" and opTime between ? and ? ");
			values.add(opTimeA);
			values.add(opTimeZ);
		}
		
		hqlBuffer.append(" order by ").append(sort);
		
		return hqlBuffer;
	}
	
}
