package com.ureactor.jeesite.modules.member.web;

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
import com.ureactor.jeesite.modules.member.entity.MemberWallet;
import com.ureactor.jeesite.modules.member.service.MemberWalletService;

/**
 * 会员钱包Controller
 * 
 * @author ForrestCao
 * @version 2017-09-06
 */
@Controller
@RequestMapping(value = "${adminPath}/member/memberWallet")
public class MemberWalletController extends BaseController {

  @Autowired
  private MemberWalletService memberWalletService;

  @ModelAttribute
  public MemberWallet get(@RequestParam(required = false) String id) {
	MemberWallet entity = null;
    if (StringUtils.isNotBlank(id)) {
      entity = memberWalletService.get(id);
    }
    if (entity == null) {
      entity = new MemberWallet();
    }
    return entity;
  }

  @RequiresPermissions("member:memberWallet:view")
  @RequestMapping(value = {"list", ""})
  public String list(MemberWallet memberWallet, HttpServletRequest request, HttpServletResponse response, Model model) {
    Page<MemberWallet> page = memberWalletService.findPage(new Page<MemberWallet>(request, response), memberWallet);
    model.addAttribute("page", page);
    model.addAttribute("memberWallet", memberWallet);
    return "modules/member/memberWalletList";
  }

  @RequiresPermissions("member:memberWallet:view")
  @RequestMapping(value = "form")
  public String form(MemberWallet memberWallet, Model model) {
    model.addAttribute("memberWallet", memberWallet);
    return "modules/member/memberWalletForm";
  }

  @RequiresPermissions("member:memberWallet:edit")
  @RequestMapping(value = "save")
  public String save(MemberWallet memberWallet, Model model, RedirectAttributes redirectAttributes) {
    if (!beanValidator(model, memberWallet)) {
      return form(memberWallet, model);
    }
    memberWalletService.save(memberWallet);
    addMessage(redirectAttributes, "保存活动示例成功");
    return "redirect:" + Global.getAdminPath() + "/member/memberWallet/?repage";
  }

  @RequiresPermissions("member:memberWallet:delete")
  @RequestMapping(value = "delete")
  public String delete(MemberWallet memberWallet, RedirectAttributes redirectAttributes) {
	  memberWalletService.delete(memberWallet);
    addMessage(redirectAttributes, "删除活动示例成功");
    return "redirect:" + Global.getAdminPath() + "/member/memberWallet/?repage";
  }


}
