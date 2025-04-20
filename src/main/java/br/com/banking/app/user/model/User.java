package br.com.banking.app.user.model;

import br.com.banking.app.auth.constants.Roles;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "users")
public class User {

  private String email;
  private String password;

  @Enumerated(EnumType.STRING)
  private Roles role;

}
