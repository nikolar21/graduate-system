package com.tusofia.graduatesystem.model.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

@EqualsAndHashCode(callSuper = true)
@Data
public class StudentRequest extends RegistrationRequest {

  @NotBlank
  private String course;

}
