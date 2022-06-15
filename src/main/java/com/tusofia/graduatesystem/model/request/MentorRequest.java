package com.tusofia.graduatesystem.model.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class MentorRequest {

  @NotNull
  @Size(max = 20)
  private String mentorFirstName;

  @NotNull
  @Size(max = 20)
  private String mentorLastName;

  @NotNull
  @Size(max = 20)
  private String mentorFaculty;

  @NotNull
  @Size(max = 20)
  private String mentorDiscipline;
}
