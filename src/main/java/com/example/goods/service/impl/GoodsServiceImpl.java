package com.example.goods.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.goods.dao.GoodsDao;
import com.example.goods.model.Goods;
import com.example.goods.service.GoodsService;
import com.example.goods.vo.GoodsVO;

@Service("goodsService")
@Transactional
public class GoodsServiceImpl implements GoodsService {

	/**
	 * EntityManager 实体管理器<br>
	 * 注解@PersistenceContext 注入的是实体管理器，执行持久化操作。<br>
	 * unitName是用数据源创建实体管理器工厂时指定的persistenceUnit 
	 */
	@PersistenceContext(unitName = "goodsPersistenceUnit")
	private EntityManager entityManager;

	@Autowired
	private GoodsDao goodsDao;

	@Override
	public List<Goods> getGoods(GoodsVO vo) {
		if(vo!=null){
			StringBuilder sql = new StringBuilder("SELECT g FROM Goods g WHERE 1=1 ");
			Map<String,Object> params = new HashMap<String,Object>();
			if (vo.getGoodsName()!=null && !"".equals(vo.getGoodsName().trim())) {
				sql.append(" and goodsName like :goodsName");
				params.put("goodsName", "%"+vo.getGoodsName().trim()+"%");
	        }
			if (vo.getGoodsCode()!=null && !"".equals(vo.getGoodsCode().trim())) {
				sql.append(" and goodsCode like :goodsCode");
				params.put("goodsCode", "%"+vo.getGoodsCode().trim()+"%");
			}
	        if (vo.getDeleteStatus()!=null) {
	        	sql.append(" and deleteStatus=:deleteStatus");
	        	params.put("deleteStatus", vo.getDeleteStatus());
	        }
	        sql.append(" ORDER BY goodsCode");
	        //查询
			Query query = entityManager.createQuery(sql.toString());
			Iterator<Entry<String, Object>> ite = params.entrySet().iterator();
			Entry<String, Object> entry = null;
			while(ite.hasNext()){
				entry = ite.next();
				query.setParameter(entry.getKey(), entry.getValue());
			}
			@SuppressWarnings("unchecked")
			List<Goods> list = (List<Goods>)query.getResultList();
			return list;
		}
		return null;
	}

	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,readOnly=false)
	@Override
	public void saveGoods() {
		goodsDao.save(new Goods("666", "商品666", 1));
		goodsDao.save(new Goods("777", "商品777", 0));
		System.out.println(1/0);
		goodsDao.save(new Goods("888", "商品888", 1));
	}

}
