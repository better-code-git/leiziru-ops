/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/ureactor/jeesite">JeeSite</a> All rights reserved.
 */
package com.ureactor.jeesite.modules.member.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ureactor.jeesite.common.persistence.Page;
import com.ureactor.jeesite.common.service.CrudService;
import com.ureactor.jeesite.modules.member.dao.MemberWalletDao;
import com.ureactor.jeesite.modules.member.entity.MemberWallet;

/**
 * 会员信息Service
 * @author ForrestCao
 * @version 2017-09-06
 */
@Service
@Transactional(readOnly = true)
public class MemberWalletService extends CrudService<MemberWalletDao, MemberWallet> {

	public MemberWallet get(String id) {
		return super.get(id);
	}
	
	public List<MemberWallet> findList(MemberWallet memberwallet) {
		return super.findList(memberwallet);
	}
	
	public Page<MemberWallet> findPage(Page<MemberWallet> page, MemberWallet memberwallet) {
		return super.findPage(page, memberwallet);
	}
	
	@Transactional(readOnly = false)
	public void save(MemberWallet memberwallet) {
		super.save(memberwallet);
	}
	
	@Transactional(readOnly = false)
	public void delete(MemberWallet memberwallet) {
		super.delete(memberwallet);
	}
	
}