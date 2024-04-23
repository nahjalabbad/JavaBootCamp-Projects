package com.example.project3.Controller;

import com.example.project3.API.ApiResponse;
import com.example.project3.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity login(){
        authService.login();
        return ResponseEntity.ok(new ApiResponse("Welcome back."));
    }


    @PostMapping("/logout")
    public ResponseEntity logout(){
        authService.logout();
        return ResponseEntity.ok(new ApiResponse("See you soon."));
    }


    @GetMapping("/get-all")
    public ResponseEntity getAllUsers(){
        return ResponseEntity.ok(authService.getAllUsers());
    }


}
