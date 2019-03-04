package com.example.goods.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.bootwebdemo.BootwebdemoApplication;
import com.example.goods.model.Goods;
import com.example.goods.vo.GoodsVO;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=BootwebdemoApplication.class)
public class GoodsServiceTest {

	@Autowired
	GoodsService goodsService;
	@Test
	public void testList(){
		GoodsVO vo = new GoodsVO();
		vo.setGoodsCode("GWB0200008");
		List<Goods> list = goodsService.getGoods(vo);
		if(list!=null && list.size()>0){
			for (Goods goods : list) {
				System.out.println(goods.getGoodsName());
			}
		}
	}
	
	@Test
	public void testSave(){
		try {
			goodsService.saveGoods();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
