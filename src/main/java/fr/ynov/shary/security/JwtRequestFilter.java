package fr.ynov.shary.security;

import fr.ynov.shary.services.CustomUserDetailsService;
import fr.ynov.shary.services.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtRequestFilter.class);

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private static final String[] PUBLIC_URLS = {
            "/api/auth/register",
            "/api/auth/login",
            "/swagger-ui/",
            "/v3/api-docs/",
            "/v3/api-docs",
            "/v3/api-docs/swagger-config",
            "/actuator/prometheus",
            // Ajoutez ici toutes les autres routes publiques
    };

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String requestPath = request.getServletPath();
        // Vérifiez si l'URL demandée est dans la liste des URLs publiques
        for (String publicUrl : PUBLIC_URLS) {

            if (requestPath.startsWith(publicUrl)) {
                // Si l'URL est publique, passez simplement au filtre suivant sans exiger de JWT
                filterChain.doFilter(request, response);
                return;
            }
        }

        final String requestTokenHeader = request.getHeader("Authorization");
        String username = null;
        String jwtToken = null;

        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);
            try {
                username = jwtTokenUtil.getUsernameFromToken(jwtToken);
            } catch (Exception e) {
                logger.warn("Unable to get JWT Token or JWT Token has expired");
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid or expired JWT token");
                return;
            }
        } else {
            logger.warn("JWT Token does not begin with Bearer String");
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "JWT Token is missing or malformed");
            return;
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(username);
            if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            } else {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid JWT token");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

}
