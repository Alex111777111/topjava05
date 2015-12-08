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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Maria on 05.12.2015.
 */
public class MealEditServlet extends HttpServlet {
    private static final LoggerWrapper LOG = LoggerWrapper.get(UserServlet.class);
    UserDao umd;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init();
        umd = UserDao.instance;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("redirect to mealEdit");
        if (!request.getParameterNames().hasMoreElements()) {
            response.sendRedirect("mealEdit.jsp");
        } else {

            if (request.getParameter("action").equals("edit")) {
                long id = Long.parseLong(request.getParameter("id"));

                UserMeal um = umd.findById(umd.mapUserMeal, id);
                List<UserMeal> userMeals = new ArrayList<>();
                userMeals.add(um);
                List<UserMealWithExceed> umEList = UserMealsUtil.getExceed(userMeals, 2000);
                UserMealWithExceed ume = umEList.get(0);
                request.setAttribute("uMeal", ume);
                request.getRequestDispatcher("/mealEdit.jsp").forward(request, response);
            }
            if (request.getParameter("action").equals("delete")) {
                long id = Long.parseLong(request.getParameter("id"));

                umd.delete(umd.mapUserMeal, id);
               /* request.setAttribute("uMeal", umd.getUme());
                request.getRequestDispatcher("/mealEdit.jsp").forward(request, response);*/
                response.sendRedirect("mealList.jsp");


            }
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("id").isEmpty()) {
            LOG.debug("record meal");

            String mealDateStr = req.getParameter("date");
            String mealDescription = req.getParameter("description");
            String mealCalStr = req.getParameter("calories");


            Map<String, String> mapMeal = new HashMap<>();
            mapMeal.put("date", mealDateStr);
            mapMeal.put("description", mealDescription);
            mapMeal.put("calories", mealCalStr);

            UserMeal um = umd.create(mapMeal);
            List<UserMeal> userMeals = new ArrayList<>();
            userMeals.add(um);
            List<UserMealWithExceed> umEList = UserMealsUtil.getExceed(userMeals, 2000);
            UserMealWithExceed ume = umEList.get(0);

            List<UserMealWithExceed> list = new ArrayList<>();
            list.add(ume);

            req.setAttribute("list", list);
            req.getRequestDispatcher("/mealList.jsp").forward(req, resp);
        } else {
            LOG.debug("update meal");

            String mealDateStr = req.getParameter("date");
            String mealDescription = req.getParameter("description");
            String mealCalStr = req.getParameter("calories");
            String mealIdStr = req.getParameter("id");
            //  UserMeal um = umd.findById(umd.mapUserMeal, Long.parseLong(mealIdStr));
            // UserMealWithExceed oldUser = umd.getUme();


            UserMeal newUser = umd.update(Long.parseLong(mealIdStr), mealDateStr, mealDescription, mealCalStr);

            List<UserMeal> userMeals = new ArrayList<>();
            userMeals.add(newUser);
            List<UserMealWithExceed> umEList = UserMealsUtil.getExceed(userMeals, 2000);
            UserMealWithExceed ume = umEList.get(0);

            List<UserMealWithExceed> list = new ArrayList<>();
            list.add(ume);
            req.setAttribute("list", list);
            req.getRequestDispatcher("/mealList.jsp").forward(req, resp);

        }


    }
}