package ru.javawebinar.topjava.service;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import java.time.LocalTime;

/**
 * Created by Maria on 28.12.2015.
 */
public class TimeRule implements TestRule {
    private float start;
    private static String log;

    public String testApply() {
        apply(null, null);
        return log;
    }

    public TimeRule() {
    }

    public TimeRule(float start) {
        this.start = start;
    }

    public float getStart() {
        return start;
    }

    public void setStart(float start) {
        this.start = start;
    }

    @Override
    public Statement apply(final Statement base, final Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
            /*    if (start == 0) {
                    start = LocalTime.now().getNano();
                    log = "rule start";*/
               /* } else {*/
                log = "lead time " + ((LocalTime.now().getNano() - start) / 1000000) + " ms";
                try {
                    base.evaluate();
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // }
            }
        };
    }
}