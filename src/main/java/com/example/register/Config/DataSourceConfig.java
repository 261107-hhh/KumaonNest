package com.example.register.Config;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
public class DataSourceConfig {
	
//	 @Bean
//	    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//	        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//	        em.setDataSource(dataSource());
//
//	        em.setPackagesToScan(new String[] { "com.example.register.Entity" });
//	        em.setPersistenceUnitName("org.hibernate.jpa.HibernatePersistenceProvider");
//
//	        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//	        vendorAdapter.setGenerateDdl(false);
//	        em.setJpaVendorAdapter(vendorAdapter);
//
//	        return em;
//	    }

    @Bean
    public DataSource dataSource() {

        return DataSourceBuilder.create()

          .driverClassName("com.mysql.cj.jdbc.Driver")

          .url("jdbc:mysql://localhost:3306/registertemp?createDatabaseIfNotExist=true")

          .username("root")

          .password("root")

          .build();	

    }

}