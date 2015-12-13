package ru.javawebinar.topjava.web.meal;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.LoggerWrapper;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.service.UserMealService;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
@Controller
public class UserMealRestController {
    protected final LoggerWrapper LOG = LoggerWrapper.get(getClass());

    @Autowired
    private UserMealService service;


    public UserMeal get(int id, int userId) {
        if (service.get(id).getUser().getId() == userId) {
            LOG.info("get " + id);
            return service.get(id);
        } else {
            LOG.error("Trying to get userMeal, not belonging to this user");
            throw new NotFoundException("Trying to get userMeal, not belonging to this user");

        }
    }

    public UserMeal create(UserMeal usermeal) {
        usermeal.setId(null);
        LOG.info("create " + usermeal);
        return service.save(usermeal);
    }

    public void delete(int id, int userId) {
        if (service.get(id).getUser().getId() == userId) {
            LOG.info("delete " + id);
            service.delete(id);
        } else {
            LOG.error("Trying to delete userMeal, not belonging to this user");
            throw new NotFoundException("Trying to delete userMeal, not belonging to this user");
        }
    }

    public void update(UserMeal userMeal, int id, int userId) {
        if (service.get(id).getUser().getId() == userId) {
            userMeal.setId(id);
            LOG.info("update " + userMeal);
            service.update(userMeal);
        } else {
            LOG.error("Trying to update userMeal, not belonging to this user");
            throw new NotFoundException("Trying to update userMeal, not belonging to this user");
        }
    }

    public List<UserMeal> getByUser(int id) {
        LOG.info("get by user id = " + id);
        return service.getByUser(id);
    }

}
