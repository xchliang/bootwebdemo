package com.example.goods.service;

import java.util.List;

import com.example.goods.model.Goods;
import com.example.goods.vo.GoodsVO;


public interface GoodsService {
	
	public List<Goods> getGoods(GoodsVO vo);
	public void saveGoods();
	
}
