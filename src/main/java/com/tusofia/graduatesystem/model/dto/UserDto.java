package com.tusofia.graduatesystem.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.tusofia.graduatesystem.model.entity.Role;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_EMPTY)
public class UserDto {

  private Long id;
  private String firstname;
  private String lastname;
  private LocalDate birthDate;
  private String username;
  private String email;
  private Set<Role> roles;
  private Date createDate;
  private Date modifyDate;
}
