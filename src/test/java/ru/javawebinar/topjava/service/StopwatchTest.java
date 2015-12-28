package ru.javawebinar.topjava.service;

import org.junit.AssumptionViolatedException;
import org.junit.Rule;
import org.junit.rules.Stopwatch;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import ru.javawebinar.topjava.LoggerWrapper;

import java.util.concurrent.TimeUnit;

public class StopwatchTest extends Stopwatch {
    protected static final LoggerWrapper logger = LoggerWrapper.get(StopwatchTest.class);

    private static void logInfo(Description description, String status, long nanos) {
        String testName = description.getMethodName();
        logger.info(String.format("Test %s %s, spent %d microseconds",
                testName, status, TimeUnit.NANOSECONDS.toMicros(nanos)));
    }


    @Override
    protected void succeeded(long nanos, Description description) {
        logInfo(description, "succeeded", nanos);
    }

    @Override
    protected void failed(long nanos, Throwable e, Description description) {
        logInfo(description, "failed", nanos);
    }

    @Override
    protected void skipped(long nanos, AssumptionViolatedException e, Description description) {
        logInfo(description, "skipped", nanos);
    }

    @Override
    protected void finished(long nanos, Description description) {
        logInfo(description, "finished", nanos);
    }
}



