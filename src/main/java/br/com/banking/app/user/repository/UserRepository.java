package br.com.banking.app.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.banking.app.user.model.User;

public interface UserRepository extends JpaRepository<Long, User> {

  Optional<User> findByEmail(String email);

}
