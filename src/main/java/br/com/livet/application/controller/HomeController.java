package br.com.livet.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("usuario", "Admin");
        return "login";  // Vai renderizar o arquivo login.html do templates
    }
}


