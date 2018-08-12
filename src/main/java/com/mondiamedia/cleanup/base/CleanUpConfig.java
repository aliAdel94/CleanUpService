package com.mondiamedia.cleanup.base;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
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
 * @author Ali.Ismail
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {
        "com.mondiamedia.cleanup.base" }, entityManagerFactoryRef = "cleanUpEntityManager", transactionManagerRef = "cleanUpTransactionManager")
public class CleanUpConfig {

    @Primary
    @Bean(name = "cleanUpEntityManager")
    public LocalContainerEntityManagerFactoryBean cleanUpEntityManager(final EntityManagerFactoryBuilder builder,
            @Qualifier("cleanUpDataSource") final DataSource dataSource) {
        return builder.dataSource(dataSource).packages("com.mondiamedia.cleanup.base").persistenceUnit("cleanup").build();
    }

    @Primary
    @Bean(name = "cleanUpDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource cleanUpDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "cleanUpTransactionManager")
    public PlatformTransactionManager cleanUpTransactionManager(@Qualifier("cleanUpEntityManager") final EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}
