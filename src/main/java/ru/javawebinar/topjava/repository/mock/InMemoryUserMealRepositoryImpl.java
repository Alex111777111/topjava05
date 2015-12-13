package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.util.UserMealsUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * GKislin
 * 15.09.2015.
 */
@Repository
public class InMemoryUserMealRepositoryImpl implements UserMealRepository {
    private Map<Integer, UserMeal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        UserMealsUtil.MEAL_LIST.forEach(this::save);
    }

    @Override
    public UserMeal save(UserMeal userMeal) {
        if (userMeal.isNew()) {
            userMeal.setId(counter.incrementAndGet());
        }
        return repository.put(userMeal.getId(), userMeal);
    }

    @Override
    public boolean delete(int id) {
        return repository.remove(id) != null;

    }

    @Override
    public UserMeal get(int id) {
        return repository.get(id);
    }


    @Override
    public List<UserMeal> getMealByUser(int userId) {
        List<UserMeal> listByUser =
                repository
                        .values()
                        .stream()
                        .filter(entry -> entry.getUser().getId() == userId)
                        .sorted((o1, o2) -> o1.getDateTime().compareTo(o2.getDateTime()))
                        .collect(Collectors.toList());

        return listByUser;
    }
}

