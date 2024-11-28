package star.tutorApp.Jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {


    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override // Se usa para sobreescribir metodos en este caso de la clase OncePerRequestFilter
    /**
     *Obtiene el token de la solicitud.
     * Si no hay token, pasa la solicitud al siguiente filtro y termina.
     * Si hay token, simplemente pasa la solicitud al siguiente filtro
     */
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Permitir solicitudes OPTIONS
        if (request.getMethod().equals("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_OK);
            filterChain.doFilter(request, response);
            return;
        }

        try {
            final String token = getTokenFromRequest(request);
            final String username;

            if (token == null) {
                System.out.println("No se encontró token");
                filterChain.doFilter(request, response);
                return;
            }

            username = jwtService.getUsernameFromToken(token);
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                if (jwtService.isTokenValid(token, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                    );
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
            filterChain.doFilter(request, response);
        } catch (io.jsonwebtoken.security.SecurityException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            String jsonResponse = "{\"message\":\"Token inválido o manipulado\",\"error\":\"INVALID_TOKEN\"}";
            response.getWriter().write(jsonResponse);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            String jsonResponse = "{\"message\":\"Error de autenticación\",\"error\":\"AUTH_ERROR\"}";
            response.getWriter().write(jsonResponse);
        }
    }

    private String getTokenFromRequest(HttpServletRequest request) {
       final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

       if(StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
           return authHeader.substring(7);
       }

       return null;
    }
}
