package com.tusofia.graduatesystem.model.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {

  private String comment;
  private UserDto user;
  private Date createDate;
  private Date modifyDate;
}
