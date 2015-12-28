package ru.javawebinar.topjava.service;

import oracle.jrockit.jfr.jdkevents.ThrowableTracer;
import org.junit.AssumptionViolatedException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.internal.Throwables;
import org.junit.rules.ExpectedException;
import org.junit.rules.Stopwatch;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.junit.runners.model.Statement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.LoggerWrapper;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class UserMealServiceTest {
    protected static final LoggerWrapper LOG = LoggerWrapper.get(UserMealServiceTest.class);


    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Rule
    public StopwatchTest stopwatchTest = new StopwatchTest();



    @Autowired
    protected UserMealService service;

    @Test
    public void testDelete() throws Throwable {
        service.delete(MealTestData.MEAL1_ID, USER_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(MEAL6, MEAL5, MEAL4, MEAL3, MEAL2), service.getAll(USER_ID));
    }


    @Test
    public void testDeleteNotFound() throws Exception {
        exception.expect(NotFoundException.class);
        exception.expectMessage("Not found entity with id=100002");
        service.delete(MEAL1_ID, 1);

    }

    @Test
    public void testSave() throws Exception {
        UserMeal created = getCreated();
        service.save(created, USER_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(created, MEAL6, MEAL5, MEAL4, MEAL3, MEAL2, MEAL1), service.getAll(USER_ID));

    }

    @Test
    public void testGet() throws Exception {
        UserMeal actual = service.get(ADMIN_MEAL_ID, ADMIN_ID);
        MATCHER.assertEquals(ADMIN_MEAL, actual);

    }

    @Test
    public void testGetNotFound() throws Exception {
        exception.expect(NotFoundException.class);
        exception.expectMessage("Not found entity with id=100002");
        service.get(MEAL1_ID, ADMIN_ID);
    }

    @Test
    public void testUpdate() throws Exception {
        UserMeal updated = getUpdated();
        service.update(updated, USER_ID);
        MATCHER.assertEquals(updated, service.get(MEAL1_ID, USER_ID));

    }

    @Test
    public void testNotFoundUpdate() throws Exception {
        exception.expect(NotFoundException.class);
        exception.expectMessage("Not found entity with id=100002");
        UserMeal item = service.get(MEAL1_ID, USER_ID);
        service.update(item, ADMIN_ID);



    }

    @Test
    public void testGetAll() throws Exception {
        MATCHER.assertCollectionEquals(USER_MEALS, service.getAll(USER_ID));

    }

    @Test
    public void testGetBetween() throws Exception {
        MATCHER.assertCollectionEquals(Arrays.asList(MEAL3, MEAL2, MEAL1),
                service.getBetweenDates(LocalDate.of(2015, Month.MAY, 30), LocalDate.of(2015, Month.MAY, 30), USER_ID));

    }
}
