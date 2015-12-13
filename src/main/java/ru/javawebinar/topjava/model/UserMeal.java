package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;

/**
 * GKislin
 * 11.01.2015.
 */
public class UserMeal extends BaseEntity {

    protected final LocalDateTime dateTime;

    protected final String description;

    protected final int calories;

    protected User user;

    public UserMeal(LocalDateTime dateTime, String description, int calories) {
        this(null, dateTime, description, calories);
    }

    public UserMeal(Integer id, LocalDateTime dateTime, String description, int calories) {
        super.id = id;
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public UserMeal(User user, Integer id, String description, LocalDateTime dateTime, int calories) {
        this.user = user;
        super.id = id;
        this.description = description;
        this.dateTime = dateTime;
        this.calories = calories;
    }

    public UserMeal(LocalDateTime dateTime, String description, int calories, User user) {
        this.calories = calories;
        this.dateTime = dateTime;
        this.description = description;
        this.user = user;
    }

    public void setId(Integer id) {
        super.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setId(int id) {
        super.id = id;
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

    public Integer getId() {
        return id;
    }

    public boolean isNew() {
        return id == null;
    }

    @Override
    public String toString() {
        return "UserMeal{" +
                "id= " + id +
                //      " user id = " + user.getName() +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                '}';
    }
}
