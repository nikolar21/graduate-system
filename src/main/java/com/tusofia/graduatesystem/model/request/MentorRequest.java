package com.tusofia.graduatesystem.model.request;

import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class MentorRequest extends RegistrationRequest {

  @NotBlank private String discipline;
}
