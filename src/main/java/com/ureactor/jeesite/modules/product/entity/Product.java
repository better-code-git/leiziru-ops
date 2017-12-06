package com.ureactor.jeesite.modules.product.entity;


import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ureactor.jeesite.common.persistence.DataEntity;

/**
 * 房源实体Entity
 * @author ForrestCao
 * @version 2017-09-06
 */
public class Product extends DataEntity<Product> {
	
	private static final long serialVersionUID = 1L;
	private String  productName;//房源名称
	private Integer productType;//房屋类型
	private String  productTypeNote;//房屋类型详细
	private Integer productStatus;//状态 0- 上架   1-下架
	private Double productPrice;//房源价格
	private String productLable;//房源标签
	private String productIntroduce;//房源介绍
	private Integer productRentType;//出租方式 0-整租 1- 合租
	private Integer productDistrictId;//房源所在商圈
	private String productAddressDetail;//房源详细地址
	private Integer productZoneId;//房源所在地区
	private Integer productProvinceId;//房源所在省份
	private Integer productCityId;//房源所在城市
	private Integer productMetroId;//地铁线
	private Integer productOrientation;//房源朝向
	private Double productArea;//房源面积
	private String productFloor;//房源楼层
	private Integer productSource;//房屋来源  0- 自由租房 1-房东整租
	private Integer recommendType;//推荐类型
	private Integer decorateType;
	private String blockIntroduce;//小区介绍
	private String trafficIntroduce;//交通介绍
	private List<ProductAssort> productAssortList;//房屋配套列表
	private List<ProductPic> productPicList; //商品图片
	private Date updateStartDate;//更新开始时间
	private Date updateEndDate;//更新结束时间
	private String productProfileUrl;//房源主图
	private String productProfileUrlOld;//房源主图老图
	private String productBlockPicUrl;//小区详情图片
	private String productBlockPicUrlOld;//小区详情图片老图
	private MultipartFile  imgFileData; //图片上传文件
	private MultipartFile  imgFileDataBlock; //小区图片上传文件
	private String  productLocalLat;//房源地址维度
	private String  productLocalLng;//房源地址精
	private List<ProductRoom>  productRoomList; //房源详情列表
	private String blockId;//小区编号

	
	public Product() {
		super();
	}

	public Product(String id){
		super(id);
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getProductType() {
		return productType;
	}

	public void setProductType(Integer productType) {
		this.productType = productType;
	}

	public String getProductTypeNote() {
		return productTypeNote;
	}

	public void setProductTypeNote(String productTypeNote) {
		this.productTypeNote = productTypeNote;
	}

	public Integer getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(Integer productStatus) {
		this.productStatus = productStatus;
	}

	public Double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductLable() {
		return productLable;
	}

	public void setProductLable(String productLable) {
		this.productLable = productLable;
	}

	public String getProductIntroduce() {
		return productIntroduce;
	}

	public void setProductIntroduce(String productIntroduce) {
		this.productIntroduce = productIntroduce;
	}

	public Integer getProductOrientation() {
		return productOrientation;
	}

	public void setProductOrientation(Integer productOrientation) {
		this.productOrientation = productOrientation;
	}

	public Double getProductArea() {
		return productArea;
	}

	public void setProductArea(Double productArea) {
		this.productArea = productArea;
	}

	public String getProductFloor() {
		return productFloor;
	}

	public void setProductFloor(String productFloor) {
		this.productFloor = productFloor;
	}

	public Integer getProductSource() {
		return productSource;
	}

	public void setProductSource(Integer productSource) {
		this.productSource = productSource;
	}

	public Integer getRecommendType() {
		return recommendType;
	}

	public void setRecommendType(Integer recommendType) {
		this.recommendType = recommendType;
	}

	public Integer getDecorateType() {
		return decorateType;
	}

	public void setDecorateType(Integer decorateType) {
		this.decorateType = decorateType;
	}

	public String getBlockIntroduce() {
		return blockIntroduce;
	}

	public void setBlockIntroduce(String blockIntroduce) {
		this.blockIntroduce = blockIntroduce;
	}

	public Integer getProductRentType() {
		return productRentType;
	}

	public void setProductRentType(Integer productRentType) {
		this.productRentType = productRentType;
	}

	public Integer getProductZoneId() {
		return productZoneId;
	}

	public void setProductZoneId(Integer productZoneId) {
		this.productZoneId = productZoneId;
	}

	public Integer getProductProvinceId() {
		return productProvinceId;
	}

	public void setProductProvinceId(Integer productProvinceId) {
		this.productProvinceId = productProvinceId;
	}

	public Integer getProductCityId() {
		return productCityId;
	}

	public void setProductCityId(Integer productCityId) {
		this.productCityId = productCityId;
	}

	public Integer getProductMetroId() {
		return productMetroId;
	}

	public void setProductMetroId(Integer productMetroId) {
		this.productMetroId = productMetroId;
	}

	public Integer getProductDistrictId() {
		return productDistrictId;
	}

	public void setProductDistrictId(Integer productDistrictId) {
		this.productDistrictId = productDistrictId;
	}

	public String getProductAddressDetail() {
		return productAddressDetail;
	}

	public void setProductAddressDetail(String productAddressDetail) {
		this.productAddressDetail = productAddressDetail;
	}

	public String getTrafficIntroduce() {
		return trafficIntroduce;
	}

	public void setTrafficIntroduce(String trafficIntroduce) {
		this.trafficIntroduce = trafficIntroduce;
	}

	public List<ProductAssort> getProductAssortList() {
		return productAssortList;
	}

	public void setProductAssortList(List<ProductAssort> productAssortList) {
		this.productAssortList = productAssortList;
	}

	public Date getUpdateStartDate() {
		return updateStartDate;
	}

	public void setUpdateStartDate(Date updateStartDate) {
		this.updateStartDate = updateStartDate;
	}

	public Date getUpdateEndDate() {
		return updateEndDate;
	}

	public void setUpdateEndDate(Date updateEndDate) {
		this.updateEndDate = updateEndDate;
	}

	public List<ProductPic> getProductPicList() {
		return productPicList;
	}

	public void setProductPicList(List<ProductPic> productPicList) {
		this.productPicList = productPicList;
	}

	public String getProductProfileUrl() {
		return productProfileUrl;
	}

	public void setProductProfileUrl(String productProfileUrl) {
		this.productProfileUrl = productProfileUrl;
	}

	public String getProductProfileUrlOld() {
		return productProfileUrlOld;
	}

	public void setProductProfileUrlOld(String productProfileUrlOld) {
		this.productProfileUrlOld = productProfileUrlOld;
	}

	public MultipartFile getImgFileData() {
		return imgFileData;
	}

	public void setImgFileData(MultipartFile imgFileData) {
		this.imgFileData = imgFileData;
	}

	public String getProductLocalLat() {
		return productLocalLat;
	}

	public void setProductLocalLat(String productLocalLat) {
		this.productLocalLat = productLocalLat;
	}

	public String getProductLocalLng() {
		return productLocalLng;
	}

	public void setProductLocalLng(String productLocalLng) {
		this.productLocalLng = productLocalLng;
	}

	public String getProductBlockPicUrl() {
		return productBlockPicUrl;
	}

	public void setProductBlockPicUrl(String productBlockPicUrl) {
		this.productBlockPicUrl = productBlockPicUrl;
	}

	public String getProductBlockPicUrlOld() {
		return productBlockPicUrlOld;
	}

	public void setProductBlockPicUrlOld(String productBlockPicUrlOld) {
		this.productBlockPicUrlOld = productBlockPicUrlOld;
	}

	public MultipartFile getImgFileDataBlock() {
		return imgFileDataBlock;
	}

	public void setImgFileDataBlock(MultipartFile imgFileDataBlock) {
		this.imgFileDataBlock = imgFileDataBlock;
	}

	public List<ProductRoom> getProductRoomList() {
		return productRoomList;
	}

	public void setProductRoomList(List<ProductRoom> productRoomList) {
		this.productRoomList = productRoomList;
	}

	public String getBlockId() {
		return blockId;
	}

	public void setBlockId(String blockId) {
		this.blockId = blockId;
	}

	
}