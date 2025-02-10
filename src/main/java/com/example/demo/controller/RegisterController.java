package com.example.demo.controller;

import com.example.demo.models.RegistrationForm;
import com.example.demo.models.Roles;
import com.example.demo.models.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Collections;

@Controller
public class RegisterController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String loginView() {
        return "login";
    }

    @GetMapping("/registration")
    private String registerView(Model model)
    {
        model.addAttribute("registrationForm", new RegistrationForm());
        return "registration";
    }

    @PostMapping("/register")
    private String register(@Valid @ModelAttribute("registrationForm") RegistrationForm registrationForm,
                            BindingResult bindingResult, Model model) {
        System.out.println("Регистрация началась");

        if (bindingResult.hasErrors()) {
            System.out.println("Ошибка валидации: " + bindingResult.getAllErrors());
            return "registration";
        }

        if (!registrationForm.getPassword().equals(registrationForm.getConfirmPassword())) {
            model.addAttribute("error", "Пароли не совпадают");
            System.out.println("Пароли не совпадают");
            return "registration";
        }

        User existingUser = userRepository.findByUsername(registrationForm.getUsername());
        if (existingUser != null) {
            model.addAttribute("error", "Пользователь с таким логином уже существует");
            System.out.println("Пользователь уже существует: " + registrationForm.getUsername());
            return "registration";
        }

        User user = new User();
        user.setUsername(registrationForm.getUsername());
        user.setEmail(registrationForm.getEmail());
        user.setActive(true);
        user.setRoles(Collections.singleton(Roles.ADMIN));
        user.setPassword(passwordEncoder.encode(registrationForm.getPassword()));

        userRepository.save(user);
        System.out.println("Пользователь сохранён: " + user.getUsername());

        return "redirect:/login";
    }

}
