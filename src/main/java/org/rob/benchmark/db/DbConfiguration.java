package org.rob.benchmark.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ConfigurationProperties(prefix = "jdbc")
@PropertySource("file:db.properties")
public class DbConfiguration
{
    private static final Logger log = LoggerFactory.getLogger(DbConfiguration.class);

    private String url;
    private String user;
    private String password;
    private String table;

    public DataSource connect()
    {
        log.info(String.format("Connecting on: %s with: %s", url, user));

        return new DriverManagerDataSource(url, user, password);
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getUser()
    {
        return user;
    }

    public void setUser(String user)
    {
        this.user = user;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getTable()
    {
        return table;
    }

    public void setTable(String table)
    {
        this.table = table;
    }
}
