package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by maria on 20.12.2015.
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserMealServiceTest {

    @Autowired
    protected UserMealService service;

    @Autowired
    private DbPopulator dbPopulator;
    List<UserMeal> meals = new ArrayList<>();
    UserMeal userMealGet;
    UserMeal newUserMeal;
    UserMeal userMealUpdate;
    UserMeal userMealSave;
    UserMeal userMealUpdateExc;


    @Before
    public void setUp() throws Exception {
        dbPopulator.execute();
        meals = Arrays.asList(
                new UserMeal(100007, LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510),
                new UserMeal(100006, LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(100005, LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(100004, LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(100003, LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(100002, LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500)
        );
        userMealGet = new UserMeal(100002, LocalDateTime.parse("2015-05-30T10:00"), "Завтрак", 500);
        newUserMeal = new UserMeal(null, LocalDateTime.parse("2015-12-21T13:00:00"), "Новый обед для админа", 1000);
        userMealUpdate = new UserMeal(100009, LocalDateTime.parse("2015-12-21T00:43:12"), "Ужин для админа новый", 1000);
        userMealSave = new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин дубликат", 2500);
        userMealUpdateExc = new UserMeal(100, LocalDateTime.parse("2015-12-21T13:00:00"), "Новый обед для админа", 1000);
    }

    @Test
    public void testGet() throws Exception {
        UserMeal userMeal = service.get(100002, 100000);
        assertEquals(userMeal.toString(), userMealGet.toString());

    }


    @Test
    public void testGetBetweenDates() throws Exception {
        List<UserMeal> list = (List<UserMeal>) service.getBetweenDates(LocalDate.parse("2015-05-30"), LocalDate.parse("2015-05-31"), 100000);
        assertEquals(list.size(), 6);
        assertEquals(meals.get(1).getCalories(), list.get(1).getCalories());
        assertEquals(meals.get(5).toString(), list.get(5).toString());

    }

    @Test
    public void testGetBetweenDateTimes() throws Exception {
        List<UserMeal> list = (List<UserMeal>) service.getBetweenDateTimes(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), LocalDateTime.of(2015, Month.MAY, 31, 20, 0), 100000);
        assertEquals(list.size(), 6);
        assertEquals(meals.get(2).getDescription(), list.get(2).getDescription());
        assertEquals(meals.get(4).toString(), list.get(4).toString());
    }

    @Test
    public void testGetAll() throws Exception {
        List<UserMeal> list = (List<UserMeal>) service.getAll(100000);
        assertEquals(list.size(), 6);
        assertEquals(meals.get(3).toString(), list.get(3).toString());
        assertEquals(meals.get(5).getDateTimeLocal(), list.get(5).getDateTimeLocal());
        // assertEquals(meals,list);


    }

    @Test
    public void testUpdate() throws Exception {
        UserMeal userMeal = service.update(userMealUpdate, 100001);
        assertEquals(userMeal.toString(), userMealUpdate.toString());
    }

    @Test
    public void testSave() throws Exception {
        UserMeal createdUserMeal = service.save(newUserMeal, 100001);
        newUserMeal.setId(createdUserMeal.getId());
        assertEquals(newUserMeal.toString(), createdUserMeal.toString());

    }

    @Test(expected = NotFoundException.class)
    public void testAnotherUserMealGet() throws Exception {
        int id = 100;
        service.get(id, 100000);
    }

    @Test(expected = DataAccessException.class)
    public void testDuplicateMailSave() throws Exception {
        service.save(userMealSave, 100000);
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(100008, 100001);
        assertEquals(service.getAll(100001).size(), 2);

    }

    @Test(expected = NotFoundException.class)
    public void testAnotherUserMealUpdate() throws Exception {
        service.update(userMealUpdateExc, 100001);
    }

}