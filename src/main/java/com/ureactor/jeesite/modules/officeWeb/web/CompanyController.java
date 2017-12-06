package com.ureactor.jeesite.modules.officeWeb.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ureactor.jeesite.common.config.Global;
import com.ureactor.jeesite.common.utils.StringUtils;
import com.ureactor.jeesite.common.web.BaseController;
import com.ureactor.jeesite.modules.officeWeb.entity.Company;
import com.ureactor.jeesite.modules.officeWeb.service.CompanyService;

/**
 * 公司信息管理Controller
 * 
 * @author ForrestCao
 * @version 2017-09-06
 */
@Controller
@RequestMapping(value = "${adminPath}/officeWeb/company")
public class CompanyController extends BaseController {

  @Autowired
  private CompanyService companyService;

  @ModelAttribute
  public Company get(@RequestParam(required = false) String id) {
	Company entity = null;
    if (StringUtils.isNotBlank(id)) {
      entity = companyService.get(id);
    }
    if (entity == null) {
      entity = new Company();
    }
    return entity;
  }


  @RequestMapping(value = {"form", ""})
  public String form(Company company, Model model) {
	if(company==null||company.getId()==null||"".equals(company.getId())){
		List<Company>  companyList =companyService.findList(company);
		if(companyList.size()>0){
			company=companyList.get(0);
		}
	}
    model.addAttribute("company", company);
    return "modules/officeWeb/companyForm";
  }

  @RequestMapping(value = "save")
  public String save(Company company, Model model, RedirectAttributes redirectAttributes) {
    if (!beanValidator(model, company)) {
      return form(company, model);
    }
    companyService.save(company);
    addMessage(redirectAttributes, "保存房源管理成功");
    return "redirect:" + Global.getAdminPath() + "/officeWeb/company/?repage";
  }

}
