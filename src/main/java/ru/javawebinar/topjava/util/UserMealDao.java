package ru.javawebinar.topjava.util;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by B on 05.12.2015.
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


}
