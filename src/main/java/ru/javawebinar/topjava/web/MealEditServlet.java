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
import java.time.LocalDateTime;
import java.util.List;

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
                request.setAttribute("uMeal", um);
                request.getRequestDispatcher("/mealEdit.jsp").forward(request, response);
            }
            if (request.getParameter("action").equals("delete")) {
                long id = Long.parseLong(request.getParameter("id"));

                umd.delete(umd.mapUserMeal, id);
                List<UserMealWithExceed> list = UserMealsUtil.getUserMealWithExceeds();
                request.setAttribute("list", list);
                request.getRequestDispatcher("/mealList.jsp").forward(request, response);
                //    response.sendRedirect("mealList.jsp");


            }
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String mealDateStr = req.getParameter("date");
        String mealDescription = req.getParameter("description");
        String mealCalStr = req.getParameter("calories");
        if (!(mealCalStr.equals("") || mealDateStr.equals("") || mealDescription.equals(""))) {
            if (req.getParameter("id").isEmpty()) {
                LOG.debug("record meal");
                UserMeal userMeal = new UserMeal
                        (LocalDateTime.parse(mealDateStr), mealDescription, Integer.parseInt(mealCalStr));
                umd.create(userMeal);
            } else {
                LOG.debug("update meal");
                String mealIdStr = req.getParameter("id");
                UserMeal um = new UserMeal(Integer.parseInt(mealCalStr), LocalDateTime.parse(mealDateStr), mealDescription, Long.parseLong(mealIdStr));
                umd.update(um);

            }
            List<UserMealWithExceed> list = UserMealsUtil.getUserMealWithExceeds();
            req.setAttribute("list", list);
            req.getRequestDispatcher("/mealList.jsp").forward(req, resp);
        } else {

            req.setAttribute("message", "Fill in all the fields");
            req.getRequestDispatcher("/mealEdit.jsp").forward(req, resp);
        }
    }
}