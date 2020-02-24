package de.sopro.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  @Value("${credentials.default.username}")
  private final String username = "admin";

  @Value("${credentials.default.password}")
  private final String password = "password";

  @Autowired
  PasswordEncoder passwordEncoder;

  @Override
  public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

    // Currently, this user details service only knows about the username of the default credentials
    if (this.username.compareTo(username) != 0) {
      throw new UsernameNotFoundException(String.format("User '%s' not found.", username));
    }
    // Return user details with default username, password and role "ADMIN"
    return User.withUsername(this.username).password(passwordEncoder.encode(password))
        .roles("ADMIN").build();
  }

}
