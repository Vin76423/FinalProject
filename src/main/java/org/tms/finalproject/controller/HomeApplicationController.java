package org.tms.finalproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.tms.finalproject.entity.User;
import org.tms.finalproject.repository.UserRepository;

@Controller
@RequestMapping(path = "/home")
public class HomeApplicationController {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public HomeApplicationController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public ModelAndView getHomePage(ModelAndView modelAndView) {
        modelAndView.setViewName("home");
        return modelAndView;
    }

    @GetMapping(path = "/reg")
    public ModelAndView getRegistrationForm(ModelAndView modelAndView) {
        modelAndView.addObject("user", new User());
        modelAndView.setViewName("reg");
        return modelAndView;
    }

    @PostMapping(path = "reg")
    public ModelAndView completeRegistration(ModelAndView modelAndView,
                                             User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
        modelAndView.setViewName("home");
        return modelAndView;
    }
}
