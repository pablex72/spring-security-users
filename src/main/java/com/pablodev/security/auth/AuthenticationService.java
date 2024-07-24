// AuthenticationService.java
package com.pablodev.security.auth;

import com.pablodev.security.config.JwtService;
import com.pablodev.security.user.Role;
import com.pablodev.security.user.User;
import com.pablodev.security.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        User user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole()) // Usar el rol proporcionado en la solicitud
                .build();
        repository.save(user);

        // Obtener el rol del usuario y generar el token usando el método con rol
        String role = user.getRole().name(); // Obtener el rol como un String
        String jwtToken = jwtService.generateTokenWithRole(user, role);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        User user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        String role = user.getRole().name(); // Obtener el rol como un String
        String jwtToken = jwtService.generateTokenWithRole(user, role);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
