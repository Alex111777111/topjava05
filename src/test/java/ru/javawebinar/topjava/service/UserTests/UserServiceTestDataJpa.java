package ru.javawebinar.topjava.service.UserTests;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserWithMealRepository;
import ru.javawebinar.topjava.repository.datajpa.DataJpaUserMealRepositoryImpl;

import static ru.javawebinar.topjava.MealTestData.ADMIN_MEAL_ID;
import static ru.javawebinar.topjava.UserTestData.ADMIN;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.MATCHER;

/**
 * Created by Maria on 03.01.2016.
 */
@ActiveProfiles({Profiles.POSTGRES, Profiles.DATAJPA})
public class UserServiceTestDataJpa extends UserServiceTest {


    @Autowired
    private UserWithMealRepository repository;

    @Test
    public void testGetMeaLWithUser() throws Exception {
        UserMeal meal = repository.getMeaLWithUser(ADMIN_MEAL_ID, ADMIN_ID);
        MATCHER.assertEquals(meal.getUser(), ADMIN);
    }

}
