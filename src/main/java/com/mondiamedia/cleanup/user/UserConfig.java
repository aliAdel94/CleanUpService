package com.mondiamedia.cleanup.user;
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
"com.mondiamedia.cleanup.user" }, entityManagerFactoryRef = "userEntityManager", transactionManagerRef = "userTransactionManager")
public class UserConfig {

    @Bean(name = "userEntityManager")
    public LocalContainerEntityManagerFactoryBean userEntityManager(final EntityManagerFactoryBuilder builder,
            @Qualifier("userDataSource") final DataSource dataSource) {
        return builder.dataSource(dataSource).packages("com.mondiamedia.cleanup.user").persistenceUnit("usrUser").build();
    }

    @Bean(name = "userDataSource")
    @ConfigurationProperties(prefix="spring.firstDatasource")
    public DataSource userDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "userTransactionManager")
    public PlatformTransactionManager userTransactionManager(@Qualifier("userEntityManager") final EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}
