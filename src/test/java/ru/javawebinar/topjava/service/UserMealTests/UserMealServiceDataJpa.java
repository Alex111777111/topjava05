package ru.javawebinar.topjava.service.UserMealTests;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;

/**
 * Created by B on 03.01.2016.
 */
@ActiveProfiles({Profiles.POSTGRES, Profiles.DATAJPA})
public class UserMealServiceDataJpa extends UserMealServiceTest {
}
