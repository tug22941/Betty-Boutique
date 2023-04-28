package com.teksystems.controller;

import com.teksystems.database.dao.UserDAO;
import com.teksystems.database.dao.UserRoleDAO;
import com.teksystems.database.entity.User;
import com.teksystems.database.entity.UserRole;
import com.teksystems.formbeans.UserFormBean;
import com.teksystems.security.AuthenticatedUserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

//controller class responsible for handling user actions

@Slf4j
@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {

    //----------Autowire Necessary DAOs--------------//
    @Autowired
    AuthenticatedUserService authenticatedUserService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRoleDAO userRoleDAO;

    @Autowired
    private UserDAO userDAO;
    //---

    //method responsible for displaying the create user page: with a list of users
    @GetMapping("/create")
    public ModelAndView create(){

        log.debug("In the user CREATE controller method:");
        ModelAndView response = new ModelAndView("user/create");

        //load list of all users : and add user object to the response object
        List<User> users = userDAO.getAllUsers();
        response.addObject("usersList", users );

        log.debug("");
        return response;
    }

    //method responsible for validating user creation form: creating a new user: and displaying updated user list
    @GetMapping("/createSubmit")
    public ModelAndView createSubmit(@Valid UserFormBean form, BindingResult bindingResult){

        log.debug("In the user CREATE SUBMIT controller method:");
        log.debug(form.toString());
        ModelAndView response = new ModelAndView("user/create");

        //--------- 1: VALIDATE PRODUCT CREATION FORM --------------//

        //validate matching form values of 'password' and 'confirmPassword' fields
        if (StringUtils.equals(form.getPassword(), form.getConfirmPassword()) == false){
            bindingResult.rejectValue("confirmPassword", "error.confirmPassword", "Passwords do not match");
        }

        //check for errors in the user form 'binding results'
        //if error found display debug notification, return to form without database upload
        if ( bindingResult.hasErrors() ) {
            for ( FieldError error : bindingResult.getFieldErrors()) {
                log.debug("Validation Error on field : " + error.getField() + " with message : " + error.getDefaultMessage());
            }
            response.addObject("form",form);
            response.addObject("bindingResult",bindingResult);

            //load list of all users : and add user object to the response object
            List<User> users = userDAO.getAllUsers();
            response.addObject("usersList", users );

            return response;
        }else {
            //if error(s) not found: add success boolean to response object
            response.addObject("success", true);
        }

        //--------- 2: CREATE NEW USER -(OR)- EDIT EXISTING USER  --------------//

        //create new user object
        User user = new User();

        //if user form has product id: PROCESS BECOMES EDIT EXISTING USER
        // assign user matching that id to new object
        if(form.getId() != null){
            user = userDAO.findById(form.getId());
        }

        //set user properties to form values
        user.setFirstName(form.getFirstName());
        user.setLastName(form.getLastName());
        user.setEmail(form.getEmail().toLowerCase());

        //b-crypt password form value using spring security: set user property to encrypted value
        String encryptedPassword = passwordEncoder.encode(form.getPassword());
        user.setPassword(encryptedPassword);

        //save created product into database: add form object to response object
        userDAO.save(user);

        //load list of all users : and add user object to the response object
        List<User> users = userDAO.getAllUsers();
        response.addObject("usersList", users );

        // authenticate created user : sign in as created user
        //authenticatedUserService.changeLoggedInUsername(session, form.getEmail(), form.getPassword());

        //redirect to new page
        //response.setViewName("redirect:/index");

        log.debug("");
        return response;
    }

    //method responsible for displaying the edit form of an existing user : and displaying users list
    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable Integer id){

        log.debug("In user EDIT controller method:");
        ModelAndView response = new ModelAndView("user/create");

        //--------- 1: DISPLAY USERS LIST  --------------//

        //load list of all users : and add user object to the response object
        List<User> users = userDAO.getAllUsers();
        response.addObject("usersList", users );

        //--------- 2: SET THE FORM FIELD VALUES  --------------//

        //create new user object : assign value of user with 'id' to user object
        User user = userDAO.findById(id);

        //create new user form bean
        UserFormBean form = new UserFormBean();
        // set the user form properties: add user object to response object
        form.setId(user.getId());
        form.setFirstName(user.getFirstName());
        form.setLastName(user.getLastName());
        form.setEmail(user.getEmail());
        form.setPassword(user.getPassword());
        response.addObject("form",form);

        log.debug("");
        return response;
    }

    //method responsible for displaying user information
    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable Integer id){
        log.debug("In the user DETAIL controller method:");
        ModelAndView response = new ModelAndView("user/detail");

        User user = userDAO.findById(id);
        response.addObject("user",user);

        log.debug("");
        return response;
    }

    //method responsible for deleting user record: and displaying updated user list
    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable Integer id){

        log.debug("In the user DELETE controller method:");
        ModelAndView response = new ModelAndView("user/create");

        List<UserRole> userRoles = userRoleDAO.findByUserId(id);
        UserRole userRole = userRoles.get(0);
        //use persistent entity manager object 'remove' method to delete userRole object;

        User user = userDAO.findById(id);
        //use persistent entity manager object 'remove' method to delete user object;


        //load list of all users : and add user object to the response object
        List<User> users = userDAO.getAllUsers();
        response.addObject("usersList", users );

        log.debug("");
        return response;
    }

}
