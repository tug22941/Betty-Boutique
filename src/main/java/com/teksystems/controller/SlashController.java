package com.teksystems.controller;

import com.teksystems.database.dao.ProductDAO;
import com.teksystems.database.entity.Product;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

//controller class responsible for handling site navigation to main pages
@Slf4j
@Controller
public class SlashController {

    //----------Autowire Necessary DAOs--------------//
    @Autowired
    ProductDAO productDAO;
    //---

    //method responsible for displaying the index page
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index() {

        log.info("In the INDEX controller method:");
        ModelAndView response = new ModelAndView("index");
        log.debug("");
        return response;
    }

    //method responsible for displaying the about page
    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public ModelAndView about() {

        log.debug("In the ABOUT controller method:");
        ModelAndView response = new ModelAndView("about");
        log.debug("");
        return response;
    }

    //method responsible for displaying the login page
    @GetMapping(value = "/login")
    public ModelAndView login(HttpSession session) {

        log.debug("In the LOGIN controller method:");
        ModelAndView response = new ModelAndView("login/login");
        log.debug("");
        return response;
    }

    //method responsible for displaying the search page: with list of products matching the user search
    @GetMapping("/search")
    public ModelAndView search(@RequestParam(required = false) String search){

        //set the response model and view object
        log.info("In the SEARCH controller method:");
        ModelAndView response = new ModelAndView("search");

        //add search string value to response object:
        response.addObject("search",search);

        //create product list object
        // assign list of products matching user search to products object: add products object to response object
        List<Product> products = new ArrayList<>();
        products = productDAO.findProductsBySearch(search);
        response.addObject("productsList",products);

        log.debug("");
        return response;
    }

}
