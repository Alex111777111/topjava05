package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.LoggerWrapper;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by B on 03.12.2015.
 */
public class MealServlet extends HttpServlet {
    private static final LoggerWrapper LOG = LoggerWrapper.get(UserServlet.class);

   /* protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dateStrings = request.getParameter("datetime");

        List<UserMealWithExceed> exceededList = new ArrayList<>();
        request.setAttribute("list", exceededList);
        request.getRequestDispatcher("mealList.jsp").forward(request, response);
    }
*/
  /*  protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }*/
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("redirect to mealList");

//        request.getRequestDispatcher("/mealList.jsp").forward(request, response);
        response.sendRedirect("mealList.jsp");
    }


}
