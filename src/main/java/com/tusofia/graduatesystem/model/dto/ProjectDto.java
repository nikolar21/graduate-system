package com.tusofia.graduatesystem.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_EMPTY)
public class ProjectDto {

  private String studentFirstName;
  private String studentLastName;
  private String studentSpecialty;
  private Integer graduationYear;
  private String title;
  private String subject;
  private String type;
  private String description;
  private String projectFileName;
  private MentorDto mentor;
  private List<CommentDto> comments;
  private Date createDate;
  private Date modifyDate;
}
