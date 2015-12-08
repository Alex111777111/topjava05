package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.UserMeal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by B on 08.12.2015.
 */
public interface UserDao {
    UserMealDao instance = new UserMealDao();
    Map<Long, UserMeal> mapUserMeal = new HashMap<>();

    void create(UserMeal um);

    void delete(Map<Long, UserMeal> mapMeal, long id);

    Map<Long, UserMeal> findByDate(Map<Long, UserMeal> mapMeal, LocalDate date);

    void update(UserMeal um);

    UserMeal findById(Map<Long, UserMeal> mapMeal, long id);


}
