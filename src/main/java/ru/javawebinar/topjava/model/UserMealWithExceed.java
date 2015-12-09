package ru.javawebinar.topjava.model;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * GKislin
 * 11.01.2015.
 */

public class UserMealWithExceed {
    protected final LocalDateTime dateTime;

    protected final String description;

    protected final int calories;

    protected final boolean exceed;

    protected long id;

    public UserMealWithExceed(LocalDateTime dateTime, String description, int calories, boolean exceed) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.exceed = exceed;
    }


    @Override
    public String toString() {
        return "UserMealWithExceed{" +
                "dateTime=" + dateTime +
                ", description=" + description + '\'' +
                ", calories=" + calories +
                ", exceed=" + exceed +
                '}';
    }

    public int getCalories() {
        return calories;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public boolean getExceed() {
        return exceed;
    }

    public UserMealWithExceed(long id, int calories, LocalDateTime dateTime, String description, boolean exceed) {
        this.calories = calories;
        this.dateTime = dateTime;
        this.description = description;
        this.exceed = exceed;
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isExceed() {
        return exceed;
    }

}
