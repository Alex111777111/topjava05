package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;

import static ru.javawebinar.topjava.MealTestData.ADMIN_MEAL_ID;
import static ru.javawebinar.topjava.MealTestData.MATCHER;
import static ru.javawebinar.topjava.MealTestData.USER_MEALS;
import static ru.javawebinar.topjava.UserTestData.*;

/**
 * Created by Maria on 03.01.2016.
 */
@ActiveProfiles(profiles = {Profiles.POSTGRES, Profiles.DATAJPA})
public class UserServiceTestDataJpa extends UserServiceTest {


    @Test
    public void testGetMeaLWithUser() throws Exception {
        User user = service.getUserWithMeals(USER_ID);
        MATCHER.assertCollectionEquals(user.getMeals(), USER_MEALS);
    }

}
