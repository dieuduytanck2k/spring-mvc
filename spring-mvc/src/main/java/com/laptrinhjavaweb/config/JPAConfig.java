package com.laptrinhjavaweb.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration // Bật tính năng java config trong spring
@EnableJpaRepositories(basePackages = {"com.laptrinhjavaweb.repository"})
@EnableTransactionManagement // Bật tính năng tự động quản lí transaction trong JPA
public class JPAConfig {
	
	// LocalContainerEntityManagerFactoryBean khởi tạo nhà máy 
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource()); // cần cung cấp drivername, url, username, password
		em.setPersistenceUnitName("persistence-data"); //là chất xúc tác dùng file persistence.xml để tạo kết nối giữa entity và database 
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(additionalProperties());
		return em;
	}
	
	@Bean
	JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return transactionManager;
	}
	
	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}
	
	//data source load các driver url username password
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver"); // Lưu ý trong file pom.xml cần có thư viện của mysql
		dataSource.setUrl("jdbc:mysql://localhost:3306/springmvcbasicfree");
		dataSource.setUsername("root");
		dataSource.setPassword("3814259Tan");
		return dataSource;
	}
	
	// Tự tạo table bằng cách buff các property từ java class
	// Trong quá trình làm việc sẽ phát sinh các properties khác, chúng ta sẽ add vào đây
	Properties additionalProperties() {
		Properties properties = new Properties();
		//properties.setProperty("hibernate.hbm2ddl.auto", "update");
		//properties.setProperty("hibernate.hbm2ddl.auto", "create"); 
		// create-drop: tắt server sẽ tự xóa database
		properties.setProperty("hibernate.hbm2ddl.auto", "none");  // khi đã có database ổn định thì dùng đến
		properties.setProperty("hibernate.enable_lazy_load_no_trans", "true"); // tính năng dùng lazy
		return properties;
	}
}
