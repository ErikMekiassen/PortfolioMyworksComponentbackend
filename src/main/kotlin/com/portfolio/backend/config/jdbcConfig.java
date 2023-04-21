package com.portfolio.backend.config;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.kotlin.KotlinPlugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@PropertySource(value = {"file:src/main/resources/application.properties"}, ignoreResourceNotFound = true)
public class jdbcConfig {

    @Bean(name = "ProdukterDatasource")
    public HikariDataSource dataSource(
            @Value("${spring.datasource.driver-class-name}") String driverName,
            @Value("${spring.datasource.url}") String url,
            @Value("${spring.datasource.username}") String username,
            @Value("${spring.datasource.password}") String password,
            @Value("{spring.application.name}") String applicationName
    ) {
        HikariDataSource ds = new HikariDataSource();

        ds.setDriverClassName(driverName);
        if (driverName.equals("org.h2.Driver")) {
            // For kjøring mot in-memory database lokalt
            ds.setJdbcUrl(url);
        } else {
            ds.setJdbcUrl(url + ";ApplicationName=" + applicationName);
        }
        ds.setUsername(username);
        ds.setPassword(password);


        ds.setIdleTimeout(10000); // 10 sekunder
        ds.setLeakDetectionThreshold(60*1000);

        return ds;
    }

    @Bean(name = "PersonhenvendelseJdbi")
    public Jdbi dbi(@Qualifier("ProdukterDatasource") HikariDataSource dataSource) throws Exception {
        Jdbi jdbi = Jdbi.create(dataSource);
        jdbi.installPlugin(new SqlObjectPlugin());
        jdbi.installPlugin(new KotlinPlugin());
        jdbi.registerArgument(new LocalDateArgumentFactory());
        return jdbi;
    }

}
