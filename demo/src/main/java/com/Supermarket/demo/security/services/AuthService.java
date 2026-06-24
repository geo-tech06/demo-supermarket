package com.Supermarket.demo.security.services;

import com.Supermarket.demo.dto.AuthenticationResponse;
import com.Supermarket.demo.dto.LoginData;
import com.Supermarket.demo.entities.Staff;
import com.Supermarket.demo.repositories.StaffRepository;
import com.Supermarket.demo.security.config.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final StaffRepository repository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthService(StaffRepository repository, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.repository = repository;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public AuthenticationResponse login(LoginData dto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.getEmail(),
                        dto.getPassword()
                )
        );

        Staff staff = repository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("STAFF_NOT_FOUND"));

        String jwtToken = jwtService.generateToken(staff);

        return new AuthenticationResponse(jwtToken);
    }
}