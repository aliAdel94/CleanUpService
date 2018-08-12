package com.mondiamedia.cleanup.subscription;

import java.util.HashMap;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Ali.Ismail
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {
        "com.mondiamedia.cleanup.subscription" }, transactionManagerRef = "subscriptionTransactionManager", entityManagerFactoryRef = "subscriptionEntityManager")
public class SubscriptionConfig {

//    @Bean(name = "subscriptionDataSource")
//    public DataSource subscriptionDataSource() {
//
//        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//        dataSource.setUrl("jdbc:mysql://s1.mysql-db23-gt.stg.arvm.de:3306/abo");
//        dataSource.setUsername("usALIS1XX1739f7R");
//        dataSource.setPassword("PF9lKfV3VGsfjOwpCptN");
//
//        return dataSource;
//    }

    @Bean(name = "subscriptionDataSource")
    @ConfigurationProperties(prefix="spring.secondDatasource")
    public DataSource subscriptionDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "subscriptionEntityManager")
    public LocalContainerEntityManagerFactoryBean subscriptionEntityEntityManager(final EntityManagerFactoryBuilder builder,
            @Qualifier("subscriptionDataSource") final DataSource dataSource) {
        final HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.default_schema", null);
        return builder.dataSource(dataSource).packages("com.mondiamedia.cleanup.subscription").persistenceUnit("subscription").properties(properties).build();
    }

    @Bean(name = "subscriptionTransactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("subscriptionEntityManager") final EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}
