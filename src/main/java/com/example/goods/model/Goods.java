package com.example.goods.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.example.entity.BaseEntity;

/**
 * 商品表
 */
@Entity
@Table(name = "goods")
public class Goods extends BaseEntity {
	private static final long serialVersionUID = -4506754815908099157L;
	/**
	 * 商品编码
	 */
	@Column(name = "goods_code")
	private String goodsCode;
	/**
	 * 商品名称
	 */
	@Column(name = "goods_name")
	private String goodsName;
	/**
	 * 品牌名称
	 */
	@Column(name = "brand_name")
	private String brandName;
	/**
	 * 销售状态默认为0(0:下架；1:上架）
	 */
	@Column(name = "salse_status")
	private Integer salseStatus;
	/**
	 * 是否删除0(0:未删；1:已删除）
	 */
	@Column(name = "delete_status")
	private Integer deleteStatus;
	/**
	 * 商品类型
	 */
	@Column(name = "goods_type")
	private Integer goodsType;
	
	public Goods(){
		
	}
	public Goods(String goodsCode, String goodsName, Integer salseStatus) {
		super();
		this.goodsCode = goodsCode;
		this.goodsName = goodsName;
		this.salseStatus = salseStatus;
	}

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
