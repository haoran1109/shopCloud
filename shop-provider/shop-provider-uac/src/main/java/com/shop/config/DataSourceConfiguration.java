
package com.shop.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
@MapperScan("com.shop.dao.*")
@ImportResource("classpath:spring/spring-tx.xml")
public class DataSourceConfiguration {
  private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceConfiguration.class);
  	
  @Bean
  public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
	  SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
	  sqlSessionFactoryBean.setDataSource(dataSource());
	  PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
	  sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mybatis/mapper/*/*.xml"));
	  LOGGER.info("数据库初始化完成");
	  return sqlSessionFactoryBean.getObject();
  }
  
  @Bean
  @ConfigurationProperties(prefix = "spring.datasource")
  public DataSource dataSource() {
    return new DruidDataSource();
  }
  
  
  @Bean(name="transactionManager")
  public DataSourceTransactionManager transactionManager() {
	  LOGGER.info("切入事务完成");
      return new DataSourceTransactionManager(dataSource());
  }
  
  
}

