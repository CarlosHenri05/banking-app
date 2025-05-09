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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Auth", description = "Authentication and Authorization")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  final AuthService authService;



  @Operation(summary = "Login", description = "User login")
  @ApiResponse(responseCode = "200", description = "User logged in successfully")
  @PostMapping("/login")
  public ResponseEntity<AuthResponseDTO> userLogin (@RequestBody @Valid LoginRequestDTO loginRequestDTO) {
    return ResponseEntity.ok(authService.signIn(loginRequestDTO));
  }

  @Operation(summary = "Register", description = "User register")
  @ApiResponse(responseCode = "200", description = "User registered successfully")
  @ApiResponse(responseCode = "400", description = "User already exists")
  @PostMapping("/register") 
  public ResponseEntity<AuthResponseDTO> userRegister (@RequestBody @Valid RegisterRequestDTO RegisterRequestDTO) {
    return ResponseEntity.ok(authService.signUp(RegisterRequestDTO));
  }

  

}
