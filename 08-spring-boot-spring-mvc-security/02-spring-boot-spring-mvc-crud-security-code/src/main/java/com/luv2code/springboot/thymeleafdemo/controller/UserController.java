package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.dao.RoleRepository;
import com.luv2code.springboot.thymeleafdemo.entity.Role;
import com.luv2code.springboot.thymeleafdemo.entity.User;
import com.luv2code.springboot.thymeleafdemo.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

@Controller
public class UserController {

    private final UserService userService;
    private final RoleRepository roleRepository;  // Add this field
    private final Logger logger = Logger.getLogger(getClass().getName());

    public UserController(UserService theUserService, RoleRepository theRoleRepository) {
        userService = theUserService;
        roleRepository = theRoleRepository;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    // Mapping for "/users"
    @GetMapping("/users")
    public String listUsers(Model theModel) {

        // Get the users from the database
        List<User> users = userService.findAll();

        // Add to the Spring model
        theModel.addAttribute("users", users);

        return "/users/list";
    }

    @GetMapping("/signup")
    public String signup(Model theModel) {

        User user = new User();
        Role employeeRole = roleRepository.findByName("ROLE_EMPLOYEE");
        user.getRoles().add(employeeRole); // Add default role
        theModel.addAttribute("User", user);
        theModel.addAttribute("roles", roleRepository.findAll());  // Fetch roles here
        return "/users/signup";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("User") User theUser,
                           BindingResult theBindingResult, HttpSession session, Model theModel) {

        String userName = theUser.getUserName();
        logger.info("Processing registration form for: " + userName);

        if (theBindingResult.hasErrors()) {
            logger.warning("Form validation errors: " + theBindingResult.getAllErrors());
            return "/users/signup";
        }

        User existing = userService.findByUserName(userName);
        if (existing != null) {
            theModel.addAttribute("User", new User());
            theModel.addAttribute("registrationError", "User name already exists.");
            logger.warning("User name already exists.");
            return "/users/signup";
        }

        userService.save(theUser);
        logger.info("Successfully created user: " + userName);
        session.setAttribute("user", theUser);

        return "/users/registration-confirmation";
    }

    // Mapping for "/updateUser"
    @GetMapping("/updateUser")
    public String updateUser(@RequestParam("userId") Long userId, Model theModel) {

        // Get the user from the service
        User user = userService.findById(userId);

        // Set user in the model to pre-populate the form
        theModel.addAttribute("User", user);

        // Send over to our form
        return "/users/updateUser";
    }

    @PostMapping("/updateUser")
    public String update(@Valid @ModelAttribute("User") User theUser,
                         BindingResult theBindingResult, Model theModel) {

        // Check for validation errors
        if (theBindingResult.hasErrors()) {
            logger.warning("Binding errors: " + theBindingResult.getAllErrors());
            return "/users/updateUser";
        }

        // Save the updated user (the ID will ensure it's an update)
        userService.save(theUser);

        // Redirect to the list of users
        return "redirect:/users";
    }

    // Mapping for "/delete"
    @GetMapping("/delete")
    public String deleteUser(@RequestParam("userId") Long userId) {

        // Delete the user
        userService.deleteById(userId);

        // Redirect to the /users/list
        return "redirect:/users";
    }
}
