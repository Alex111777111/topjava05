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

    @Override
    public void create(UserMeal um) {
        um.setId(mapUserMeal.size() + 1);
        mapUserMeal.put(um.getId(), um);
    }

    @Override
    public void delete(Map<Long, UserMeal> mapMeal, long id) {
        mapUserMeal.remove(id);
        mapMeal.remove(id);
    }

    @Override
    public Map<Long, UserMeal> findByDate(Map<Long, UserMeal> mapMeal, LocalDate date) {
        Map<Long, UserMeal> mapByDate =
                mapMeal
                        .entrySet()
                        .stream()
                        .filter(entry -> entry.getValue().getDateTime().toLocalDate().equals(date))
                        .collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue()));
        return mapByDate;
    }

    @Override
    public void update(UserMeal um) {
        UserMeal neUserMeal = new UserMeal(um.getCalories(), um.getDateTime(), um.getDescription(), um.getId());
        mapUserMeal.put(um.getId(), neUserMeal);

    }

    @Override
    public UserMeal findById(Map<Long, UserMeal> mapMeal, long id) {
        return mapMeal.get(id);
    }
}
