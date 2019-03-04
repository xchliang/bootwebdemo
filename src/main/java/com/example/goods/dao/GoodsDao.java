package com.example.goods.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.goods.model.Goods;
public interface GoodsDao extends JpaRepository<Goods, Long>, 
	JpaSpecificationExecutor<Goods> {
	
	Goods findByGoodsCode(String goodsCode);
	
}
