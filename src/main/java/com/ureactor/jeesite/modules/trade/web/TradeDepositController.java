/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/ureactor/jeesite">JeeSite</a> All rights
 * reserved.
 */
package com.ureactor.jeesite.modules.trade.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ureactor.jeesite.common.config.Global;
import com.ureactor.jeesite.common.persistence.Page;
import com.ureactor.jeesite.common.utils.StringUtils;
import com.ureactor.jeesite.common.web.BaseController;
import com.ureactor.jeesite.modules.trade.entity.Trade;
import com.ureactor.jeesite.modules.trade.service.TradeService;

/**
 * 活动示例Controller
 * 
 * @author wangshichuan@uworks.cc
 * @version 2017-09-06
 */
@Controller
@RequestMapping(value = "${adminPath}/trade/tradeDeposit")
public class TradeDepositController extends BaseController {

  @Autowired
  private TradeService tradeService;

  @ModelAttribute
  public Trade get(@RequestParam(required = false) String id) {
	Trade entity = null;
    if (StringUtils.isNotBlank(id)) {
      entity = tradeService.get(id);
    }
    if (entity == null) {
      entity = new Trade();
    }
    return entity;
  }

  @RequiresPermissions("trade:trade:view")
  @RequestMapping(value = {"list", ""})
  public String list(Trade trade, HttpServletRequest request, HttpServletResponse response, Model model) {
    Page<Trade> page = tradeService.findPage(new Page<Trade>(request, response), trade);
    model.addAttribute("page", page);
    model.addAttribute("trade", trade);
    return "modules/trade/tradeList";
  }

  @RequiresPermissions("trade:trade:view")
  @RequestMapping(value = "form")
  public String form(Trade trade, Model model) {
    model.addAttribute("trade", trade);
    return "modules/trade/tradeForm";
  }

  @RequiresPermissions("trade:trade:edit")
  @RequestMapping(value = "save")
  public String save(Trade trade, Model model, RedirectAttributes redirectAttributes) {
    if (!beanValidator(model, trade)) {
      return form(trade, model);
    }
    //对ueditor传回的字符串编码成html,解决ueditor转义的问题
    tradeService.save(trade);
    addMessage(redirectAttributes, "保存活动示例成功");
    return "redirect:" + Global.getAdminPath() + "/trade/trade/?repage";
  }

  @RequiresPermissions("trade:trade:delete")
  @RequestMapping(value = "delete")
  public String delete(Trade trade, RedirectAttributes redirectAttributes) {
    tradeService.delete(trade);
    addMessage(redirectAttributes, "删除活动示例成功");
    return "redirect:" + Global.getAdminPath() + "/trade/trade/?repage";
  }

  /**
   * 跳转到融云页面
   * 
   * @return
   */
  @RequestMapping(value = "rongIM")
  public String rongIM() {
    return "modules/rongim/im";
  }

}
