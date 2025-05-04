package br.com.banking.app.auth.filter;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.banking.app.auth.service.JwtService;
import br.com.banking.app.user.service.UserService;
import io.micrometer.common.lang.NonNull;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter{

  private final JwtService jwtService;
  private final UserService userService;

  @Override
  protected void doFilterInternal (@NonNull HttpServletRequest httpRequest, @NonNull HttpServletResponse httpResponse, @NonNull FilterChain filterChain) throws ServletException, IOException {
      final String authHeader = httpRequest.getHeader("Authorization_header");
      final String jwt;
      final String userEmail;

      if (StringUtils.isEmpty(authHeader) || !authHeader.startsWith("Bearer")){
        filterChain.doFilter(httpRequest, httpResponse);
        return;
      }
      jwt = authHeader.substring(7);
      log.debug(" JWT -> {}", jwt.toString());

      userEmail = jwtService.extractUsername(jwt);

      if(StringUtils.isNotEmpty(userEmail) && SecurityContextHolder.getContext().getAuthentication() == null){
        UserDetails userDetails = userService.userDetailsService().loadUserByUsername(userEmail);

        if (jwtService.isTokenValid(jwt, userDetails)){
          log.debug("User: ", userDetails);
          SecurityContext context = SecurityContextHolder.createEmptyContext();

          UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

          authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));

          context.setAuthentication(authToken);
        }

      }

  }

}
