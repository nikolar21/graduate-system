package com.tusofia.graduatesystem.repository;

import com.tusofia.graduatesystem.model.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {

}
