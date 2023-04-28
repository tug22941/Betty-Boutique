package com.teksystems.controller;

import com.teksystems.database.dao.OrderDAO;
import com.teksystems.database.dao.OrderProductDAO;
import com.teksystems.database.dao.ProductDAO;
import com.teksystems.database.entity.Order;
import com.teksystems.database.entity.OrderProduct;
import com.teksystems.database.entity.Product;
import com.teksystems.database.entity.User;
import com.teksystems.formbeans.ProductFormBean;
import com.teksystems.security.AuthenticatedUserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.List;

//controller class responsible for handling product actions
@Slf4j
@Controller
@RequestMapping("/product")
public class ProductController {

    //----------Autowire Necessary DAOs--------------//
    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private OrderProductDAO orderProductDAO;

    @Autowired
    AuthenticatedUserService authenticatedUserService;
    //---

    //method responsible for displaying the create product page: with a list of products
    @GetMapping("/create")
    public ModelAndView create(){

        log.debug("In the product CREATE controller method:");
        ModelAndView response = new ModelAndView("product/create");

        //load list of all products : and add products object to the response object
        List<Product> products = productDAO.getAllProducts();
        response.addObject("productsList", products );

        log.debug("");
        return response;
    }

    //method responsible for validating product creation form: creating a new product: and displaying updated products list
    @PostMapping("/createSubmit")
    public ModelAndView createSubmit(@Valid ProductFormBean form,
                                     BindingResult bindingResult) throws IOException {

        log.debug("In the product CREATE SUBMIT controller method:");
        log.debug(form.toString());
        ModelAndView response = new ModelAndView("product/create");

        //--------- 1: VALIDATE PRODUCT CREATION FORM --------------//

        //check for errors in the product form 'binding results'
        //if error(s) found, log error: add form and binding result objects to response object: return response
        if ( bindingResult.hasErrors() ) {
            for ( FieldError error : bindingResult.getFieldErrors()) {
                log.debug("Validation Error on field: " + error.getField());
                log.debug("Validation Error Message: " + error.getDefaultMessage());
            }
            response.addObject("form",form);
            response.addObject("bindingResult",bindingResult);

            //load list of all products : and add products object to the response object
            List<Product> products = productDAO.getAllProducts();
            response.addObject("productsList", products );

            return response;
        }else{
            //if error(s) not found: add success boolean to response object
            response.addObject("success", true);
        }

        //--------- 2: CREATE NEW PRODUCT -(OR)- EDIT EXISTING PRODUCT  --------------//

        //create new product object
        Product product = new Product();

        //if product form has product id: PROCESS BECOMES EDIT EXISTING PRODUCT
        // assign product matching that id to new object
        if(form.getId() != null){
            product = productDAO.findById(form.getId());
        }

        // set form 'ImageUrl' value to Image folder relative location
        String imageUrl = "/pub/images/" + form.getPicture().getOriginalFilename();
        form.setImageUrl(imageUrl);

        //set product properties to form values
        product.setName(form.getName());
        product.setDescription(form.getDescription());
        product.setImageUrl(imageUrl);
        product.setPrice(form.getPrice());
        product.setProductType(form.getProductType());

        //save created product into database: add form object to response object
        productDAO.save(product);
        response.addObject("form",form);

        //target the save location of the form's 'picture' file to the images folder
        File target = new File("./src/main/webapp"+imageUrl);
        log.debug(target.getAbsolutePath());

        //use the convenience method provided by commons-io library
        //read the file upload input stream : and write it to the target filesystem
        FileUtils.copyInputStreamToFile(form.getPicture().getInputStream(), target);

        //--------- 3: DISPLAY UPDATED PRODUCT LIST  --------------//

        //load list of all products : and add products object to the response object
        List<Product> products = productDAO.getAllProducts();
        response.addObject("productsList", products );

        log.debug("");
        return response;
    }

    //method responsible for displaying the edit form of an existing product : and displaying products list
    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable Integer id){

        log.debug("In EDIT controller method:");
        ModelAndView response = new ModelAndView("product/create");

        //--------- 1: DISPLAY PRODUCTS LIST  --------------//

        //load list of all products : and add products object to the response object
        List<Product> products = productDAO.getAllProducts();
        response.addObject("productsList", products );


        //--------- 2: SET THE FORM FIELD VALUES  --------------//

        //create new product object : assign value of product with 'id' to product object
        Product product = productDAO.findById(id);

        //create new product form bean
        ProductFormBean form = new ProductFormBean();
        // set the product form properties: add product object to response object
        form.setId(product.getId());
        form.setName(product.getName());
        form.setDescription((product.getDescription()));
        form.setImageUrl(product.getImageUrl());
        form.setPrice(product.getPrice());
        form.setProductType(product.getProductType());
        response.addObject("form",form);

        log.debug("");
        return response;
    }

    //method responsible for displaying product information
    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable Integer id){

        log.debug("In the product DETAIL controller method:");
        ModelAndView response = new ModelAndView("product/detail");

        //load product matching id: assign product to new product object: add product object to response object
        Product product = productDAO.findById(id);
        response.addObject("product",product);

        //load user from authenticated user service
        User user = authenticatedUserService.loadCurrentUser();

        // if user has an open order: assign open order to order object: add order object to response object
        Order order = new Order();
        if(orderDAO.findOpenOrder(user.getId()) != null){
            order = orderDAO.findOpenOrder(user.getId());
            response.addObject("orderId", order.getId());
        }

        // check for order-product record with matching product id and order id
        OrderProduct orderProduct = orderProductDAO.findOrderProductById(order.getId(), id);

        //if matching record found: add record's 'quantity' field-value to response object
        if(orderProduct != null){
            log.debug("Order Product found - ID: " + orderProduct.getId());
            response.addObject("quantity", orderProduct.getQuantity());
        }

        log.debug("");
        return response;
    }


}
