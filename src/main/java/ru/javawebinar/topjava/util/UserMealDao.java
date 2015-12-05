package ru.javawebinar.topjava.util;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Maria on 05.12.2015.
 */
@Repository
public class UserMealDao {


    public UserMealWithExceed create(Map<String, String> mapMeal) {
        UserMeal um = new UserMeal
                (LocalDateTime.parse(mapMeal.get("date")), mapMeal.get("description"), Integer.parseInt(mapMeal.get("calories")));
        List<UserMeal> userMeals = new ArrayList<>();
        userMeals.add(um);
        List<UserMealWithExceed> umEList = UserMealsUtil.getExceed(userMeals, 2000);
        return umEList.get(0);
    }

    public void delete(Map<String, String> mapMeal, LocalDateTime dateTime) {
        if (LocalDateTime.parse(mapMeal.get("date")).equals(dateTime)) {
            mapMeal.clear();
        }
    }

    public Map<String, String> findByDate(Map<String, String> mapMeal, LocalDate date) {
        if (LocalDateTime.parse(mapMeal.get("date")).toLocalDate().equals(date)) {
            return mapMeal;
        } else return null;
    }

    public Map<String, String> update(Map<String, String> mapMeal, String newDate, String newDescription, String newCalories) {
        mapMeal.put("date", newDate);
        mapMeal.put("description", newDescription);
        mapMeal.put("calories", newCalories);
        return mapMeal;
    }
}
