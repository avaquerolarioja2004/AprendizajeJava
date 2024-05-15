package com.example.security.authentication;

import com.example.security.User.User;
import com.example.security.User.UserRepository;
import com.nimbusds.openid.connect.sdk.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse registerUsu(RegisterRequest request) {
        var user= User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getAuthorities())
                .build();

        userRepository.save(user);
        var JWT = jwtService.generateToken(user);
        return AuthResponse.builder().token(JWT).build();
    }

    public AuthResponse authenticate(AuthRequest request) {
        var auth=authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user=userRepository.findByUsername(request.getUsername()).orElseThrow(()->new RuntimeException("User not found"));
        var jwtToken = jwtService.generateToken(user);

        return AuthResponse.builder().token(jwtToken).build();
    }

    public User getCurrentUser(String userName) {
        return userRepository.findByUsername(userName).orElseThrow(()->new RuntimeException("User not found"));
    }
}
