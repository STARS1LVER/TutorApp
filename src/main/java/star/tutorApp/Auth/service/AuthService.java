package star.tutorApp.Auth.service;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import star.tutorApp.Jwt.JwtService;
import star.tutorApp.User.Role;
import star.tutorApp.User.User;
import star.tutorApp.User.UserRepository;
import star.tutorApp.Auth.dto.AuthResponse;
import star.tutorApp.Auth.dto.LoginRequest;
import star.tutorApp.Auth.dto.LoginResponse;
import star.tutorApp.Auth.dto.RegisterRequest;
import star.tutorApp.Auth.dto.UserResponse;
import star.tutorApp.Exception.EmailAlreadyExistsException;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    @Autowired
    private final  JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

  public LoginResponse login(LoginRequest request) {
    try {
          authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        User user = userRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
            
        String token = jwtService.getToken(user);
        
        UserResponse userResponse = UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .career(user.getCareer())
                .modality(user.getModality())
                .semester(user.getSemester())
                .role(user.getRole())
                .build();
                
        return LoginResponse.builder()
                .token(token)
                .message("Inicio de sesión exitoso")
                .status(200)
                .user(userResponse)
                .build();
    } catch (BadCredentialsException e) {
        System.out.println("Credenciales inválidas para: " + request.getEmail());
        throw new BadCredentialsException("Email o contraseña incorrectos");
    } catch (Exception e) {
        System.out.println("Error en login: " + e.getMessage());
        throw e;
    }
}


public AuthResponse register(RegisterRequest request) {
    System.out.println("Iniciando registro para: " + request.getEmail());
    
    if (userRepository.findByEmail(request.getEmail()).isPresent()) {
        System.out.println("Email ya registrado: " + request.getEmail());
        throw new EmailAlreadyExistsException("El email " + request.getEmail() + " ya está registrado");
    }
    
    String encodedPassword = passwordEncoder.encode(request.getPassword());
    User user = User.builder()
            .name(request.getName())
            .lastName(request.getLastName())
            .email(request.getEmail())
            .password(encodedPassword)
            .career(request.getCareer())
            .modality(request.getModality())
            .semester(request.getSemester())
            .role(Role.USER)
            .build();
            
    userRepository.save(user);
    return AuthResponse.builder()
            .token(jwtService.getToken(user))
            .email(request.getEmail())
            .message("Registro exitoso")
            .build();
}



public AuthResponse logout(String authHeader) {
    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
        return AuthResponse.builder()
            .message("No hay sesión activa")
            .build();
    }
    
    try {
        String token = authHeader.substring(7);
        SecurityContextHolder.clearContext();
        return AuthResponse.builder()
            .message("Sesión cerrada exitosamente")
            .build();
    } catch (Exception e) {
        return AuthResponse.builder()
            .message("Error al cerrar sesión")
            .build();
    }
}

}
