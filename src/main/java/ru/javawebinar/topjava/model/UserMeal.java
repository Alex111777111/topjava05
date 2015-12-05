package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;

/**
 * GKislin
 * 11.01.2015.
 */
public class UserMeal {
    protected LocalDateTime dateTime;

    protected String description;

    protected  int calories;

    public UserMeal(LocalDateTime dateTime) {
        this.dateTime = dateTime;
       this.description = description;
        this.calories = calories;
    }

    public UserMeal(LocalDateTime date, String description, int calories) {
        this.calories = calories;
        this.dateTime = date;
        this.description = description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public UserMeal(int calories, LocalDateTime dateTime, String description) {
        this.calories = calories;
        this.dateTime = dateTime;
        this.description = description;
    }
}
