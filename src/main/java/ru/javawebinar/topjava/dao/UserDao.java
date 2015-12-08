package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by B on 08.12.2015.
 */
public interface UserDao {
    UserMealDao instance = new UserMealDao();
    Map<Long, UserMeal> mapUserMeal = new HashMap<>();

    UserMeal create(Map<String, String> mapMeal);

    void delete(Map<Long, UserMeal> mapMeal, long id);

    Map<Long, UserMeal> findByDate(Map<Long, UserMeal> mapMeal, LocalDate date);

    UserMeal update(long id, String newDate, String newDescription, String newCalories);

    UserMeal findById(Map<Long, UserMeal> mapMeal, long id);


}
