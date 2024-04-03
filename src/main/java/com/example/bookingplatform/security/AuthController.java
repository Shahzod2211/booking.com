package com.example.bookingplatform.security;

import com.example.bookingplatform.user.UserService;
import com.example.bookingplatform.user.dto.UserSignUpDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;

    @GetMapping("/sign-up")
    public String goSignUp() {
        return "security/sign-up";
    }

    @GetMapping("/sign-in")
    public String goSignIn() {
        return "security/sign-in";
    }

    @PostMapping("/sign-up")
    public String signUpUser(@ModelAttribute UserSignUpDTO userSignUpDTO) {
        userService.create(userSignUpDTO);
        return "/index";
    }
}
