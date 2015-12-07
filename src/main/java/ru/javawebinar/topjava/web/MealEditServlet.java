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
        if (!request.getParameterNames().hasMoreElements()) {
            response.sendRedirect("mealEdit.jsp");
        } else {
            if (request.getParameter("action").equals("edit")) {
                long id = Long.parseLong(request.getParameter("id"));
                UserMealDao umd = UserMealDao.getInstance();
                Map<Long, UserMealWithExceed> mapMeal = new HashMap<>();
                mapMeal.put(umd.getUme().getId(), umd.getUme());
                UserMealWithExceed ume = umd.findById(mapMeal, id);
                request.setAttribute("uMeal", ume);
                request.getRequestDispatcher("/mealEdit.jsp").forward(request, response);
            }
            if (request.getParameter("action").equals("delete")) {
                long id = Long.parseLong(request.getParameter("id"));
                UserMealDao umd = UserMealDao.getInstance();
            }
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserMealDao umd = UserMealDao.getInstance();
        // if (req.getParameterMap().size() == 3){
        if (req.getParameter("id").isEmpty()) {
            LOG.debug("record meal");

            String mealDateStr = req.getParameter("date");
            String mealDescription = req.getParameter("description");
            String mealCalStr = req.getParameter("calories");


            Map<String, String> mapMeal = new HashMap<>();
            mapMeal.put("date", mealDateStr);
            mapMeal.put("description", mealDescription);
            mapMeal.put("calories", mealCalStr);

            UserMealWithExceed um = umd.create(mapMeal);
            List<UserMealWithExceed> list = new ArrayList<>();
            list.add(um);

            req.setAttribute("list", list);
            req.getRequestDispatcher("/mealList.jsp").forward(req, resp);
        } else if (req.getParameterMap().size() == 4) {
            LOG.debug("update meal");

            String mealDateStr = req.getParameter("date");
            String mealDescription = req.getParameter("description");
            String mealCalStr = req.getParameter("calories");
            String mealIdStr = req.getParameter("id");

            UserMealWithExceed oldUser = umd.getUme(); //TODO ById
            Map<Long, UserMealWithExceed> mapMeal = new HashMap<>();
            mapMeal.put(oldUser.getId(), oldUser);

            UserMealWithExceed newUser = umd.update(mapMeal, Long.parseLong(mealIdStr), mealDateStr, mealDescription, mealCalStr);
            umd.setUme(newUser);
            List<UserMealWithExceed> list = new ArrayList<>();
            list.add(newUser);

            req.setAttribute("list", list);
            req.getRequestDispatcher("/mealList.jsp").forward(req, resp);

        }


    }
}