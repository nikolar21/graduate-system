package com.tusofia.graduatesystem.controller;

import com.tusofia.graduatesystem.model.entity.Project;
import com.tusofia.graduatesystem.service.ProjectService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectsController {

  private final ProjectService projectService;

  @ApiResponses(
      value = {
        @ApiResponse(
            code = 200,
            message = "List of all projects",
            response = Project.class,
            responseContainer = "List")
      })
  @GetMapping
  public Page<Project> findAllProjects(
      @PageableDefault(size = 20)
          @SortDefault.SortDefaults({@SortDefault(sort = "id", direction = Sort.Direction.ASC)})
          Pageable pageable) {
    return projectService.findAllProjects(pageable);
  }
}
