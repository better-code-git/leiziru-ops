package com.ureactor.jeesite.modules.member.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ureactor.jeesite.common.config.Global;
import com.ureactor.jeesite.common.json.AjaxJson;
import com.ureactor.jeesite.common.persistence.Page;
import com.ureactor.jeesite.common.utils.StringUtils;
import com.ureactor.jeesite.common.web.BaseController;
import com.ureactor.jeesite.modules.member.entity.Member;
import com.ureactor.jeesite.modules.member.service.MemberService;

/**
 * 会员管理Controller
 * 
 * @author ForrestCao
 * @version 2017-09-06
 */
@Controller
@RequestMapping(value = "${adminPath}/member/member")
public class MemberController extends BaseController {

	@Autowired
	private MemberService memberService;

	@ModelAttribute
	public Member get(@RequestParam(required = false) String id) {
		Member entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = memberService.get(id);
		}
		if (entity == null) {
			entity = new Member();
		}
		return entity;
	}

	@RequestMapping(value = { "list", "" })
	public String list(Member member, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Member> page = memberService.findPage(new Page<Member>(request, response), member);
		model.addAttribute("page", page);
		model.addAttribute("member", member);
		return "modules/member/memberList";
	}

	@RequestMapping(value = "form")
	public String form(Member member, Model model) {
		model.addAttribute("member", member);
		return "modules/member/memberForm";
	}

	@RequestMapping(value = "save")
	@ResponseBody
	public AjaxJson save(Member member, Model model, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String message = "操作成功！";
		j.setSuccess(true);
		if (!beanValidator(model, member)) {
			message = "数据校验失败";// 把校验结果但会到string中
			j.setSuccess(false);
		}
		memberService.save(member);
		j.setMsg(message);
		return j;
	}
	
	@RequestMapping(value = "updateStatus")
	@ResponseBody
	public AjaxJson updateStatus(Member member, Model model, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String message = "操作成功！";
		j.setSuccess(true);
		memberService.updateStatus(member);
		j.setMsg(message);
		return j;
	}
	
	

	@RequestMapping(value = "delete")
	public String delete(Member member, RedirectAttributes redirectAttributes) {
		memberService.delete(member);
		addMessage(redirectAttributes, "删除活动示例成功");
		return "redirect:" + Global.getAdminPath() + "/member/member/?repage";
	}

	@RequestMapping(value = "deleteAll")
	@ResponseBody
	public AjaxJson deleteAll(Member member, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String message = "操作成功！";
		j.setSuccess(true);
		String ids = member.getIds();
		if(ids!=null&&!"".equals(ids)){
			String idArray[] =ids.split(",");
			List<String> idList =new ArrayList<String>();
			for (String id : idArray) {
				idList.add(id);
			}
			Member mb =new Member();
			mb.setIdList(idList);
			memberService.deleteBatchByLogic(mb);
		}
		j.setMsg(message);
		return j;
	}

}
