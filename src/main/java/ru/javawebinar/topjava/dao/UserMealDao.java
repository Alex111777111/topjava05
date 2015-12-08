package ru.javawebinar.topjava.dao;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Maria on 05.12.2015.
 */
@Repository
public class UserMealDao implements UserDao {

    public UserMealDao() {

    }

    public UserMeal create(Map<String, String> mapMealStr) {

        UserMeal um = new UserMeal
                (LocalDateTime.parse(mapMealStr.get("date")), mapMealStr.get("description"), Integer.parseInt(mapMealStr.get("calories")));
        um.setId(1);
        mapUserMeal.put(1L, um);
        return um;
    }

    public void delete(Map<Long, UserMeal> mapMeal, long id) {
        mapUserMeal.remove(id);
        mapMeal.remove(id);
    }

    public Map<Long, UserMeal> findByDate(Map<Long, UserMeal> mapMeal, LocalDate date) {
        Map<Long, UserMeal> mapByDate =
                mapMeal
                        .entrySet()
                        .stream()
                        .filter(entry -> entry.getValue().getDateTime().toLocalDate().equals(date))
                        .collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue()));
        return mapByDate;
    }

    public UserMeal update(long id, String newDate, String newDescription, String newCalories) {

        UserMeal um = new UserMeal(Integer.parseInt(newCalories), LocalDateTime.parse(newDate), newDescription, id);
        return um;
    }

    public UserMeal findById(Map<Long, UserMeal> mapMeal, long id) {

        return mapMeal.get(id);
    }
}
