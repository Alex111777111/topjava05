package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * GKislin
 * 27.03.2015.
 */
@Repository
public class DataJpaUserMealRepositoryImpl implements UserMealRepository {

    @Autowired
    private ProxyUserMealRepository proxy;


    @Override
    public UserMeal save(UserMeal userMeal, int userId) {
        int idNew = proxy.save(userMeal).getId();
        return getMeaLWithUser(idNew, userId);
    }

    @Override
    public boolean delete(int id, int userId) {
        return proxy.delete(id, userId) != 0;
    }

    @Override
    public UserMeal get(int id, int userId) {

        return proxy.findOne(id, userId).isEmpty() ? null : proxy.findOne(id, userId).get(0);

    }

    @Override
    public List<UserMeal> getAll(int userId) {
        return proxy.findAll(userId);
    }

    @Override
    public List<UserMeal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return proxy.findBetween(startDate, endDate, userId);
    }

    public UserMeal getMeaLWithUser(int id, int userId) {
        List<UserMeal> userMeals = proxy.findMealWihtUser(userId);
        List<UserMeal> um = userMeals
                .stream()
                .filter(userMeal -> userMeal.getId() == id)
                .collect(Collectors.toList());

        return um.isEmpty() ? null : um.get(0);
    }


}
