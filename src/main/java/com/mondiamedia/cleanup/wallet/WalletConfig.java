package com.mondiamedia.cleanup.wallet;

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
 *
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {
        "com.mondiamedia.cleanup.wallet" }, entityManagerFactoryRef = "walletEntityManager", transactionManagerRef = "walletTransactionManager")
public class WalletConfig {

    @Bean(name = "walletEntityManager")
    public LocalContainerEntityManagerFactoryBean walletEntityManager(final EntityManagerFactoryBuilder builder,
            @Qualifier("walletDataSource") final DataSource dataSource) {
        return builder.dataSource(dataSource).packages("com.mondiamedia.cleanup.wallet").persistenceUnit("wallet").build();
    }

//    @Bean(name = "walletDataSource")
//    public DataSource walletDataSource() {
//
//        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//        dataSource.setUrl("jdbc:mysql://s1.mysql-db20-gt.stg.arvm.de:3306/wallet");
//        dataSource.setUsername("usALIS1XX402c5aR");
//        dataSource.setPassword("OMiB6hupuagSj7oYPu7y");
//
//        return dataSource;
//    }

    @Bean(name = "walletDataSource")
    @ConfigurationProperties(prefix="spring.thirdDatasource")
    public DataSource walletDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "walletTransactionManager")
    public PlatformTransactionManager walletTransactionManager(@Qualifier("walletEntityManager") final EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}