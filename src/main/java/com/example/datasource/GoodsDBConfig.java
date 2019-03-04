package com.example.datasource;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 数据源配置
 * @author xcl
 *
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		entityManagerFactoryRef="entityManagerFactoryGoods",
		transactionManagerRef="transactionManagerGoods",
		basePackages = {"com.example.goods.dao"}) //次数据源repository所在位置
public class GoodsDBConfig {

	@Autowired
	@Qualifier("goodsDataSource")
	private DataSource goodsDataSource;
	
	@Bean(name="entityManagerGoods")
	public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
		return entityManagerFactoryGoods(builder).getObject().createEntityManager();
	}
	
	@Bean(name="entityManagerFactoryGoods")
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryGoods(EntityManagerFactoryBuilder builder) {
		return builder.dataSource(goodsDataSource)
				.properties(getVendorProperties(goodsDataSource))
				.packages("com.example.goods.model") //次数据源实体所在位置
				.persistenceUnit("goodsPersistenceUnit")
				.build();
	}
	
	@Autowired
	private JpaProperties jpaProperties;
	
	private Map<String, String> getVendorProperties(DataSource dataSource) {
		return jpaProperties.getHibernateProperties(dataSource);
	}
	
	@Bean(name="transactionManagerGoods")
	PlatformTransactionManager transactionManagerGoods(EntityManagerFactoryBuilder builder) {
		return new JpaTransactionManager(entityManagerFactoryGoods(builder).getObject());
	}
}