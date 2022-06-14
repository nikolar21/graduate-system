package com.tusofia.graduatesystem.repository;

import com.tusofia.graduatesystem.model.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProjectRepository extends JpaRepository<Project, Long> {

  @Query("select p from Project p")
  Page<Project> findAllProjects(Pageable pageable);
}
