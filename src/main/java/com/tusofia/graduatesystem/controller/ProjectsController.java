package com.tusofia.graduatesystem.controller;

import com.tusofia.graduatesystem.model.entity.Project;
import com.tusofia.graduatesystem.repository.pagination.ProjectPage;
import com.tusofia.graduatesystem.repository.pagination.ProjectSearchCriteria;
import com.tusofia.graduatesystem.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectsController {

  private final ProjectService projectService;

  @GetMapping
  public ResponseEntity<Page<Project>> getProjects(ProjectPage projectPage, ProjectSearchCriteria projectSearchCriteria) {
    return new ResponseEntity<>(projectService.getProjects(projectPage, projectSearchCriteria), HttpStatus.OK);
  }
}
