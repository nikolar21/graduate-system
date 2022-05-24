package com.tusofia.graduatesystem.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@Table(name = "student")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student  {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotEmpty
  private String firstName;

  @NotEmpty
  private String lastName;

  @NotEmpty
  private String course;

  @ManyToOne
  @JoinColumn(name = "mentor_id")
  private Mentor mentor;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "project_id")
  private Project project;

  @OneToOne
  @JoinColumn(name = "user_id")
  @JsonIgnore
  private User user;

  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date createDate;

  @UpdateTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date modifyDate;
}
