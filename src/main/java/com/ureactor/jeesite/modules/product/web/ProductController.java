package com.ureactor.jeesite.modules.product.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
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
import com.ureactor.jeesite.modules.overall.entity.ServiceArea;
import com.ureactor.jeesite.modules.overall.service.ServiceAreaService;
import com.ureactor.jeesite.modules.product.entity.Product;
import com.ureactor.jeesite.modules.product.entity.ProductBlock;
import com.ureactor.jeesite.modules.product.entity.ProductPic;
import com.ureactor.jeesite.modules.product.service.ProductBlockService;
import com.ureactor.jeesite.modules.product.service.ProductService;
import com.ureactor.jeesite.modules.sys.utils.LzrConstant;

/**
 * 房源管理Controller
 * 
 * @author ForrestCao
 * @version 2017-09-06
 */
@Controller
@RequestMapping(value = "${adminPath}/product/product")
public class ProductController extends BaseController {

	@Autowired
	private ProductService productService;
	@Autowired
	private ProductBlockService productBlockService;
	@Autowired
	private ServiceAreaService serviceAreaService;

	@ModelAttribute
	public Product get(@RequestParam(required = false) String id) {
		Product entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = productService.get(id);
		}
		if (entity == null) {
			entity = new Product();
		}
		return entity;
	}

	@RequiresPermissions("product:product:view")
	@RequestMapping(value = { "list", "" })
	public String list(Product product, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Product> page = productService.findPage(new Page<Product>(request, response), product);
		List<ServiceArea> serviceAreaList = serviceAreaService.findList(new ServiceArea(null, LzrConstant.arealevelP));
		model.addAttribute("page", page);
		model.addAttribute("serviceAreaList", serviceAreaList);
		model.addAttribute("product", product);
		return "modules/product/productList";
	}

	@RequiresPermissions("product:product:view")
	@RequestMapping(value = "form")
	public String form(Product product, Model model) {
		if(null==product.getId()||"".equals(product.getId())){
			List<ServiceArea> serviceAreaListP = serviceAreaService.findList(new ServiceArea(null, LzrConstant.arealevelP));
			model.addAttribute("serviceAreaListP", serviceAreaListP);
		}else{
			List<ServiceArea> serviceAreaListP = serviceAreaService.findList(new ServiceArea(null, LzrConstant.arealevelP));
			List<ServiceArea> serviceAreaListC = serviceAreaService.findList(new ServiceArea(String.valueOf(product.getProductCityId()), LzrConstant.arealevelC));
			List<ServiceArea> serviceAreaListZ = serviceAreaService.findList(new ServiceArea(String.valueOf(product.getProductZoneId()), LzrConstant.arealevelZ));
			List<ServiceArea> serviceAreaListM = serviceAreaService.findList(new ServiceArea(String.valueOf(product.getProductMetroId()), LzrConstant.arealevelM));
			List<ServiceArea> serviceAreaListD= serviceAreaService.findList(new ServiceArea(String.valueOf(product.getProductDistrictId()), LzrConstant.arealevelD));
			model.addAttribute("serviceAreaListP", serviceAreaListP);
			model.addAttribute("serviceAreaListC", serviceAreaListC);
			model.addAttribute("serviceAreaListZ", serviceAreaListZ);
			model.addAttribute("serviceAreaListM", serviceAreaListM);
			model.addAttribute("serviceAreaListD", serviceAreaListD);
		}
		List<ProductPic> productPicList =product.getProductPicList();
		int psize = 0;
		if(productPicList!=null){
			psize=productPicList.size();
		}else{
			productPicList=new ArrayList<ProductPic>();
		}
		for (int i=0;i<6-psize;i++){
			ProductPic pic =new ProductPic();
			productPicList.add(pic);
		}
		List<ProductBlock> blockList =productBlockService.findList(new ProductBlock());
		product.setProductPicList(productPicList);
		model.addAttribute("blockList", blockList);
		model.addAttribute("product", product);
		return "modules/product/productForm";
	}

	@RequiresPermissions("product:product:edit")
	@RequestMapping(value = "save")
	@ResponseBody
	public AjaxJson save(Product product, Model model, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String message = "操作成功！";
		j.setSuccess(true);
		if (!beanValidator(model, product)) {
			message = "数据校验失败";// 把校验结果但会到string中
			j.setSuccess(false);
		}
		productService.save(product);
		j.setMsg(message);
		return j;
	}

	@RequiresPermissions("product:product:edit")
	@RequestMapping(value = "delete")
	public String delete(Product product, RedirectAttributes redirectAttributes) {
		productService.delete(product);
		addMessage(redirectAttributes, "删除房源管理成功");
		return "redirect:" + Global.getAdminPath() + "/product/product/?repage";
	}

	@RequestMapping(value = "deleteAll")
	@ResponseBody
	public AjaxJson deleteAll(Product product, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String message = "操作成功！";
		j.setSuccess(true);
		String ids = product.getIds();
		if (ids != null && !"".equals(ids)) {
			String idArray[] = ids.split(",");
			List<String> idList = new ArrayList<String>();
			for (String id : idArray) {
				idList.add(id);
			}
			Product pd = new Product();
			pd.setIdList(idList);
			productService.deleteBatchByLogic(pd);
		}
		j.setMsg(message);
		return j;
	}

}
