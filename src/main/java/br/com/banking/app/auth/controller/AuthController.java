package br.com.banking.app.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.banking.app.auth.dto.AuthResponseDTO;
import br.com.banking.app.auth.dto.LoginRequestDTO;
import br.com.banking.app.auth.dto.RegisterRequestDTO;
import br.com.banking.app.auth.service.AuthService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  final AuthService authService;




  @PostMapping("/auth/login")
  public ResponseEntity<AuthResponseDTO> userLogin (@RequestBody LoginRequestDTO loginRequestDTO) {
    return ResponseEntity.ok(authService.signIn(loginRequestDTO));
  }

  @PostMapping("/auth/register") 
  public ResponseEntity<AuthResponseDTO> userRegister (@RequestBody RegisterRequestDTO RegisterRequestDTO) {
    return ResponseEntity.ok(authService.signUp(RegisterRequestDTO));
  }

  

}
