package ru.javawebinar.topjava.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.LoggerWrapper;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.repository.mock.InMemoryUserMealRepositoryImpl;
import ru.javawebinar.topjava.util.TimeUtil;
import ru.javawebinar.topjava.util.UserMealsUtil;
import ru.javawebinar.topjava.web.meal.UserMealRestController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

/**
 * User: gkislin
 * Date: 19.08.2014
 */

public class MealServlet extends HttpServlet {
    private static final LoggerWrapper LOG = LoggerWrapper.get(MealServlet.class);

    //   private UserMealRepository repository;
    int userId;
    private UserMealRestController controller;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        //    repository = new InMemoryUserMealRepositoryImpl();
        userId = 1;
        // ServletContext servletContext = getServletContext();
       /* ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        MyBean myBean = (MyBean)ctx.getBean("myBean");*/
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            controller = appCtx.getBean(UserMealRestController.class);
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        if (request.getParameter("userId") != null) {
            userId = LoggedUser.getId(request.getParameter("userId"));
            // response.sendRedirect("mealList.jsp");
            //  request.setAttribute("userId");
            request.setAttribute("mealList",
                    UserMealsUtil.getWithExceeded(controller.getByUser(userId), UserMealsUtil.DEFAULT_CALORIES_PER_DAY));
            request.getRequestDispatcher("/mealList.jsp").forward(request, response);

        } else if (request.getParameter("fromDate") != null || request.getParameter("toDate") != null || request.getParameter("fromTime") != null || request.getParameter("toTime") != null) {
            String fromDate = request.getParameter("fromDate");
            String toDate = request.getParameter("toDate");
            String fromTime = request.getParameter("fromTime");
            String toTime = request.getParameter("toTime");


            LocalDate dateFrom = fromDate != "" ? LocalDate.parse(fromDate, TimeUtil.DATE_FORMATTER) : LocalDate.MIN;
            LocalDate dateTo = toDate != "" ? LocalDate.parse(toDate, TimeUtil.DATE_FORMATTER) : LocalDate.MAX;
            LocalTime timeFrom = fromTime != "" ? LocalTime.parse(fromTime, TimeUtil.TME_FORMATTER) : LocalTime.MIN;
            LocalTime timeTo = toTime != "" ? LocalTime.parse(toTime, TimeUtil.TME_FORMATTER) : LocalTime.MAX;


            List<UserMeal> listDate = UserMealsUtil.getFilteredWithExceededByDate(controller.getByUser(userId), dateFrom, dateTo, UserMealsUtil.DEFAULT_CALORIES_PER_DAY);

            request.setAttribute("mealList",
                    UserMealsUtil.getFilteredWithExceeded(listDate, timeFrom, timeTo, UserMealsUtil.DEFAULT_CALORIES_PER_DAY));
            request.getRequestDispatcher("/mealList.jsp").forward(request, response);


        } else {
            String id = request.getParameter("id");
            UserMeal userMeal = new UserMeal(id.isEmpty() ? null : Integer.valueOf(id),
                    LocalDateTime.parse(request.getParameter("dateTime")),
                    request.getParameter("description"),
                    Integer.valueOf(request.getParameter("calories")));
            LOG.info(userMeal.isNew() ? "Create {}" : "Update {}", userMeal);
            controller.create(userMeal);
            response.sendRedirect("meals");
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        // int userId = Integer.parseInt(request.getParameter("userId"));

        if (action == null) {
            LOG.info("getAll");
            request.setAttribute("mealList",
                    UserMealsUtil.getWithExceeded(controller.getByUser(userId), UserMealsUtil.DEFAULT_CALORIES_PER_DAY));
            request.getRequestDispatcher("/mealList.jsp").forward(request, response);
        } else if (action.equals("delete")) {
            int id = getId(request);
            LOG.info("Delete {}", id);
            controller.delete(id, userId);
            response.sendRedirect("meals");
        } else {
            final UserMeal meal = action.equals("create") ?
                    new UserMeal(LocalDateTime.now(), "", 1000) :
                    controller.get(getId(request), userId);
            request.setAttribute("meal", meal);
            request.getRequestDispatcher("mealEdit.jsp").forward(request, response);
        }
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }
}
