package star.tutorApp.Config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import star.tutorApp.Jwt.JwtAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;



@Configuration // Esta anotacion indica que esta clase es de configuracion
@EnableWebSecurity
@RequiredArgsConstructor

/**
 * ESta clase es para configurar las rutas privadas
 */
public class SecurityConfig {


        private final JwtAuthenticationFilter jwtAuthenticationFilter;
        private final AuthenticationProvider authProvider;
        private final CorsConfigurationSource corsConfigurationSource;
    
        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
        {
            return http
                .cors(cors -> cors.configurationSource(corsConfigurationSource))
                .csrf(csrf -> 
                    csrf
                    .disable())
                .authorizeHttpRequests(authRequest ->
                  authRequest
                    .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                    .requestMatchers("/auth/**").permitAll()
                    .requestMatchers("/subject/accept/**").permitAll()
                    .requestMatchers("/subject/reject/**").permitAll()
                    .anyRequest().authenticated()
                    )
                .sessionManagement(sessionManager->
                    sessionManager 
                      .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
                
                
        }
    
}
