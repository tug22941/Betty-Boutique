package com.teksystems.controller;

import com.teksystems.database.dao.UserDAO;
import com.teksystems.database.entity.User;
import com.teksystems.formbeans.UserFormBean;
import com.teksystems.security.AuthenticatedUserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

//controller class responsible for handling user login/signup
@Slf4j
@Controller
@RequestMapping("/login")
public class LoginController {

    //----------Autowire Necessary DAOs--------------//
    @Autowired
    UserDAO userDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticatedUserService authenticatedUserService;
    //---

    //method responsible for displaying the login page
    @GetMapping("/loginPage")
    public ModelAndView loginPage() {

        log.debug("In the LOGIN PAGE controller method:");
        ModelAndView response = new ModelAndView("login/login");
        log.debug("");
        return response;
    }

    //method responsible for displaying the signup page
    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public ModelAndView signup() {

        log.debug("In the SIGNUP controller method");
        ModelAndView response = new ModelAndView("login/signup");
        log.debug("");
        return response;
    }

    //method responsible for validating user signup form: creating new user: and signing in as created user
    @PostMapping("/signupSubmit")
    public ModelAndView signupSubmit(HttpSession session, @Valid UserFormBean form,
                                     BindingResult bindingResult){

        //set the response model and view object
        log.debug("in the SIGNUP SUBMIT controller method:");
        log.debug(form.toString());
        ModelAndView response = new ModelAndView("login/signup");

        //log the user form: add the user form to the response object
        log.debug(form.toString());
        response.addObject("form", form);

        //--------- 1: VALIDATE USER SIGNUP FORM --------------//

        //validate matching form values of 'password' and 'confirmPassword' fields
        if (StringUtils.equals(form.getPassword(), form.getConfirmPassword()) == false){
            bindingResult.rejectValue("confirmPassword", "error.confirmPassword", "Passwords do not match");
        }

        //check for errors in the user form 'binding results'
        //if error(s) found, log error: add form and binding result objects to response object: return response
        if ( bindingResult.hasErrors() ) {
            for ( FieldError error : bindingResult.getFieldErrors()) {
                log.debug("Validation Error on field: " + error.getField());
                log.debug("Validation Error Message: " + error.getDefaultMessage());
            }
            response.addObject("form",form);
            response.addObject("bindingResult",bindingResult);
            return response;
        }else{
            //if error(s) not found: add success boolean to response object
            response.addObject("success", true);
        }

        //--------- 2: CREATE NEW USER  --------------//

        //** duplicate username check performed by validation package**

        //create new user : set new user properties to form values
        User user = new User();
        user.setFirstName(form.getFirstName());
        user.setLastName(form.getLastName());
        user.setEmail(form.getEmail());

        //b-crypt password form value using spring security: set user property to encrypted value
        String encryptedPassword = passwordEncoder.encode(form.getPassword());
        user.setPassword(encryptedPassword);

        userDAO.save(user); //save created user into database

        //--------- 3: SIGN IN AS CREATED USER  --------------//

        // authenticate created user : sign in as created user
        authenticatedUserService.changeLoggedInUsername(session, form.getEmail(), form.getPassword());

        //redirect response to new view (page)
        response.setViewName("redirect:/index");

        log.debug("");
        return response;
    }

}