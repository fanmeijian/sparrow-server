//package cn.sparrowmini.server;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.transaction.PlatformTransactionManager;
//
//import javax.sql.DataSource;
//import java.util.Objects;
//
//@Configuration
//public class JbpmDatasourceConfig {
//    @Bean
//    @ConfigurationProperties("spring.datasource.jbpm")
//    public DataSourceProperties jbpmDataSourceProperties() {
//        return new DataSourceProperties();
//    }
//
//
//    @Bean
//    public DataSource jbpmDataSource() {
//        return jbpmDataSourceProperties()
//                .initializeDataSourceBuilder()
//                .build();
//    }
//
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean jbpmEntityManagerFactory(
//            @Qualifier("jbpmDataSource") DataSource dataSource,
//            EntityManagerFactoryBuilder builder) {
//        return builder
//                .dataSource(dataSource)
//                .packages("org.jbpm")
//                .build();
//    }
//
//    @Bean
//    public PlatformTransactionManager jbpmTransactionManager(
//            @Qualifier("jbpmEntityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactory) {
//        return new JpaTransactionManager(Objects.requireNonNull(entityManagerFactory.getObject()));
//    }
//
//
//}
