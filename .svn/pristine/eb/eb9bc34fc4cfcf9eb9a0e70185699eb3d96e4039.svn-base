package com.rayton.gps.exception;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CustomExceptionResolver implements HandlerExceptionResolver {


    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
                                         Exception ex) {
        // String msg = ex.getMessage();
        ex.printStackTrace();

        ModelAndView modelAndView = new ModelAndView("errorPage");
        modelAndView.addObject("error", ex.getMessage());
        // request.setAttribute("error", ex.getMessage());
        return modelAndView;


    }


}