package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.LoggerWrapper;
import ru.javawebinar.topjava.model.UserMealWithExceed;
import ru.javawebinar.topjava.util.UserMealDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Maria on 05.12.2015.
 */
public class MealEditServlet extends HttpServlet {
    private static final LoggerWrapper LOG = LoggerWrapper.get(UserServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("redirect to mealEdit");
        response.sendRedirect("mealEdit.jsp");
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("record meal");

        String mealDateStr = req.getParameter("date");
        String mealDescription = req.getParameter("description");
        String mealCalStr = req.getParameter("calories");


        Map<String, String> mapMeal = new HashMap<>();
        mapMeal.put("date", mealDateStr);
        mapMeal.put("description", mealDescription);
        mapMeal.put("calories", mealCalStr);


        UserMealDao umd = new UserMealDao();
        UserMealWithExceed um = umd.create(mapMeal);
        List<UserMealWithExceed> list = new ArrayList<>();
        list.add(um);

        req.setAttribute("list", list);
        req.getRequestDispatcher("/mealList.jsp").forward(req, resp);


    }
}