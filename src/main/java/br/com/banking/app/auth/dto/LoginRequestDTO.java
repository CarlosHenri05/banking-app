package br.com.banking.app.auth.dto;

import br.com.banking.app.user.model.Roles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginRequestDTO {

  private String email;
  private String password;

}
