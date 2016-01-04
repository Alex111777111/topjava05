package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.repository.UserWithMealRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * GKislin
 * 27.03.2015.
 */
@Repository
public class DataJpaUserMealRepositoryImpl implements UserMealRepository, UserWithMealRepository {

    @Autowired
    private ProxyUserMealRepository proxy;
    @Autowired
    private ProxyUserRepository proxyUserRepository;


    @Override
    public UserMeal save(UserMeal userMeal, int userId) {
        User user = proxyUserRepository.getOne(userId);
        userMeal.setUser(user);
        if (!userMeal.isNew() && get(userMeal.getId(), userId) == null) {
            return null;
        } else {
            return proxy.save(userMeal);
        }
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


    @Override
    public UserMeal getMeaLWithUser(int id, int userId) {
        List<UserMeal> userMeals = proxy.findMealWihtUser(userId);
        List<UserMeal> um = userMeals
                .stream()
                .filter(userMeal -> userMeal.getId() == id)
                .collect(Collectors.toList());
        return um.isEmpty() ? null : um.get(0);
    }

    @Override
    public User getUserWithMeals(int userId) {
        List<UserMeal> userMeals = proxy.findMealWihtUser(userId);
        if (!userMeals.isEmpty()) {
            User user = userMeals.get(0).getUser();
            user.setMeals(userMeals);
            return user;
        } else return null;

    }

}
