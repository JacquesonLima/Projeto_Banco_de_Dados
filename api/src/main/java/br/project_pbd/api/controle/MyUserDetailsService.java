package br.project_pbd.api.controle;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class MyUserDetailsService implements UserDetailsService {

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    if ("user".equals(username)) {
      return User.builder().username("user").password(new BCryptPasswordEncoder().encode("password")).roles("USER")
          .build();
    }
    throw new UnsupportedOperationException("Usuário não encontrado");
  }

}
