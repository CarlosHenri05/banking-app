package br.com.banking.app.user.service;


import java.time.LocalDateTime;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.banking.app.user.model.User;
import br.com.banking.app.user.repository.UserRepository;

public class UserService {

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public UserDetailsService userDetailsService() {
    return new UserDetailsService() {
        @Override
        public UserDetails loadUserByUsername(String username) {
          return userRepository.findByEmail(username).orElseThrow( () -> new UsernameNotFoundException("Username not found "));
        }
    };
  }

  public User saveUser(User newUser) {
    if (newUser.getId()==null)
      newUser.setCreatedAt(LocalDateTime.now());
    
    newUser.setUpdatedAt(LocalDateTime.now());

    return userRepository.save(newUser);
  }

}
