package com.tusofia.graduatesystem.controller;

import com.tusofia.graduatesystem.model.dto.ProjectDto;
import com.tusofia.graduatesystem.model.entity.Project;
import com.tusofia.graduatesystem.repository.pagination.ProjectPage;
import com.tusofia.graduatesystem.repository.pagination.ProjectSearchCriteria;
import com.tusofia.graduatesystem.service.ProjectService;
import com.tusofia.graduatesystem.util.FileDownloadUtil;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

  @GetMapping("/download/{fileCode}")
  public ResponseEntity<?> downloadFile(@PathVariable("fileCode") String fileCode) {
    FileDownloadUtil downloadUtil = new FileDownloadUtil();

    Resource resource;
    try {
      resource = downloadUtil.getFileAsResource(fileCode);
    } catch (IOException e) {
      return ResponseEntity.internalServerError().build();
    }

    if (resource == null) {
      return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
    }

    String contentType = "application/octet-stream";
    String headerValue = "attachment; filename=\"" + resource.getFilename() + "\"";

    return ResponseEntity.ok()
        .contentType(MediaType.parseMediaType(contentType))
        .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
        .body(resource);
  }
}
