package ru.javawebinar.topjava.util;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
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

    public void delete(Map<Integer, UserMealWithExceed> mapMeal, int id) {
       /* Iterator<Integer, UserMealWithExceed> iterator = mapMeal.entrySet().iterator();
        while (iterator.hasNext()) {
 //Map.Entry<Integer, UserMealWithExceed> entry = (Map.Entry<Integer, UserMealWithExceed>) iterator.hasNext();
            if (entry.getKey().equals(id)) {

        }*/
        mapMeal
                .forEach((k, v) -> {
                    if (k == id) mapMeal.remove(k);

                });
    }

    public Map<Integer, UserMealWithExceed> findByDate(Map<Integer, UserMealWithExceed> mapMeal, LocalDate date) {
        Map<Integer, UserMealWithExceed> mapByDate = new HashMap<>();
        mapMeal
                .forEach((k, v) -> {
                    if (v.getDateTime().toLocalDate().equals(date))
                        mapByDate.put(k, v);
                });
        return mapByDate;
    }

    public UserMealWithExceed update(Map<Integer, UserMealWithExceed> mapMeal, int id, String newDate, String newDescription, String newCalories) {
       /* mapMeal.put("date", newDate);
        mapMeal.put("description", newDescription);
        mapMeal.put("calories", newCalories);
        return mapMeal;*/
        List<UserMeal> listMeal = new ArrayList<>();
        UserMealWithExceed ume = null;
        mapMeal
                .forEach((k, v) -> {
                    if (k == id) {
                        listMeal.add(
                                new UserMeal(Integer.parseInt(newCalories), LocalDateTime.parse(newDate), newDescription, id));
                    }
                });

        if (listMeal.size() > 0) {
            ume = UserMealsUtil.getExceed(listMeal, 2000).get(0);

        }
        return ume;
    }
}
