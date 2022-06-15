package com.tusofia.graduatesystem.model.request;

import java.time.Year;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StudentRequest {

  @NotNull
  private String firstName;

  @NotNull
  private String lastName;

  @NotNull
  private String specialty;

  @NotNull
  private Year graduationYear;
}
