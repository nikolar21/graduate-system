package com.tusofia.graduatesystem.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.File;
import java.time.Year;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table
public class Project {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String studentName;

  private String studentLastName;

  private String studentSpecialty;

  private Year graduationYear;

  private String title;

  private String subject;

  private String description;

  private String projectFileName;

  private File file;

  @ManyToOne
  @JoinColumn(name = "mentor_id")
  private Mentor mentor;

  @OneToMany(mappedBy = "project")
  private List<Comment> comments;

  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date createDate;

  @UpdateTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date modifyDate;

}
