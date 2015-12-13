package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.LoggerWrapper;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.util.exception.ExceptionUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
@Service
public class UserMealServiceImpl implements UserMealService {
    protected final LoggerWrapper LOG = LoggerWrapper.get(getClass());

    @Autowired
    private UserMealRepository repository;


    @Override
    public UserMeal save(UserMeal userMeal, int userId) {
        if (userMeal.getUser().getId() == userId) {
            return repository.save(userMeal);
        } else {
            LOG.error("Trying to save userMeal, not belonging to this user");
            throw new NotFoundException("Trying to save userMeal, not belonging to this user");
        }
    }

    @Override
    public void delete(int id, int userId) throws NotFoundException {
        if (repository.get(id).getUser().getId() == userId) {
            ExceptionUtil.check(repository.delete(id), id);
        } else {
            LOG.error("Trying to delete userMeal, not belonging to this user");
            throw new NotFoundException("Trying to delete userMeal, not belonging to this user");
        }

    }

    @Override
    public UserMeal get(int id, int userId) throws NotFoundException {
        if (repository.get(id).getUser().getId() == userId) {
            return ExceptionUtil.check(repository.save(repository.get(id)), id);
        } else {
            LOG.error("Trying to get userMeal, not belonging to this user");
            throw new NotFoundException("Trying to get userMeal, not belonging to this user");
        }
    }


    @Override
    public List<UserMeal> getByUser(int userId) {
        return (repository.getMealByUser(userId));
    }

    @Override
    public void update(UserMeal userMeal, int userId) {
        if (userMeal.getUser().getId() == userId) {
            repository.save(userMeal);
        } else {
            LOG.error("Trying to update userMeal, not belonging to this user");
            throw new NotFoundException("Trying to update userMeal, not belonging to this user");
        }
    }
}
