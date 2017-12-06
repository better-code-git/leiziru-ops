package com.ureactor.jeesite.modules.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ureactor.jeesite.common.persistence.CrudDao;
import com.ureactor.jeesite.modules.member.entity.Member;

/**
 * 会员信息DAO接口
 * @author ForrestCao
 * @version 2017-09-06
 */
@Mapper
public interface MemberDao extends CrudDao<Member> {

	List<Member> getNewCount(Member member);
	void updateStatus(Member member);
	
}