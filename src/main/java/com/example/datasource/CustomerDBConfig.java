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
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 数据源配置
 * @author xcl
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		entityManagerFactoryRef="entityManagerFactoryCustomer", 
		transactionManagerRef="transactionManagerCustomer", 
		//basePackages。Repository所在的位置，主数据源要和次数据源所在的包要分开
		basePackages = {"com.example.cutomer.repository"} ) 
public class CustomerDBConfig {

	@Autowired
	@Qualifier("customerDataSource")
	private DataSource customerDataSource;
	
	@Autowired
    private JpaProperties jpaProperties;
	
	@Primary //该注解表示为主数据源
    @Bean(name = "entityManagerCustomer")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactoryCustomer(builder).getObject().createEntityManager();
    }
	
	@Primary
    @Bean(name = "entityManagerFactoryCustomer")
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryCustomer(EntityManagerFactoryBuilder builder) {
		return builder.dataSource(customerDataSource)
				.properties(getVendorProperties(customerDataSource))
				.packages("com.example.cutomer.model") //数据源实体所在位置
				.persistenceUnit("customerPersistenceUnit")
				.build();
	}
	
	private Map<String, String> getVendorProperties(DataSource dataSource) {
        return jpaProperties.getHibernateProperties(dataSource);
    }
	
	@Primary
    @Bean(name = "transactionManagerCustomer")
    public PlatformTransactionManager transactionManagerCustomer(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactoryCustomer(builder).getObject());
    }
}
