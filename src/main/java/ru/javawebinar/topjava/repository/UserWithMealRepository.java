package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;

/**
 * Created by B on 04.01.2016.
 */
public interface UserWithMealRepository {
    UserMeal getMeaLWithUser(int id, int userId);

    User getUserWithMeals(int userId);

}
