package com.edata.monitor;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CustomExceptionResolver implements HandlerExceptionResolver {


    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
                                         Exception ex) {
        // String msg = ex.getMessage();
        ex.printStackTrace();

        ModelAndView modelAndView = new ModelAndView("redirect:/home");


        return modelAndView;


    }


}