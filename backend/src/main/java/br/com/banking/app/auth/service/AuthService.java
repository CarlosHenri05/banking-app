package br.com.banking.app.auth.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.banking.app.auth.dto.AuthResponseDTO;
import br.com.banking.app.auth.dto.LoginRequestDTO;
import br.com.banking.app.auth.dto.RegisterRequestDTO;
import br.com.banking.app.user.model.Roles;
import br.com.banking.app.user.model.User;
import br.com.banking.app.user.repository.UserRepository;
import br.com.banking.app.user.service.UserService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {


  private final UserRepository userRepository;
  private final JwtService jwtService;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;
  private final UserService userService;

 public AuthResponseDTO signUp (RegisterRequestDTO registerRequestDTO) {
      var user = User.builder()
                    .username(registerRequestDTO.getEmail())
                    .password(passwordEncoder.encode(registerRequestDTO.getPassword()))    
                    .role(Roles.USER)
                    .build();

      user = userService.saveUser(user);

      var jwt = jwtService.generateToken(user);

      return AuthResponseDTO.builder()
                            .token(jwt)
                            .build();

  }

  public AuthResponseDTO signIn (LoginRequestDTO loginRequestDTO) {
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
        loginRequestDTO.getEmail(),
        loginRequestDTO.getPassword()
    ));
    var user = userRepository.findByUsername(loginRequestDTO.getEmail())
                             .orElseThrow(() -> new IllegalArgumentException("User not found"));
    

    var jwt = jwtService.generateToken(user);

    return AuthResponseDTO.builder()
                          .token(jwt)
                          .build();
  }

}
