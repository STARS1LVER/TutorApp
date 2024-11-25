package star.tutorApp.Auth.controllers;


import lombok.RequiredArgsConstructor;
import star.tutorApp.Auth.dto.AuthResponse;
import star.tutorApp.Auth.dto.LoginRequest;
import star.tutorApp.Auth.dto.RegisterRequest;
import star.tutorApp.Auth.service.AuthService;

// import java.net.http.HttpHeaders;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor // Hace obligatorio que se agregue el constructor con todos los argumentos

public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "login")
    public ResponseEntity<AuthResponse> login (@RequestBody LoginRequest request ){
        return ResponseEntity.ok( authService.login(request) );
    }

    @PostMapping( value = "register")
    public ResponseEntity<AuthResponse> register( @RequestBody RegisterRequest request ){
        return  ResponseEntity.ok( authService.register(request) );
    }

    @PostMapping(value = "logout")
    public ResponseEntity<AuthResponse> logout(HttpServletRequest request) {
    return ResponseEntity.ok(authService.logout(request.getHeader(HttpHeaders.AUTHORIZATION)));
}

}
