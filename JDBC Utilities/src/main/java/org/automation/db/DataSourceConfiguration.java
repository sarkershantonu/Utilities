package org.automation.db;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;


@Configuration
public class DataSourceConfiguration {

    private @Value("${db.url}") String url;
    private @Value("${db.driver.class.name}") String driver;
    private @Value("${db.user.name}") String user;
    private @Value("${db.user.password}") String pass;

    @Bean(name = "dataSource")
    public DataSource dataSource() throws PropertyVetoException {
        return prepare();
    }

    private DataSource prepare() throws PropertyVetoException {
        ComboPooledDataSource pool = new ComboPooledDataSource ();
        pool.setDriverClass(driver);
        pool.setJdbcUrl(url);
        pool.setUser(user);
        pool.setPassword(pass);
        return pool;
    }
}
