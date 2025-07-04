package br.com.livet.application.controller.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<String> login() {
        // Implement login logic here
        System.out.println("Login attempt for user: ");
        return status(OK).body("Login successful");
    }

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password) {
        // Implement registration logic here
        return "Registration successful for user: " + username;
    }
}