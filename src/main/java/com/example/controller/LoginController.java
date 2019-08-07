package com.example.controller;

import javax.servlet.http.HttpSession;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.domain.Login;
import com.example.service.UserService;

@Controller
public class LoginController {
	
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String initForm(Model model) {
        Login login = new Login();

        model.addAttribute("login", login);
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String submitForm(@Valid Login login, BindingResult result, HttpSession session) {

        if (result.hasErrors()) {
            return "login";
        }

        if (!userService.isValidUser(login)) {
            return "login";
        }

        if (userService.isValidAdmin(login)) {
            session.setAttribute("user", userService.findUserByLogin(login));
            return "forward:/admin/home";
        }

        session.setAttribute("user", userService.findUserByLogin(login));
        return "forward:/user/home";
    }
    
    @RequestMapping(value = "/logout")
    public String logout(HttpSession session, Model model) {
        session.invalidate();
        return "redirect:/";
    }
    
}
