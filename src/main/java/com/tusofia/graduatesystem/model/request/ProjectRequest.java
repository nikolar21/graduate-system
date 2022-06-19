package com.tusofia.graduatesystem.model.request;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProjectRequest {

  @NotNull
  private Long mentorId;

  @Valid
  private StudentRequest student;

  @NotNull
  private String type;

  @NotNull
  private String title;

  @NotNull
  private String subject;

  @NotNull
  private String description;

  @NotNull
  private String commission;
}
