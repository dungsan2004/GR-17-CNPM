package org.example.dung_s_spring_boot.controller;

import org.example.dung_s_spring_boot.dto.LoginRequest;
import org.example.dung_s_spring_boot.dto.RegisterRequest;
import org.example.dung_s_spring_boot.dto.UserResponse;
import org.example.dung_s_spring_boot.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    public AuthController(AuthService authService) { this.authService = authService; }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody RegisterRequest req) {
        return ResponseEntity.ok(authService.register(req));
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody LoginRequest req) {
        return ResponseEntity.ok(authService.login(req));
    }
}
