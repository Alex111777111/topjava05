package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.LoggerWrapper;
import ru.javawebinar.topjava.dao.UserDao;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;
import ru.javawebinar.topjava.util.UserMealsUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maria on 03.12.2015.
 */
public class MealServlet extends HttpServlet {
    private static final LoggerWrapper LOG = LoggerWrapper.get(UserServlet.class);
    UserDao umd;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        umd = UserDao.instance;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("redirect to mealList");
        List<UserMealWithExceed> list = UserMealsUtil.getUserMealWithExceeds();
        request.setAttribute("list", list);
        request.getRequestDispatcher("/mealList.jsp").forward(request, response);
        //     response.sendRedirect("mealList.jsp");
    }


}
