package com.getinline.getinline.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionSynchronization;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@EnableJpaAuditing
@Configuration
public class JpaConfig {

    // 직접 DataSource 를 설정하고 싶을때.
    //spring.datasource.url=jdbc:h2:mem:testdb properties 에 있는 이 내용이 주입된다.
//    @Bean
//    @ConfigurationProperties("spring.datasource")
//    public DataSource dataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean
//    @ConfigurationProperties("spring.datasource")
//    public DataSource dataSource2() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
//
//        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        vendorAdapter.setGenerateDdl(true);
//
//        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
//        factory.setJpaVendorAdapter(vendorAdapter);
//        factory.setPackagesToScan("com.getinline.getinline");
//        factory.setDataSource(dataSource);
//        return factory;
//    }
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory2(DataSource dataSource2) {
//
//        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        vendorAdapter.setGenerateDdl(true);
//
//        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
//        factory.setJpaVendorAdapter(vendorAdapter);
//        factory.setPackagesToScan("com.getinline.getinline");
//        factory.setDataSource(dataSource2);
//        return factory;
//    }
//
//    @Bean
//    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
//        JpaTransactionManager txManager = new JpaTransactionManager();
//        txManager.setEntityManagerFactory(entityManagerFactory);
//        return txManager;
//    }
//
//    @Bean
//    public PlatformTransactionManager transactionManager2(EntityManagerFactory entityManagerFactory2) {
//        JpaTransactionManager txManager = new JpaTransactionManager();
//        txManager.setEntityManagerFactory(entityManagerFactory2);
//        return txManager;
//    }

    // db 가 2개일때의 문제
    /*
        db 를 2개를 사용한다면 1번 db 에서 문제가 생기면 2번째 db 는 롤백이 자동으로 된다.
        그러나 1번쨰것이 커밋이 되고 2번째가 문제가 생긴다면? 원자성이 지켜지지않는다.
        그래서 직접 관리할 수 있도록 설정을 따로 하게되었다.
     */


    // Transaction 관리를 해야하는데 만약 2개의 데이터베이스를 동시에 사용하고있다면 어떻게 해야할까?
    // 기존에 사용하던 방법은 사라짐. TransactionSynchronization 가 최신버전이고 이걸 사용할 준비를 해야한다.
//    @Bean
//    public TransactionSynchronization transactionSynchronization(
//
//    ){
//
//    }
}
