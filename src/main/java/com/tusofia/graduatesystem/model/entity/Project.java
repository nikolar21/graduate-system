package com.tusofia.graduatesystem.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table
public class Project {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;

  private String subject;

  private String projectFileName;

  @OneToOne(mappedBy = "project")
  private Student student;

  private String comment;

  private boolean isApproved;

  private boolean isDeclined;

  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date createDate;

  @UpdateTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date modifyDate;

}
