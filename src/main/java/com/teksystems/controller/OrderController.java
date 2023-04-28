package com.teksystems.controller;

import com.teksystems.database.dao.OrderDAO;
import com.teksystems.database.dao.OrderProductDAO;
import com.teksystems.database.dao.ProductDAO;
import com.teksystems.database.dao.UserDAO;
import com.teksystems.database.entity.OrderProduct;
import com.teksystems.database.entity.Order;
import com.teksystems.database.entity.Product;
import com.teksystems.database.entity.User;
import com.teksystems.formbeans.OrderFormBean;
import com.teksystems.formbeans.OrderProductFormBean;
import com.teksystems.security.AuthenticatedUserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Date;
import java.util.List;
import java.util.Map;

//controller class responsible for handling user order(s)
@Slf4j
@Controller
@RequestMapping("/order")
public class OrderController {
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
    @GetMapping("/cart")
    public ModelAndView cart() {

        log.debug("In the CART PAGE controller method:");
        ModelAndView response = new ModelAndView("order/cart");

        //load user from authenticated user service
        User user = authenticatedUserService.loadCurrentUser();

        // if user does not have an open order: return the response object
        Order order = new Order();

        if(orderDAO.findOpenOrder(user.getId()) == null){
            log.debug("");
            return response;
        }

        //send the order id as a request parameter to used on the cart page
        order = orderDAO.findOpenOrder(user.getId());
        Integer orderId = order.getId();
        response.addObject("orderId", orderId);

        //add list of ordered products map to the response object
        List<Map<String,Object>> orderProducts = orderProductDAO.findByCartByOrderId(orderId);
        response.addObject("orderProducts",orderProducts);


        //loop through the ordered products list : and total the price of each product
        Double doubleTotal = 0.00;
        Integer quantity = 0;
        for( Map product: orderProducts){

            BigDecimal bd = (BigDecimal)product.get("price");
            doubleTotal += bd.doubleValue();
            quantity += (Integer) product.get("quantity");
            doubleTotal =  doubleTotal * quantity;
        }
        response.addObject("quantity", quantity);

        BigDecimal orderTotal = new BigDecimal(doubleTotal.toString());
        MathContext m = new MathContext(4);
        orderTotal = orderTotal.round(m);
        response.addObject("orderTotal", orderTotal );

        log.debug(orderProducts + "");

        log.debug("");
        return response;
    }

    //method responsible for saving quantity ordered of product to user cart: and re-displaying the product detail page with saved quantity value
    @GetMapping("/addToCart/{id}")
    public ModelAndView addToCart(@PathVariable Integer id,
                                  @RequestParam(required = false) Integer orderId,
                                  @RequestParam(required = false) Integer quantity,
                                  @Valid OrderProductFormBean form,
                                  BindingResult bindingResult){

        log.info("In the ADD TO CART controller method:");
        ModelAndView response = new ModelAndView("product/detail");
        log.info(form.toString());

        //add quantity integer value to return object
        response.addObject("quantity", quantity);

        //load product object with matching 'id': add product object to response object
        Product product = productDAO.findById(id);
        response.addObject("product",product);


        //check for error in the detail form - quantity - 'binding result'
        //if error found display debug notification, return to form without database upload
        if ( bindingResult.hasErrors() ) {
            for ( FieldError error : bindingResult.getFieldErrors()) {
                log.debug("Validation Error on field: " + error.getField());
                log.debug("Validation Error Message: " + error.getDefaultMessage());
            }
            response.addObject("form",form);
            response.addObject("bindingResult",bindingResult);
            return response;
        }

        // load user from authenticated user service
        User user = authenticatedUserService.loadCurrentUser();
        log.debug("Current User:" + user.getEmail());

        //create new order object: and set -(or)- load order
        Order order = new Order();

        //if order ID is already loaded in: return that order to the page
        if(orderId != null){
            order = orderDAO.findById(orderId);
            response.addObject("orderId", orderId);
            // if user has an open order: load that order and return it to the page
        }else if(orderDAO.findOpenOrder(user.getId()) != null){
            order = orderDAO.findOpenOrder(user.getId());

            response.addObject("orderId", order.getId());
        }
        //if user does not have a preloaded or open order: create a new order and return it to the page
        else {
            order.setUser(user);
            order.setOrderStatus("Open");
            orderDAO.save(order);
            response.addObject("orderId", order.getId());

        }

        // set or load product in the order (order-products)

        // check for order-product record with matching product id and order id
        OrderProduct orderProduct = new OrderProduct();

        //if matching record found: update record's product-quantity field-value with form quantity value
        if(orderProductDAO.findOrderProductById(order.getId(), id) != null){
            orderProduct = orderProductDAO.findOrderProductById(order.getId(), id);
            log.debug("Order Product found - ID #: " + orderProduct.getId());

            if(orderProduct.getQuantity() == quantity){
                log.debug("Product Quantity is already set to " + quantity);
                return response;
            }else{
                orderProduct.setQuantity(form.getQuantity());
            }
        }
        //if no matching record found: create a new record with product-quantity field-value set to form quantity value
        else{
            log.debug("no Order Product found");
            orderProduct.setOrder(order);
            orderProduct.setProduct(product);
            orderProduct.setQuantity(quantity);
        }

        //save created - (or)- modified order-product record into database
        orderProductDAO.save(orderProduct);

        response.addObject("cartUpdated", true);


        log.debug("ORDER PRODUCT UPDATED!");
        log.debug("Order #: " + orderProduct.getId() +
                " | Product #: " + orderProduct.getProduct().getId() +
                " | Quantity #: " + orderProduct.getQuantity());
        log.debug("");

        return response;
    }

    //method responsible for displaying the cart page: with user info: and order details
    @GetMapping("/deleteFromCart/{orderProductId}")
    public ModelAndView deleteFromCart(@PathVariable Integer orderProductId) {

        log.debug("In the DELETE FROM CART controller method:");
        ModelAndView response = new ModelAndView("order/cart");

        OrderProduct orderProduct = orderProductDAO.findById(orderProductId);
        orderProductDAO.delete(orderProduct);

        log.debug("");

        //redirect response to new view (page)
        response.setViewName("redirect:/order/cart");
        return response;
    }

    @GetMapping("/placeOrder/{orderId}/{orderTotal}")
    public ModelAndView placeOrder(@PathVariable Integer orderId,
                                   @PathVariable Double orderTotal,
                                   @Valid OrderFormBean form, BindingResult bindingResult) {

        log.debug("In the PLACE ORDER controller method:");
        ModelAndView response = new ModelAndView("order/cart");

        //load user from authenticated user service
        User user = authenticatedUserService.loadCurrentUser();

        //check for errors in the user form 'binding results'
        //if error found display debug notification, return to form without database upload
        if ( bindingResult.hasErrors() ) {
            for ( FieldError error : bindingResult.getFieldErrors()) {
                log.debug("Validation Error on field : " + error.getField() + " with message : " + error.getDefaultMessage());
            }
            response.addObject("form",form);
            response.addObject("bindingResult",bindingResult);

            //repeat logic from view cart

            // if user does not have an open order: return the response object
            Order order = new Order();

            if(orderDAO.findOpenOrder(user.getId()) == null){
                log.debug("");
                return response;
            }

            //send the order id as a request parameter to used on the cart page
            order = orderDAO.findOpenOrder(user.getId());
            response.addObject("orderId", orderId);

            //add list of ordered products map to the response object
            List<Map<String,Object>> orderProducts = orderProductDAO.findByCartByOrderId(orderId);
            response.addObject("orderProducts",orderProducts);

            //loop through the ordered products list : and total the price of each product
            Double doubleTotal = 0.00;
            Integer quantity = 0;
            for( Map product: orderProducts){

                BigDecimal bd = (BigDecimal)product.get("price");
                doubleTotal += bd.doubleValue();
                quantity = (Integer) product.get("quantity");
                doubleTotal =  doubleTotal * quantity;
            }
            response.addObject("quantity", quantity);


            response.addObject("orderTotal", orderTotal );

            return response;
        }else {

            Order order = orderDAO.findById(orderId);

            order.setUser(user);
            order.setReceiver(form.getFullName());
            order.setOrderStatus("Shipped");
            order.setCardNumber(form.getCardNumber());
            order.setCountry(form.getCountry());
            order.setCity(form.getCity());
            order.setState(form.getState());
            order.setZipcode(form.getZipcode());
            order.setAddressLine1(form.getAddressLine1());
            if(form.getAddressLine2() != null){
                order.setAddressLine2(form.getAddressLine2());
            }
            order.setTotal(orderTotal);
            order.setShippingDate(new Date());
            orderDAO.save(order);

            response.addObject("orderShipped", order.getOrderStatus());

            log.debug("");

            //redirect response to new view (page)
            response.setViewName("redirect:/index");
            return response;
        }
    }

}
