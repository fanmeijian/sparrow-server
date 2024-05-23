//package cn.sparrowmini.server;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.domain.EntityScan;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.sql.DataSource;
//import java.util.Objects;
//
//@ComponentScan("cn.sparrowmini")
//@EntityScan({"cn.sparrowmini"})
//@Configuration
//@EnableJpaRepositories(
//        basePackages = {"cn.sparrowmini"},
//        entityManagerFactoryRef = "sparrowEntityManagerFactory"
//)
//public class SparrowDatasourceConfig {
//
//
//    @Bean
//    @ConfigurationProperties("sparrow.datasource")
//    public DataSourceProperties sparrowDataSourceProperties() {
//        return new DataSourceProperties();
//    }
//
//    @ConfigurationProperties("sparrow.datasource.hikari")
//    @Bean
//    public DataSource sparrowDataSource() {
//        return sparrowDataSourceProperties()
//                .initializeDataSourceBuilder()
//                .build();
//    }
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean sparrowEntityManagerFactory(
//            @Qualifier("sparrowDataSource") DataSource dataSource,
//            EntityManagerFactoryBuilder builder) {
//        return builder
//                .dataSource(dataSource)
//                .packages("cn.sparrowmini")
//                .build();
//    }
////
////    @Bean
////    public PlatformTransactionManager sparrowTransactionManager(
////            @Qualifier("sparrowEntityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactory) {
////        return new JpaTransactionManager(Objects.requireNonNull(entityManagerFactory.getObject()));
////    }
//
//
//}
