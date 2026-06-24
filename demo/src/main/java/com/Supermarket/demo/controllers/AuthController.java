package com.Supermarket.demo.controllers;

import com.Supermarket.demo.dto.AuthenticationResponse;
import com.Supermarket.demo.dto.LoginData;
import com.Supermarket.demo.security.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@Valid @RequestBody LoginData dto) {
        return service.login(dto);
    }
}