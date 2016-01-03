package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * GKislin
 * 27.03.2015.
 */
@Repository
public class DataJpaUserMealRepositoryImpl implements UserMealRepository {


    @Autowired
    private ProxyUserMealRepository proxy;

    @Autowired
    private ProxyUserRepository proxyUserRepository;
    @Override
    public UserMeal save(UserMeal userMeal, int userId) {
        UserMeal um = proxy.save(userMeal);
        User user = proxyUserRepository.getOne(userId);
        um.setUser(user);
        return get(um.getId(), userId) != null ? get(um.getId(), userId) : null;
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
}
