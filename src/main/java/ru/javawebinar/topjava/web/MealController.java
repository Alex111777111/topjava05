package ru.javawebinar.topjava.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.util.TimeUtil;
import ru.javawebinar.topjava.web.meal.UserMealRestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

/**
 * Created by Maria on 13.01.2016.
 */
@Controller
public class MealController {

    @Autowired
    private UserMealRestController mealController;

    @RequestMapping(value = "/meals", method = RequestMethod.GET)
    public String mealList(Model model) {
        model.addAttribute("mealList", mealController.getAll());
        return "mealList";
    }

    @RequestMapping(value = "/meals", method = RequestMethod.POST)
    public String setFilter(HttpServletRequest request) {
        String action = request.getParameter("action");
        if (action.equals("filter")) {
            LocalDate startDate = TimeUtil.parseLocalDate(resetParam("startDate", request), TimeUtil.MIN_DATE);
            LocalDate endDate = TimeUtil.parseLocalDate(resetParam("endDate", request), TimeUtil.MAX_DATE);
            LocalTime startTime = TimeUtil.parseLocalTime(resetParam("startTime", request), LocalTime.MIN);
            LocalTime endTime = TimeUtil.parseLocalTime(resetParam("endTime", request), LocalTime.MAX);
            request.setAttribute("mealList", mealController.getBetween(startDate, startTime, endDate, endTime));

        }
        return "mealList";

    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView save(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        String action = request.getParameter("action");
        if (action.equals("save")) {
            UserMeal userMeal = new UserMeal(id.isEmpty() ? null : Integer.valueOf(id),
                    LocalDateTime.parse(request.getParameter("dateTime")),
                    request.getParameter("description"),
                    Integer.valueOf(request.getParameter("calories")));
            if (userMeal.isNew()) {
                mealController.create(userMeal);
            } else {
                mealController.update(userMeal);
            }
            return new ModelAndView("mealList", "mealList", mealController.getAll());
        } else return null;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView deleteMeal(@RequestParam(value = "id") int id) {
        mealController.delete(id);
        return new ModelAndView("mealList", "mealList", mealController.getAll());
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public ModelAndView getEdit(HttpServletRequest request) {
        UserMeal userMeal = mealController.get(getId(request));
        request.setAttribute("userMeal", userMeal);
        return new ModelAndView("mealEdit", "userMeal", userMeal);
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView getCreate(Model model) {
        model.addAttribute("userMeal", new UserMeal());
        return new ModelAndView("mealEdit", "userMeal", new UserMeal());
    }


    private String resetParam(String param, HttpServletRequest request) {
        String value = request.getParameter(param);
        request.setAttribute(param, value);
        return value;
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }
}

