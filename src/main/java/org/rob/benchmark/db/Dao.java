package org.rob.benchmark.db;

import org.rob.benchmark.metric.Metric;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class Dao
{
    public Dao(DataSource dataSource)
    {
        new Metric("Creating JdbcTemplate", () ->
                jdbcTemplate = new JdbcTemplate(dataSource)
        ).go();
    }

    private JdbcTemplate jdbcTemplate;

    public long totalRecords(String table)
    {
        final String sql = String.format("select count(*) from %s", table);
        return jdbcTemplate.queryForObject(sql, Long.class);
    }
}
