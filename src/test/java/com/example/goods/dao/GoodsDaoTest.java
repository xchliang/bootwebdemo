package com.example.goods.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.bootwebdemo.BootwebdemoApplication;
import com.example.goods.model.Goods;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=BootwebdemoApplication.class)
public class GoodsDaoTest {
	@Autowired
	GoodsDao goodsDao;
	
	@Test
	public void testFind() {
		Goods goods = goodsDao.findByGoodsCode("GWB0200008");
		System.out.println(goods.getGoodsName());
	}

}
