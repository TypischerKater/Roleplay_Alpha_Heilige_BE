//package de.roleplay.backend.config;
//
//import liquibase.integration.spring.SpringLiquibase;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.sql.DataSource;
//import javax.xml.crypto.Data;
//
//@Configuration
//public class Liquibaseconfig {
//
//    @Autowired
//    private DataSource dataSource;
//
//    @Bean
//    public SpringLiquibase liquibase() {
//        SpringLiquibase liquibase = new SpringLiquibase();
//        liquibase.setDataSource(this.dataSource);
//        liquibase.setChangeLog("classpath:db/changelog/db.changelogmaster.xml");
//        return liquibase;
//    }
//}
//
//
