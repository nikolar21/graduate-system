package com.tusofia.graduatesystem.model.entity;

import java.io.File;
import java.time.Year;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@Setter
@Entity
@Table
public class Project {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String studentFirstName;

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
