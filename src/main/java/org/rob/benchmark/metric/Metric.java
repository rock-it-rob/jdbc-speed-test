package org.rob.benchmark.metric;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Metric measures how long something takes.
 */
public abstract class Metric
{
    private static final Logger log = LoggerFactory.getLogger(Metric.class);

    /**
     * Call to do the action.
     */
    public static void go(String name, Runnable runnable)
    {
        LocalDateTime begin = LocalDateTime.now();
        log.debug("Beginning: " + name);
        runnable.run();
        LocalDateTime end = LocalDateTime.now();
        log.debug("Ending: " + name);
        final String duration = String.format("Duration of %s: %s", name, formatDuration(Duration.between(begin, end)));
        log.info(String.format("Duration of %s: %s", name, formatDuration(Duration.between(begin, end))));
    }

    private static String formatDuration(Duration d)
    {
        return d.toString();
    }
}
