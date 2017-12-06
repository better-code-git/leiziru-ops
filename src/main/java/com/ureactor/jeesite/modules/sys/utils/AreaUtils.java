/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.ureactor.jeesite.modules.sys.utils;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;
import com.ureactor.jeesite.common.utils.SpringContextHolder;
import com.ureactor.jeesite.modules.overall.dao.ServiceAreaDao;
import com.ureactor.jeesite.modules.overall.entity.ServiceArea;
import com.ureactor.jeesite.modules.sys.entity.Dict;

/**
 * 区域工具类
 * @author ThinkGem
 * @version 2013-5-29
 */
public class AreaUtils {
	
	private static ServiceAreaDao serviceAreaDao = SpringContextHolder.getBean(ServiceAreaDao.class);

	public static final String CACHE_AREA_MAP = "areaMap";
	
	public static String getAreaLabel(String areaId,String defaultValue){
		ServiceArea sa =serviceAreaDao.get(areaId);
		if(sa!=null){
			defaultValue =sa.getAreaName();
		}
		return defaultValue;
	}
	
	
}
