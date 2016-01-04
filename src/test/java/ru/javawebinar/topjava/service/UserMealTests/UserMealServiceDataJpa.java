package ru.javawebinar.topjava.service.UserMealTests;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserWithMealRepository;

import static ru.javawebinar.topjava.MealTestData.MATCHER;
import static ru.javawebinar.topjava.MealTestData.USER_MEALS;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

/**
 * Created by B on 03.01.2016.
 */
@ActiveProfiles({Profiles.POSTGRES, Profiles.DATAJPA})
public class UserMealServiceDataJpa extends UserMealServiceTest {


    @Autowired
    private UserWithMealRepository repository;

    @Test
    public void testGetUserWithMeals() throws Exception {
        User user = repository.getUserWithMeals(USER_ID);
        MATCHER.assertCollectionEquals(user.getMeals(), USER_MEALS);

    }
}
