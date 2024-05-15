package com.example.security.authentication;

import com.example.security.User.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthServiceController {

    private final AuthService authService;

    @PostMapping("/login")
    //@PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody final AuthRequest authRequest) {
        return ResponseEntity.ok(authService.authenticate(authRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody final RegisterRequest registerRequest) {
        return ResponseEntity.ok(authService.registerUsu(registerRequest));
    }

    @GetMapping("/get/getInfo")
    public ResponseEntity<User> getInfo(@RequestParam final String username) {
        System.out.println("username: " + username);
        return ResponseEntity.ok(authService.getCurrentUser(username));
    }
}
