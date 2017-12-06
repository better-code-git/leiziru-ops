package com.ureactor.jeesite.modules.homePage.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ureactor.jeesite.common.service.CrudService;
import com.ureactor.jeesite.common.utils.DateUtils;
import com.ureactor.jeesite.common.utils.SpringContextHolder;
import com.ureactor.jeesite.modules.homePage.dao.HomePageDao;
import com.ureactor.jeesite.modules.homePage.entity.HomePage;
import com.ureactor.jeesite.modules.homePage.entity.OrderProductCount;
import com.ureactor.jeesite.modules.member.dao.MemberDao;
import com.ureactor.jeesite.modules.member.entity.Member;
import com.ureactor.jeesite.modules.order.dao.OrderDao;
import com.ureactor.jeesite.modules.order.entity.Order;
import com.ureactor.jeesite.modules.product.dao.ProductDao;
import com.ureactor.jeesite.modules.product.entity.Product;

/**
 * 首页管理Service
 * @author ForrestCao
 * @version 2017-09-06
 */
@Service
@Transactional(readOnly = true)
public class HomePageService extends CrudService<HomePageDao, HomePage> {

	private static MemberDao memberDao = SpringContextHolder.getBean(MemberDao.class);
	private static ProductDao productDao = SpringContextHolder.getBean(ProductDao.class);
	private static OrderDao orderDao = SpringContextHolder.getBean(OrderDao.class);
	
	public HomePage get(String id) {
		return super.get(id);
	}
	public HomePage getInit() {
		HomePage hp =new HomePage();
		Date today =DateUtils.getTodayStart();
		Member member =new Member();
		member.setCreateDate(today);
		List<Member> memberList =memberDao.getNewCount(member);
		hp.setIncreasedMember(memberList==null?0:memberList.size());
		Product product =new Product();
		product.setCreateDate(today);
		List<Product> productList =productDao.getNewCount(product);
		Order order =new Order();
		order.setCreateDate(today);
		List<Order> orderList =orderDao.getNewCount(order);
		hp.setIncreasedMember(memberList==null?0:memberList.size());
		hp.setIncreasedProduct(productList==null?0:productList.size());
		hp.setIncreasedOrder(orderList==null?0:orderList.size());
		List<OrderProductCount> orderProductCountList =new ArrayList<OrderProductCount>();
		hp.setOrderProductCountList(orderProductCountList);
		return hp;
	}
	
}