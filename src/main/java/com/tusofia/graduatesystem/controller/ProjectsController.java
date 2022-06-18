package com.tusofia.graduatesystem.controller;

import com.tusofia.graduatesystem.model.dto.ProjectDto;
import com.tusofia.graduatesystem.model.entity.Project;
import com.tusofia.graduatesystem.repository.pagination.ProjectPage;
import com.tusofia.graduatesystem.repository.pagination.ProjectSearchCriteria;
import com.tusofia.graduatesystem.service.ProjectService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectsController {

  private final ProjectService projectService;
  private final ModelMapper modelMapper = new ModelMapper();

  @GetMapping
  public ResponseEntity<List<ProjectDto>> getProjects(ProjectPage projectPage, ProjectSearchCriteria projectSearchCriteria) {
    Page<Project> projects = projectService.getProjects(projectPage, projectSearchCriteria);
    Page<ProjectDto> dtoPage = projects.map(project -> modelMapper.map(project, ProjectDto.class));
    List<ProjectDto> projectDto = dtoPage.getContent();

    return ResponseEntity.ok().body(projectDto);
  }

  @GetMapping("/{projectId}")
  public ResponseEntity<ProjectDto> getProject(@PathVariable Long projectId) {
    Project project = projectService.getProjectById(projectId);

    return ResponseEntity.ok().body(modelMapper.map(project, ProjectDto.class));
  }
}
