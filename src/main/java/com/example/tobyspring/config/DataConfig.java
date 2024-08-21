package com.example.tobyspring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class DataConfig {
    // datasource
    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                    .setType(EmbeddedDatabaseType.H2)
                    .build();
    }

    // entity manager factory(잘안씀)
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
//        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
//        emf.setDataSource(dataSource());
//        emf.setPackagesToScan("com.example.tobyspring");    // 자동으로 mapping 되도록
//        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter() {{  // hibernate한테 필요한 설정 세팅
//            setDatabase(Database.H2);
//            setGenerateDdl(true);
//            setShowSql(true);
//        }});
//        return emf;
//    }

//    @Bean
//    public BeanPostProcessor persistenceAnnotationBeanpostProcessor() {
//        return new PersistenceAnnotationBeanPostProcessor();
//    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }
}

