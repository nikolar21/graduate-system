package com.tusofia.graduatesystem.model.request;

import java.time.Year;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class ProjectRequest {

  @NotNull private String studentFirstName;

  @NotNull private String studentLastName;

  @NotNull private String studentSpecialty;

  @NotNull private Year graduationYear;

  @NotNull private String title;

  @NotNull private String subject;

  @NotNull private String description;

  @NotNull private String projectFileName;

  @NotBlank
  @Size(max = 20)
  private String mentorFirstName;

  @NotBlank
  @Size(max = 20)
  private String mentorLastName;

  @NotBlank
  @Size(max = 20)
  private String mentorFaculty;

  @Size(max = 20)
  private String mentorDiscipline;
}
