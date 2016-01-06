package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.*;
import static ru.javawebinar.topjava.UserTestData.MATCHER;

@ActiveProfiles(profiles = {Profiles.POSTGRES, Profiles.DATAJPA})
public class UserMealServiceDataJpa extends UserMealServiceTest {


    @Test
    public void testGetUserWithMeals() throws Exception {
        UserMeal userMeal = service.getMeaLWithUser(ADMIN_MEAL_ID, ADMIN_ID);
        MATCHER.assertEquals(userMeal.getUser(), ADMIN);
    }
}

