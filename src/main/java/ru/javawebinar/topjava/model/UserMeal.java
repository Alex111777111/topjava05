package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;

/**
 * GKislin
 * 11.01.2015.
 */
public class UserMeal {
    protected LocalDateTime dateTime;

    protected String description;

    protected int calories;

    protected long id;

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

    public UserMeal(int calories, LocalDateTime dateTime, String description) {
        this.calories = calories;
        this.dateTime = dateTime;
        this.description = description;
    }

    public UserMeal(int calories, LocalDateTime dateTime, String description, long id) {
        this.calories = calories;
        this.dateTime = dateTime;
        this.description = description;
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
