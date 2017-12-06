/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/ureactor/jeesite">JeeSite</a> All rights reserved.
 */
package com.ureactor.jeesite.modules.trade.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ureactor.jeesite.common.persistence.Page;
import com.ureactor.jeesite.common.service.CrudService;
import com.ureactor.jeesite.modules.trade.dao.TradeDao;
import com.ureactor.jeesite.modules.trade.entity.Trade;

/**
 * 活动示例Service
 * @author wangshichuan@uworks.cc
 * @version 2017-09-06
 */
@Service
@Transactional(readOnly = true)
public class TradeService extends CrudService<TradeDao, Trade> {

	public Trade get(String id) {
		return super.get(id);
	}
	
	public List<Trade> findList(Trade trade) {
		return super.findList(trade);
	}
	
	public Page<Trade> findPage(Page<Trade> page, Trade trade) {
		return super.findPage(page, trade);
	}
	
	@Transactional(readOnly = false)
	public void save(Trade trade) {
		super.save(trade);
	}
	
	@Transactional(readOnly = false)
	public void delete(Trade trade) {
		super.delete(trade);
	}
	
}