package br.com.banking.app.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.banking.app.auth.filter.JwtAuthenticationFilter;
import br.com.banking.app.user.service.UserService;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  

  private final JwtAuthenticationFilter jwtAuthenticationFilter;
  private final UserService userService;
  private final PasswordEncoder passwordEncoder;

    

  @Bean
  public AuthenticationProvider authenticationProvider (){
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

    authProvider.setUserDetailsService(userService.userDetailsService());

    authProvider.setPasswordEncoder(passwordEncoder);

    return authProvider;
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
    return config.getAuthenticationManager();
  }
  
  @Bean
  public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
    http
      .csrf(csrf -> csrf.disable())
      .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
      .authorizeHttpRequests(authorize -> 
      authorize.requestMatchers("/auth/**").permitAll()
      .requestMatchers("/v3/api-docs/**").permitAll()
      .requestMatchers("/transactions/save").permitAll()
      .requestMatchers("/transactions/**").permitAll()
      .requestMatchers("/swagger-ui/**").permitAll()
      .requestMatchers("/swagger-ui/index.html").permitAll()
      .requestMatchers("/swagger-ui.html").permitAll()
      .requestMatchers("/swagger-resources/**").permitAll()
      .requestMatchers("/all").permitAll()
      .requestMatchers("/transactions").permitAll()
      .requestMatchers("/transactions/all").permitAll()
      .requestMatchers("/transactions/{id}").permitAll()
      .requestMatchers("/transactions/transaction{id}").permitAll()
      .requestMatchers("/transactions/type").permitAll()
      .requestMatchers("/transactions/expense{id}").permitAll()
      .requestMatchers("/transactions/income{id}").permitAll()
      .requestMatchers("/transactions/delete{id}/{idTransaction}").permitAll()
      .anyRequest()
      .authenticated())
      .authenticationProvider(authenticationProvider())
      .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);



    return http.build();
  }

}
