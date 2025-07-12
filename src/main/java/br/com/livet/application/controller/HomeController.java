package br.com.livet.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("usuario", "Admin");
        return "login";  // Vai renderizar o arquivo login.html do templates
    }
}


