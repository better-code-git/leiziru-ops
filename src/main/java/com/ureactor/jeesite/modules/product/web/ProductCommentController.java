package com.ureactor.jeesite.modules.product.web;

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
import com.ureactor.jeesite.modules.product.entity.ProductComment;
import com.ureactor.jeesite.modules.product.service.ProductCommentService;

/**
 * 房源评价Controller
 * 
 * @author ForrestCao
 * @version 2017-09-06
 */
@Controller
@RequestMapping(value = "${adminPath}/product/productComment")
public class ProductCommentController extends BaseController {

	@Autowired
	private ProductCommentService productCommentService;

	@ModelAttribute
	public ProductComment get(@RequestParam(required = false) String id) {
		ProductComment entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = productCommentService.get(id);
		}
		if (entity == null) {
			entity = new ProductComment();
		}
		return entity;
	}

	@RequestMapping(value = { "list", "" })
	public String list(ProductComment productComment, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<ProductComment> page = productCommentService.findPage(new Page<ProductComment>(request, response),
				productComment);
		model.addAttribute("page", page);
		model.addAttribute("productComment", productComment);
		return "modules/product/productCommentList";
	}

	@RequestMapping(value = "form")
	public String form(ProductComment productComment, Model model) {
		model.addAttribute("productComment", productComment);
		return "modules/product/productCommentForm";
	}

	@RequestMapping(value = "save")
	public String save(ProductComment productComment, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, productComment)) {
			return form(productComment, model);
		}
		productCommentService.save(productComment);
		addMessage(redirectAttributes, "保存房源管理成功");
		return "redirect:" + Global.getAdminPath() + "/product/product/?repage";
	}

	@RequestMapping(value = "delete")
	@ResponseBody
	public AjaxJson delete(ProductComment productComment, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String message = "操作成功！";
		j.setSuccess(true);
		productCommentService.delete(productComment);
		j.setMsg(message);
		return j;
	}

	@RequestMapping(value = "updateStatus")
	@ResponseBody
	public AjaxJson updateStatus(ProductComment productComment, Model model, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String message = "操作成功！";
		j.setSuccess(true);
		productCommentService.updateStatus(productComment);
		j.setMsg(message);
		return j;
	}

}
