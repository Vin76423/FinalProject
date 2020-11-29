package org.tms.finalproject.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.tms.finalproject.service.database.UserService;

@Controller
@RequestMapping(path = "/account")
public class UserAccountApplicationController {

    private UserService userService;

    public UserAccountApplicationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/get-profile")
    public ModelAndView getUserProfile(Authentication authentication,
                                       ModelAndView modelAndView) {
        String username = ((User) authentication.getPrincipal()).getUsername();
        org.tms.finalproject.entity.User user = userService.getUserByLogin(username);
        modelAndView.addObject("profile", user);
        modelAndView.setViewName("account");
        return modelAndView;
    }
}
