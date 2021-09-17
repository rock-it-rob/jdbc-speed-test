package org.rob.benchmark.metric;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Metric measures how long something takes.
 */
public class Metric
{
    private static final Logger log = LoggerFactory.getLogger(Metric.class);

    private final String name;
    private LocalDateTime begin;
    private LocalDateTime end;
    private final Runnable runnable;

    public Metric(String name, Runnable runnable)
    {
        this.name = name;
        this.runnable = runnable;
    }

    private void start()
    {
        begin = LocalDateTime.now();
        log.debug("Beginning: " + name);
    }

    private void end()
    {
        end = LocalDateTime.now();
        log.debug("Ending: " + name);
    }

    public String getDuration()
    {
        return String.format("Duration of %s: %s", name, formatDuration(Duration.between(begin, end)));
    }

    /**
     * Call to do the action.
     */
    public void go()
    {
        start();
        runnable.run();
        end();
        log.info(getDuration());
    }

    private String formatDuration(Duration d)
    {
        return d.toString();
    }
}
