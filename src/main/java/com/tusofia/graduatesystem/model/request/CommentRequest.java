package com.tusofia.graduatesystem.model.request;

import lombok.Data;

@Data
public class CommentRequest {
  private Long projectId;
  private String comment;
}
