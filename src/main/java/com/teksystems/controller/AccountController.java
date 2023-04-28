package com.teksystems.controller;

import com.teksystems.database.dao.OrderDAO;
import com.teksystems.database.dao.ProductDAO;
import com.teksystems.database.dao.UserDAO;
import com.teksystems.database.dao.OrderProductDAO;
import com.teksystems.database.entity.Order;
import com.teksystems.database.entity.User;
import com.teksystems.security.AuthenticatedUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Slf4j
@Controller
@RequestMapping("/account")
public class AccountController {
    //----------Autowire Necessary DAOs--------------//
    @Autowired
    AuthenticatedUserService authenticatedUserService;

    @Autowired
    UserDAO userDAO;

    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private OrderProductDAO orderProductDAO;

    @Autowired
    private ProductDAO productDAO;
    //---

    //method responsible for displaying the cart page: with user info: and order details
    @GetMapping("/accountInformation")
    public ModelAndView userInformation() {

        log.debug("In USER INFORMATION controller method:");
        ModelAndView response = new ModelAndView("account");

        //load user from authenticated user service
        User user = authenticatedUserService.loadCurrentUser();
        String fullName = user.getFirstName() + " " + user.getLastName();
        response.addObject("fullName", fullName);

        // if user does not have an open order: return the response object
        List <Order> ordersList = new ArrayList<>();
        if(orderDAO.findPastOrders(user.getId()) == null){
            log.debug("");
            return response;
        }else{
            //get orderId of all closed orders
            ordersList = orderDAO.findPastOrders(user.getId());

            //stream
            ordersList.stream().forEach(ol -> {
                log.debug(ol.toString());
            });

            List<List<Map<String,Object>>> fullList = new ArrayList<>();

            //for every closed order
            for(Order order: ordersList){
                //get the List of items in that order
                List<Map<String,Object>> orderInfoList = orderDAO.findOrderInformation(order.getId());

                //add it to new list?
                fullList.add(orderInfoList);
            }
            response.addObject("fullList",fullList);
        }

        return response;

    }
}
