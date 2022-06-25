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
  private String topic;

  @NotNull
  private String subject;

  @NotNull
  private String description;

  @NotNull
  private String commission;

  @NotNull
  private String reviewer;

  @NotNull
  private String review;
}
