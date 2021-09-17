package org.rob.benchmark.db;

import org.rob.benchmark.metric.Metric;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.concurrent.atomic.AtomicLong;

public class Dao
{
    private static final Logger log = LoggerFactory.getLogger(Dao.class);

    public Dao(DataSource dataSource)
    {
        new Metric("Creating JdbcTemplate", () ->
                jdbcTemplate = new JdbcTemplate(dataSource)
        ).go();
    }

    private JdbcTemplate jdbcTemplate;

    public void totalRecords(String table)
    {
        final String sql = String.format("select count(*) from %s", table);
        final long total = jdbcTemplate.queryForObject(sql, Long.class);

        log.info(String.format("Total in %s = %d", table, total));
    }

    /**
     * Performs a select over every record in the table, essentially doing a no-op on each row.
     */
    public void readEachRecord(String table)
    {
        final String sql = String.format("select * from %s", table);
        AtomicLong row = new AtomicLong();

        jdbcTemplate.query(sql, (rs) -> {
            row.set(row.getAndIncrement());
            if (row.get() % 10000 == 0)
                log.debug("Read row: " + row.get());
        });
    }
}
