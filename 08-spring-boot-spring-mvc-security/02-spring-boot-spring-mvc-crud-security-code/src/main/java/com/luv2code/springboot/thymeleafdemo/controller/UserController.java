package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.dao.RoleRepository;
import com.luv2code.springboot.thymeleafdemo.entity.Role;
import com.luv2code.springboot.thymeleafdemo.entity.User;
import com.luv2code.springboot.thymeleafdemo.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

        // Get the users and roles from the database
        List<User> users = userService.findAll();
        List<Role> allRoles = roleRepository.findAll();

        // Add to the Spring model
        theModel.addAttribute("users", users);
        theModel.addAttribute("roles", allRoles);

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
                           BindingResult theBindingResult,
                           HttpSession session, Model theModel) {

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

        // Fetch all roles to populate the dropdown or checkboxes
        List<Role> allRoles = roleRepository.findAll();

        // Set user and roles in the model to pre-populate the form
        theModel.addAttribute("User", user);
        theModel.addAttribute("roles", allRoles);

        // Send over to our form
        return "/users/updateUser";
    }

    @PostMapping("/updateUser")
    public String update(@Valid @ModelAttribute("User") User theUser,
                         BindingResult theBindingResult,
                         @RequestParam(value = "roles", required = false) List<Integer> roleIds,
                         Model theModel) {

        // Check for validation errors
        if (theBindingResult.hasErrors()) {
            logger.warning("Binding errors: " + theBindingResult.getAllErrors());
            // Re-fetch roles to repopulate the form
            List<Role> allRoles = roleRepository.findAll();
            theModel.addAttribute("roles", allRoles);
            return "/users/updateUser";
        }

        // Retrieve existing user from the database
        User existingUser = userService.findById(theUser.getId());
        if (existingUser == null) {
            throw new RuntimeException("User not found for ID: " + theUser.getId());
        }

        // Preserve existing password if not provided
        if (theUser.getPassword() == null || theUser.getPassword().isEmpty()) {
            theUser.setPassword(existingUser.getPassword());
        }

        // Fetch selected roles and assign to the user
        if (roleIds != null && !roleIds.isEmpty()) {
            List<Role> updatedRoles = roleRepository.findAllById(roleIds);
            theUser.setRoles(updatedRoles);
        }

        // Save the updated user (the ID will ensure it's an update)
        userService.save(theUser);

        // Redirect to the list of users
        return "redirect:/users";
    }

    @GetMapping("/profile")
    public String viewProfile(Model theModel, HttpSession session) {

        // Get the logged-in user from the session or authentication context
        User existingUser = (User) session.getAttribute("user");
        // And fetch the user from the database to ensure roles are loaded
        User loggedInUser = userService.findById(existingUser.getId());

        if (loggedInUser == null) {
            return "redirect:/login"; // Redirect to login if not authenticated
        }

        // Fetch roles for the dropdown/checkboxes (if necessary)
        List<Role> allRoles = roleRepository.findAll();

        // Set user and roles in the model
        theModel.addAttribute("User", loggedInUser);
        theModel.addAttribute("roles", allRoles);

        return "/users/profile"; // New profile-specific Thymeleaf template
    }

    @PostMapping("/profile")
    public String updateProfile(@Valid @ModelAttribute("User") User theUser,
                                BindingResult theBindingResult,
                                HttpSession session, Model theModel) {

        if (theBindingResult.hasErrors()) {
            logger.warning("Validation errors while updating profile: " + theBindingResult.getAllErrors());

            // Re-fetch roles to repopulate the form
            List<Role> allRoles = roleRepository.findAll();
            theModel.addAttribute("roles", allRoles);
            return "/users/profile"; // Reload the profile page on errors
        }

        // Retrieve existing user from the database
        User existingUser = userService.findById(theUser.getId());
        if (existingUser == null) {
            throw new RuntimeException("User not found for ID: " + theUser.getId());
        }

        // Preserve existing password if not provided
        if (theUser.getPassword() == null || theUser.getPassword().isEmpty()) {
            theUser.setPassword(existingUser.getPassword());
        }

        // Update only the logged-in user's data
        userService.save(theUser);

        // Re-authenticate the user to reflect new roles
        UserDetails userDetails = userService.loadUserByUsername(theUser.getUserName());
        UsernamePasswordAuthenticationToken newAuth =
                new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(newAuth);

        // Update the session with the modified user object
        session.setAttribute("user", theUser);

        return "redirect:/users";
    }

    // Mapping for "/delete"
    @GetMapping("/delete")
    public String deleteUser(@RequestParam("userId") Long userId, HttpSession session) {

        // Delete the user
        userService.deleteById(userId);

        // Get the logged-in user from the session or authentication context
        User existingUser = (User) session.getAttribute("user");

        // Re-authenticate the user to reflect new roles
        UserDetails userDetails = userService.loadUserByUsername(existingUser.getUserName());
        UsernamePasswordAuthenticationToken newAuth =
                new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(newAuth);

        // Update the session with the modified user object
        session.setAttribute("user", existingUser);

        // Redirect to the /users/list
        return "redirect:/users";
    }
}
