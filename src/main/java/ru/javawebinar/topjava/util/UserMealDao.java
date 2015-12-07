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
    private static UserMealDao instance;
    UserMealWithExceed ume;

    public static synchronized UserMealDao getInstance() {
        if (instance == null) {
            instance = new UserMealDao();
        }
        return instance;
    }

    public UserMealWithExceed getUme() {
        return ume;
    }

    public void setUme(UserMealWithExceed ume) {
        this.ume = ume;
    }

    public UserMealDao() {

    }

    public UserMealWithExceed create(Map<String, String> mapMeal) {
        UserMeal um = new UserMeal
                (LocalDateTime.parse(mapMeal.get("date")), mapMeal.get("description"), Integer.parseInt(mapMeal.get("calories")));
        List<UserMeal> userMeals = new ArrayList<>();
        userMeals.add(um);
        List<UserMealWithExceed> umEList = UserMealsUtil.getExceed(userMeals, 2000);
        ume = umEList.get(0);
        return ume;
    }

    public void delete(Map<Long, UserMealWithExceed> mapMeal, long id) {
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

    public Map<Long, UserMealWithExceed> findByDate(Map<Long, UserMealWithExceed> mapMeal, LocalDate date) {
        Map<Long, UserMealWithExceed> mapByDate = new HashMap<>();
        mapMeal
                .forEach((k, v) -> {
                    if (v.getDateTime().toLocalDate().equals(date))
                        mapByDate.put(k, v);
                });
        return mapByDate;
    }

    public UserMealWithExceed update(Map<Long, UserMealWithExceed> mapMeal, long id, String newDate, String newDescription, String newCalories) {
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

    public UserMealWithExceed findById(Map<Long, UserMealWithExceed> mapMeal, long id) {
        List<UserMeal> listMeal = new ArrayList<>();
        UserMealWithExceed ume = null;
        mapMeal
                .forEach((k, v) -> {
                    if (k == id) {
                        listMeal.add(new UserMeal(v.getCalories(), v.getDateTime(), v.getDescription(), id));
                    }
                });
        if (listMeal.size() > 0) {
            ume = UserMealsUtil.getExceed(listMeal, 2000).get(0);

        }
        return ume;
    }
}
