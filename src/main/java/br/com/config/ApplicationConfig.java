package br.com.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * Created by renato on 25/05/15.
 */

@Configuration
@ComponentScan(basePackages = "br.com")
@PropertySource("classpath:jdbc.properties")
public class ApplicationConfig {

    @Autowired
    Environment env;

    @Bean
    public DataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(env.getProperty("jdbc.driverClassName")); // org.sqlite.JDBC
        dataSource.setJdbcUrl(env.getProperty("jdbc.url"));//jdbc:sqlite:test.db
        dataSource.setUser(env.getProperty("jdbc.username"));
        dataSource.setPassword(env.getProperty("jdbc.password"));
        dataSource.setAcquireIncrement(Integer.valueOf(env.getProperty("jdbc.acquireIncrement")));
        dataSource.setMaxPoolSize(Integer.valueOf(env.getProperty("jdbc.maxPoolSize")));
        dataSource.setMinPoolSize(Integer.valueOf(env.getProperty("jdbc.minPoolSize")));
        dataSource.setInitialPoolSize(Integer.valueOf(env.getProperty("jdbc.initialPoolSize")));
        dataSource.setCheckoutTimeout(Integer.valueOf(env.getProperty("jdbc.timeout")));
        dataSource.setPreferredTestQuery(env.getProperty("jdbc.preferredTestQuery"));
        dataSource.setIdleConnectionTestPeriod(Integer.valueOf(env.getProperty("jdbc.idleConnectionTestPeriod")));
        return dataSource;
    }

}
