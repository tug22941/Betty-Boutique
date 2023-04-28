package com.teksystems.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

//controller class responsible for formatting and displaying runtime errors
@Slf4j
@Controller
@ControllerAdvice
public class ErrorController {

    //method responsible for running the exception handling process
    @ExceptionHandler(Exception.class)
    public ModelAndView handleAllException(HttpServletRequest request, Exception ex) {

        log.debug("In the HANDLE ALL EXCEPTION controller method:");

        String requestUrl = getRequestURL(request);
        log.warn("Error page exception happened on URL : " + requestUrl, ex);

        String htmlStackTrace = getHTMLStackTrace(ExceptionUtils.getStackFrames(ex));

        ModelAndView model = new ModelAndView("/error/500");

        String stackTrace = getHTMLStackTrace(ExceptionUtils.getStackFrames(ex));

        // display error message as the request URL if it was an error page
        // otherwise display error message from the calling class
        model.addObject("requestUrl", requestUrl);
        model.addObject("message", ex.getMessage());
        model.addObject("stackTrace", stackTrace);

        if (ex.getCause() != null) {
            model.addObject("rootcause", ExceptionUtils.getRootCause(ex));

            String roottrace = getHTMLStackTrace(ExceptionUtils.getRootCauseStackTrace(ex));
            model.addObject("roottrace", roottrace);
        }
        log.debug("");
        return model;
    }

    //method responsible for getting the HTML stack trace
    private String getHTMLStackTrace(String[] stack) {
        StringBuffer result = new StringBuffer();
        for (String frame : stack) {
            //**CHANGE THIS TO YOUR PACKAGE NAME**
            if (frame.contains("com.teksystems")) {
                result.append(" &nbsp; &nbsp; &nbsp;" + frame.trim().substring(3) + "<br>\n");
            } else if (frame.contains("Caused by:")) {
                result.append("Caused By:<br>");
            }
        }
        return result.toString();
    }

    //method responsible for getting the requested URL that produced an error
    public String getRequestURL(HttpServletRequest request) {
        String result = request.getRequestURL().toString();
        if (request.getQueryString() != null) {
            result = result + "?" + request.getQueryString();
        }

        return result;
    }

}