package ru.javawebinar.topjava.service;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import java.time.Duration;
import java.time.LocalTime;

/**
 * Created by Maria on 28.12.2015.
 */
public class TimeRule implements TestRule {
    private static long time;
    private static String log;

    public String testApply() {
        return log;
    }


    @Override
    public Statement apply(final Statement base, final Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {

                //  log = "lead time " + ((LocalTime.now().getNano() - start) / 1000000000) + " s";
                log = description.getClassName() + " lead time ";
                try {
                    base.evaluate();
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }
}