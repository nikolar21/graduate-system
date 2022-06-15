package com.tusofia.graduatesystem.model.request;

import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CommentRequest {

  @NotNull
  private Long projectId;

  @NotNull
  private Long userId;

  @NotNull
  private String comment;
}
