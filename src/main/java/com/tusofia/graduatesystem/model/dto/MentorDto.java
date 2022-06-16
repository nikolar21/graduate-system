package com.tusofia.graduatesystem.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MentorDto {

  private String firstname;
  private String lastname;
  private String faculty;
  private String disciplines;
}
