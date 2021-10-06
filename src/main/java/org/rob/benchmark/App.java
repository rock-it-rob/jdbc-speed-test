package org.rob.benchmark;

import org.rob.benchmark.db.Dao;
import org.rob.benchmark.db.DbConfiguration;
import org.rob.benchmark.metric.Metric;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;

@SpringBootApplication
public class App implements CommandLineRunner
{
    private static final Logger log = LoggerFactory.getLogger(App.class);

    @Autowired
    private DbConfiguration dbConfiguration;

    public static void main(String[] args)
    {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) throws Exception
    {
        Metric.go("Benchmark Application", this::runBenchmarks);
    }

    private void runBenchmarks()
    {
        Metric.go("Connecting to DB", dbConfiguration::connect);
        final DataSource dataSource = dbConfiguration.getDataSource();
        final Dao dao = new Dao(dataSource);

        // Get a count of every record in the table.
        Metric.go("Querying table", () -> dao.totalRecords(dbConfiguration.getTable()));

        // Iterate over each record in the table.
        Metric.go("Reading each record", () -> dao.readEachRecord(dbConfiguration.getTable()));
    }
}
