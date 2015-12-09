package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.UserMeal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Created by B on 08.12.2015.
 */
public interface UserMealDao {

    List<UserMeal> getAll();

    void create(UserMeal um);

    void delete(long id);

    void update(UserMeal um);

    UserMeal findById(long id);


}
