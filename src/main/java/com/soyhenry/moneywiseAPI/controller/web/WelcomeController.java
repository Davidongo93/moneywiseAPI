package com.soyhenry.moneywiseAPI.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {

    @GetMapping("/")
    public String welcome(Model model) {
        return "welcome"; // Este nombre corresponde al nombre de tu archivo HTML en src/main/resources/templates
    }
}
