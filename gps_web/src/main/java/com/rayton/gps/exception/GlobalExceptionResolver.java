package com.rayton.gps.exception;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class GlobalExceptionResolver implements HandlerExceptionResolver {

    private Logger log = Logger.getLogger(getClass());

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
                                         Exception ex) {
        // String msg = ex.getMessage();
        ex.printStackTrace();
        // AJAX JSON
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // RestController restControllerAnno = AnnotationUtils.findAnnotation(handlerMethod.getBeanType(),
        //         RestController.class);
        ResponseBody responseBodyAnno = AnnotationUtils.findAnnotation(handlerMethod.getMethod(), ResponseBody.class);
       ModelAndView modelAndView = new ModelAndView();
        if (responseBodyAnno != null) {

            try {
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().write(JSON.toJSONString(ex.getMessage()));
                log.warn(JSON.toJSONString(ex.getMessage()));
            } catch (Exception e) {
                ex.printStackTrace();
            }

        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("message", ex.getMessage());
            modelAndView = new ModelAndView("error", map);

            // modelAndView.addObject("message", msg);
            // modelAndView.setViewName("error");
        }
        return modelAndView;

    }


}