package ru.javawebinar.topjava.model;

import org.springframework.stereotype.Component;
import ru.javawebinar.topjava.util.TimeUtil;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * GKislin
 * 11.01.2015.
 */

public class UserMeal {
    protected Integer id;

    protected LocalDateTime dateTime;

    protected String description;

    protected int calories;

    public UserMeal(LocalDateTime dateTime, String description, int calories) {
        this(null, dateTime, description, calories);
    }

    public UserMeal() {
    }

    public UserMeal(Integer id, LocalDateTime dateTime, String description, int calories) {
        this.id = id;
        this.dateTime = dateTime;
        this.calories = calories;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDateTimeLocal() {
        return dateTime;
    }

    public Timestamp getDateTime() {
        return TimeUtil.getTimestamp(dateTime);
    }


    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public boolean isNew() {
        return id == null;
    }

    @Override
    public String toString() {
        return "UserMeal{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                '}';
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime.toLocalDateTime();
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserMeal(Integer id, Timestamp dateTime, String description, int calories) {
        this.id = id;
        this.dateTime = dateTime.toLocalDateTime();
        this.calories = calories;
        this.description = description;
    }

}
