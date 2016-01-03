package ru.javawebinar.topjava.service.UserMealTests;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;

/**
 * Created by Maria on 03.01.2016.
 */
@ActiveProfiles({Profiles.HSQLDB, Profiles.JPA})
public class UserMealServiceTestJPA extends UserMealServiceTest {
}
