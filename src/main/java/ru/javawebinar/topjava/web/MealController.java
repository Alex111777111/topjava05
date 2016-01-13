package ru.javawebinar.topjava.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.LoggerWrapper;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.service.UserMealService;
import ru.javawebinar.topjava.util.TimeUtil;
import ru.javawebinar.topjava.util.UserMealsUtil;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

/**
 * Created by B on 13.01.2016.
 */
@Controller
public class MealController {
    private static final LoggerWrapper LOG = LoggerWrapper.get(MealController.class);

    @Autowired
    private UserMealService service;

    @RequestMapping(value = "/meals", method = RequestMethod.GET)
    public String mealList(Model model) {
        LOG.info("getAll");
        Model mealList = model.addAttribute("mealList", UserMealsUtil.getFilteredWithExceeded(service.getAll(LoggedUser.id()), LocalTime.MIN, LocalTime.MAX, LoggedUser.getCaloriesPerDay()));
        return "mealList";
    }


   /* @RequestMapping(value = "/meals", method = RequestMethod.POST)
    public String setMeal(HttpServletRequest request) {
       String action = request.getParameter("action");
        *//*int userId = Integer.valueOf(request.getParameter("userId"));
        LoggedUser.setId(userId);*//*
        // return "redirect:meals";

      *//*  if (action.equals("delete")) {
            int id = getId(request);
            LOG.info("Delete {}", id, LoggedUser.id);
            service.delete(id, LoggedUser.id());
            return "redirect:meals";
        }*//*
        return null;
    }*/

    @RequestMapping(value = "/meals", method = RequestMethod.POST)
    public String setFilter(HttpServletRequest request) {
        String action = request.getParameter("action");
        if (action.equals("filter")) {
            LocalDate startDate = TimeUtil.parseLocalDate(resetParam("startDate", request), TimeUtil.MIN_DATE);
            LocalDate endDate = TimeUtil.parseLocalDate(resetParam("endDate", request), TimeUtil.MAX_DATE);
            LocalTime startTime = TimeUtil.parseLocalTime(resetParam("startTime", request), LocalTime.MIN);
            LocalTime endTime = TimeUtil.parseLocalTime(resetParam("endTime", request), LocalTime.MAX);
            request.setAttribute("mealList", UserMealsUtil.getFilteredWithExceeded(service.getBetweenDateTimes(startDate.atTime(startTime), endDate.atTime(endTime), LoggedUser.id()), startTime, endTime, LoggedUser.getCaloriesPerDay()));

        }
        return "mealList";

    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView deleteMeal(@RequestParam(value = "id") int id) {
        service.delete(id, LoggedUser.id());
        return new ModelAndView("mealList", "mealList", service.getAll(LoggedUser.id()));
    }

    ​

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

