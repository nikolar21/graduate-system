package com.tusofia.graduatesystem.security.jwt;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tusofia.graduatesystem.model.entity.User;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Data
public class CustomUserDetails implements UserDetails {

  @Serial private static final long serialVersionUID = 1L;

  private final Long id;

  private final String username;

  private final String email;

  @JsonIgnore
  private final String password;

  private final Collection<? extends GrantedAuthority> authorities;

  public static CustomUserDetails build(User user) {
    List<GrantedAuthority> authorities =
        user.getRoles().stream()
            .map(role -> new SimpleGrantedAuthority(role.getName().name()))
            .collect(Collectors.toList());

    return new CustomUserDetails(
        user.getId(), user.getUsername(), user.getEmail(), user.getPassword(), authorities);
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
