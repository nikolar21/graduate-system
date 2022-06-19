package com.tusofia.graduatesystem.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_EMPTY)
public class StudentDto {

  private String firstName;
  private String lastName;
  private String faculty;
  private String specialty;
  private String facultyGroup;
  private String facultyNumber;
  private String educationalDegree;
  private Integer graduationYear;
}
