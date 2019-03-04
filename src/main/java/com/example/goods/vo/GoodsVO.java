package com.example.goods.vo;

import java.io.Serializable;

/**
 * 
 * @author xcl
 *
 */
public class GoodsVO implements Serializable {
	private static final long serialVersionUID = -6989350657931519545L;
	/**
	 * 商品编码
	 */
	private String goodsCode;
	/**
	 * 商品名称
	 */
	private String goodsName;
	/**
	 * 品牌名称
	 */
	private String brandName;
	/**
	 * 销售状态默认为0(0:下架；1:上架）
	 */
	private Integer salseStatus;
	/**
	 * 是否删除0(0:未删；1:已删除）
	 */
	private Integer deleteStatus;
	/**
	 * 商品类型
	 */
	private Integer goodsType;

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public Integer getSalseStatus() {
		return salseStatus;
	}

	public void setSalseStatus(Integer salseStatus) {
		this.salseStatus = salseStatus;
	}

	public Integer getDeleteStatus() {
		return deleteStatus;
	}

	public void setDeleteStatus(Integer deleteStatus) {
		this.deleteStatus = deleteStatus;
	}

	public Integer getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(Integer goodsType) {
		this.goodsType = goodsType;
	}

}
