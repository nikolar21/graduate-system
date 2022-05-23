package com.tusofia.scienceblog.model.request;

import com.tusofia.scienceblog.model.entity.ERole;
import lombok.Data;

import java.util.Set;

import javax.validation.constraints.*;

@Data
public class SignupRequest {

  @NotBlank
  @Size(min = 2, max = 20)
  private String firstName;

  @NotBlank
  @Size(min = 2, max = 20)
  private String lastName;

  @NotBlank
  @Size(min = 3, max = 20)
  private String username;

  @NotBlank
  @Size(max = 50)
  @Email
  private String email;

  private Set<ERole> role;

  @NotBlank
  @Size(min = 6, max = 40)
  private String password;
}
