package com.tusofia.graduatesystem.model.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StudentRequest {

  @NotNull
  private String firstName;

  @NotNull
  private String lastName;

  @NotNull
  private String faculty;

  @NotNull
  private String specialty;

  @NotNull
  private String facultyGroup;

  @NotNull
  private String facultyNumber;

  @NotNull
  private String educationalDegree;

  @NotNull(message = "Please, provide year of production!")
  @Min(value = 1800)
  @Max(value = 2022)
  private Integer graduationYear;
}
