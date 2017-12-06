package com.ureactor.jeesite.modules.product.web;

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

import com.ureactor.jeesite.common.json.AjaxJson;
import com.ureactor.jeesite.common.persistence.Page;
import com.ureactor.jeesite.common.utils.StringUtils;
import com.ureactor.jeesite.common.web.BaseController;
import com.ureactor.jeesite.modules.overall.entity.ServiceArea;
import com.ureactor.jeesite.modules.overall.service.ServiceAreaService;
import com.ureactor.jeesite.modules.product.entity.ProductBlock;
import com.ureactor.jeesite.modules.product.entity.ProductBlockPrice;
import com.ureactor.jeesite.modules.product.entity.ProductRoom;
import com.ureactor.jeesite.modules.product.service.ProductBlockPriceService;
import com.ureactor.jeesite.modules.product.service.ProductBlockService;
import com.ureactor.jeesite.modules.product.service.ProductRoomService;
import com.ureactor.jeesite.modules.sys.utils.LzrConstant;

/**
 * 房源小区管理Controller
 * 
 * @author ForrestCao
 * @version 2017-09-06
 */
@Controller
@RequestMapping(value = "${adminPath}/product/productBlock")
public class ProductBlockController extends BaseController {

	@Autowired
	private ProductBlockService productBlockService;
	@Autowired
	private ProductRoomService productRoomService;
	@Autowired
	private ProductBlockPriceService productBlockPriceService;
	@Autowired
	private ServiceAreaService serviceAreaService;

	@ModelAttribute
	public ProductBlock get(@RequestParam(required = false) String id) {
		ProductBlock entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = productBlockService.get(id);
		}
		if (entity == null) {
			entity = new ProductBlock();
		}
		return entity;
	}

	@RequestMapping(value = { "list", "" })
	public String list(ProductBlock productBlock, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ProductBlock> page = productBlockService.findPage(new Page<ProductBlock>(request, response), productBlock);
		List<ServiceArea> serviceAreaList = serviceAreaService.findList(new ServiceArea(null, LzrConstant.arealevelP));
		model.addAttribute("page", page);
		model.addAttribute("serviceAreaList", serviceAreaList);
		model.addAttribute("productBlock", productBlock);
		return "modules/product/productBlockList";
	}

	@RequestMapping(value = "form")
	public String form(ProductBlock productBlock, Model model) {
		if(null==productBlock.getId()||"".equals(productBlock.getId())){
			List<ServiceArea> serviceAreaListP = serviceAreaService.findList(new ServiceArea(null, LzrConstant.arealevelP));
			model.addAttribute("serviceAreaListP", serviceAreaListP);
		}else{
			List<ServiceArea> serviceAreaListP = serviceAreaService.findList(new ServiceArea(null, LzrConstant.arealevelP));
			List<ServiceArea> serviceAreaListC = serviceAreaService.findList(new ServiceArea(String.valueOf(productBlock.getProductCityId()), LzrConstant.arealevelC));
			List<ServiceArea> serviceAreaListZ = serviceAreaService.findList(new ServiceArea(String.valueOf(productBlock.getProductZoneId()), LzrConstant.arealevelZ));
			List<ServiceArea> serviceAreaListM = serviceAreaService.findList(new ServiceArea(String.valueOf(productBlock.getProductMetroId()), LzrConstant.arealevelM));
			List<ServiceArea> serviceAreaListD= serviceAreaService.findList(new ServiceArea(String.valueOf(productBlock.getProductDistrictId()), LzrConstant.arealevelD));
			model.addAttribute("serviceAreaListP", serviceAreaListP);
			model.addAttribute("serviceAreaListC", serviceAreaListC);
			model.addAttribute("serviceAreaListZ", serviceAreaListZ);
			model.addAttribute("serviceAreaListM", serviceAreaListM);
			model.addAttribute("serviceAreaListD", serviceAreaListD);
		}
		List<ProductRoom>  roomList =productRoomService.findList(new ProductRoom());
		// 待处理
		String roomType= String.valueOf(roomList.get(0).getRoomType());
		ProductBlockPrice productBlockPrice =new ProductBlockPrice();
		productBlockPrice.setRoomType(roomType);
		List<ProductBlockPrice> blockPriceList =productBlockPriceService.findList(new ProductBlockPrice());
		productBlock.setRoomList(roomList);
		productBlock.setBlockPriceList(blockPriceList);
		return "modules/product/productBlockForm";
	}
	
	@RequestMapping(value = "blockPrice")
	public String blockPrice(ProductBlock productBlock, Model model) {
		List<ProductRoom>  roomList =productRoomService.findList(new ProductRoom());
		String roomType= String.valueOf(roomList.get(0).getRoomType());
		ProductBlockPrice productBlockPrice =new ProductBlockPrice();
		productBlockPrice.setRoomType(roomType);
		List<ProductBlockPrice> blockPriceList =productBlockPriceService.findList(new ProductBlockPrice());
		productBlock.setRoomList(roomList);
		productBlock.setBlockPriceList(blockPriceList);
		return "modules/product/productBlockPriceForm";
	}

	@RequestMapping(value = "save")
	@ResponseBody
	public AjaxJson save(ProductBlock productBlock, Model model, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String message = "操作成功！";
		j.setSuccess(true);
		if (!beanValidator(model, productBlock)) {
			message = "数据校验失败";// 把校验结果但会到string中
			j.setSuccess(false);
		}
		productBlockService.save(productBlock);
		j.setMsg(message);
		return j;
	}
	
	
	@RequestMapping(value = "savePrice")
	@ResponseBody
	public AjaxJson savePrice(ProductBlock productBlock, Model model, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String message = "操作成功！";
		j.setSuccess(true);
		if (!beanValidator(model, productBlock)) {
			message = "数据校验失败";// 把校验结果但会到string中
			j.setSuccess(false);
		}
		productBlockService.save(productBlock);
		j.setMsg(message);
		return j;
	}
	

	@RequestMapping(value = "delete")
	@ResponseBody
	public AjaxJson delete(ProductBlock productBlock, RedirectAttributes redirectAttributes) {
		AjaxJson j = new AjaxJson();
		String message = "操作成功！";
		j.setSuccess(true);
		productBlockService.delete(productBlock);
		j.setMsg(message);
		return j;
	}


}
