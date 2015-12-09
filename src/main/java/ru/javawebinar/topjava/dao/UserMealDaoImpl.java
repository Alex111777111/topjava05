package ru.javawebinar.topjava.dao;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Maria on 05.12.2015.
 */
@Repository
public class UserMealDaoImpl implements UserMealDao {
    private static UserMealDaoImpl instance;
    public static Map<Long, UserMeal> mapUserMeal;
    private static long count;

    public UserMealDaoImpl() {
    }

    public static synchronized UserMealDao getInstance() {
        if (instance == null) {
            instance = new UserMealDaoImpl();
            mapUserMeal = new HashMap<>();
            count = 0;
        }
        return instance;
    }


    @Override
    public void create(UserMeal um) {
        um.setId(++count);
        mapUserMeal.put(um.getId(), um);
    }

    @Override
    public void delete(long id) {
        mapUserMeal.remove(id);

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

    @Override
    public void update(UserMeal um) {
        UserMeal neUserMeal = new UserMeal(um.getCalories(), um.getDateTime(), um.getDescription(), um.getId());
        mapUserMeal.put(um.getId(), neUserMeal);

    }

    @Override
    public UserMeal findById(long id) {
        return mapUserMeal.get(id);
    }
}
