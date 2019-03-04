package com.example.cutomer.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.cutomer.model.User;
import com.example.cutomer.repository.UserRepository;
import com.example.cutomer.service.UserService;
import com.example.cutomer.vo.UserVO;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{
	/**
	 * 注解@PersistenceContext 注入的是实体管理器，执行持久化操作。
	 * unitName是用数据源创建实体管理器工厂时指定的persistenceUnit
	 * EntityManager 实体管理器
	 */
	@PersistenceContext(unitName="customerPersistenceUnit")
	private EntityManager entityManager;
	
	@Autowired
	private UserRepository userRepository;

	/**
	 * 多条件动态分页查询
	 */
	@Override
	public Page<User> getUserPage(final UserVO vo, int pageNum, int pageSize) {
		if(vo==null){
			return null;
		}
		Specification<User> spec = new Specification<User>() {
			@Override
	        public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
	            List<Predicate> list = new ArrayList<Predicate>();
	            if (vo.getUserName()!=null && !"".equals(vo.getUserName().trim())) {
	                list.add(cb.like(root.get("userName").as(String.class), "%" + vo.getUserName().trim() + "%"));
	            }
	            if (vo.getEmail()!=null && !"".equals(vo.getEmail().trim())) {
	                list.add(cb.equal(root.get("email").as(String.class), vo.getEmail().trim()));
	            }
	            if (vo.getLockStatus()!=null) {
	            	list.add(cb.equal(root.get("lockStatus").as(Integer.class), vo.getLockStatus()));
	            }
	            // and (level=1 or level=2 or level=3 and lock_status=0) and 
	            list.add(cb.or(
	            			cb.equal(root.get("level").as(Integer.class), 1),
	            			cb.equal(root.get("level").as(Integer.class), 2),
	            			cb.and(
	        					cb.equal(root.get("level").as(Integer.class), 3),
	        					cb.equal(root.get("lockStatus").as(Integer.class),0)
	        				)
	            		));
	            
	            //in(i,2,3)
	            List<Integer> orgIdList = vo.getOrgIdList();
	            if(orgIdList!=null && orgIdList.size()>0){
	            	Expression<Integer> exp = root.get("orgId").as(Integer.class);
	            	//方式1：exp.in(orgIdList)
	            	list.add(exp.in(orgIdList));
	            	//方式2：多次添加in值
	            	/*In<Integer> in = cb.in(exp);
	            	for (Integer orgId : orgIdList) {
						in.value(orgId);//in添加数据
					}
	            	list.add(in);*/
	            }
	            
	            /*if (model.getDepartment() != null && model.getDepartment().getCode() != null) {
	             	model中的department是关联表的实体类，用as指定类型
	                list.add(cb.equal(root.get("department").as(DepartmentModel.class), model.getDepartment()));
	        	}*/
	            
	            Predicate[] p = new Predicate[list.size()];
	            //方法一：where连接，不用返回值
	            //query.where(list.toArray(p));
	            //return null;
	            
	            //方法二：where连接，返回结果
	            //return query.where(list.toArray(p)).getRestriction(); 
	            
	            //and连接各条件
	            return cb.and(list.toArray(p));
	        }
	
	    };
	    Sort sort = new Sort(Direction.ASC, "userName");// 排序
		sort = sort.and(new Sort(Direction.DESC, "baseCreateTimeStr"));
		Pageable pageable = new PageRequest((pageNum-1)*pageSize, pageSize, sort);
		Page<User> findAll = userRepository.findAll(spec, pageable);
		//统计总数
		//long count = userRepository.count(spec);
		//查询所有数据
		//List<User> list = userRepository.findAll(spec, sort);
	    return findAll;
	}
	
	//原生SQL查询
	public long getCount(UserVO vo) {
		if(vo!=null){
			StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM user u WHERE 1=1 ");
			Map<String,Object> params = new HashMap<String,Object>();
			if (vo.getUserName()!=null && !"".equals(vo.getUserName().trim())) {
				sql.append(" and user_name like :userName");
				params.put("userName", "%"+vo.getUserName().trim()+"%");
	        }
	        if (vo.getLockStatus()!=null) {
	        	sql.append(" and lock_status=:lockStatus");
	        	params.put("lockStatus", vo.getLockStatus());
	        }
	        //原生SQL查询
			Query query = entityManager.createNativeQuery(sql.toString());
			Iterator<Entry<String, Object>> ite = params.entrySet().iterator();
			Entry<String, Object> entry = null;
			while(ite.hasNext()){
				entry = ite.next();
				query.setParameter(entry.getKey(), entry.getValue());
			}
			//count类型为java.math.BigInteger，否则抛出异常：
			//java.math.BigInteger cannot be cast to java.lang.Long
			List<?> list = query.getResultList();
			if (list.size() > 0 && list != null) {
				return ((BigInteger)list.get(0)).longValue();
			}
		}
		return 0;
	}
	
	//原生SQL查询
	public List<Map<String,Object>> getUserList(UserVO vo,int pageNum,int pageSize) {
		if(vo!=null && pageNum>0 && pageSize>0){
			StringBuilder sql = new StringBuilder("SELECT * FROM user u WHERE 1=1 ");
			Map<String,Object> paramsMap = new HashMap<String,Object>();
			if (vo.getUserName()!=null && !"".equals(vo.getUserName().trim())) {
				sql.append(" and user_name like :userName");
				paramsMap.put("userName", "%"+vo.getUserName().trim()+"%");
			}
			if (vo.getLockStatus()!=null) {
				sql.append(" and lock_status=:lockStatus");
				paramsMap.put("lockStatus", vo.getLockStatus());
			}
			List<Integer> orgIdList = vo.getOrgIdList();
            if(orgIdList!=null && orgIdList.size()>0){
            	sql.append(" and org_id = in(:orgIdList)");
				paramsMap.put("orgIdList", orgIdList);
            }
			
			//原生SQL查询
			//方法一：指定query实现类SQLQueryImpl，再设置返回结果为map
			Query query = entityManager.createNativeQuery(sql.toString());
			query.unwrap(org.hibernate.internal.SQLQueryImpl.class).setResultTransformer(
					org.hibernate.transform.Transformers.ALIAS_TO_ENTITY_MAP);
			
			//方法二：获取hibernate session，创建查询，设置返回结果类型
			/*org.hibernate.Session session = entityManager.unwrap(org.hibernate.Session.class);
			org.hibernate.Query query = session.createSQLQuery(sql.toString()).setResultTransformer(
							org.hibernate.transform.Transformers.ALIAS_TO_ENTITY_MAP);
			//参数map
			//query.setProperties(paramsMap);
			//List<Map<String,Object>> list = query.list()
			*/
			
			//分页
			query.setFirstResult((pageNum-1)*pageSize);
			query.setMaxResults(pageSize);
			//参数
			Iterator<Entry<String, Object>> ite = paramsMap.entrySet().iterator();
			Entry<String, Object> entry = null;
			while(ite.hasNext()){
				entry = ite.next();
				query.setParameter(entry.getKey(), entry.getValue());
			}
			
			@SuppressWarnings("unchecked")
			List<Map<String,Object>> list = (List<Map<String,Object>>)query.getResultList();
			if (list != null && list.size()>0) {
				return list;
			}
		}
		return null;
	}
	
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,readOnly=false)
	public void modifyUser(){
//		userRepository.save(new User("aaa", "aaa@163.com", 0, 1, 2));
//		userRepository.save(new User("bbb", "bbb@163.com", 0, 1, 2));
//		System.out.println(1/0);//制造异常
//		userRepository.save(new User("ccc", "ccc@163.com", 0, 1, 2));
		
		int res = userRepository.updateUser(0, 191L);
		System.out.println("========updateUser:"+res);
		res = userRepository.updateUser(0, 192L);
		System.out.println("========updateUser:"+res);
//		System.out.println(1/0);
		res = userRepository.updateUser(0, 193L);
		System.out.println("========updateUser:"+res);
	}
	
}
