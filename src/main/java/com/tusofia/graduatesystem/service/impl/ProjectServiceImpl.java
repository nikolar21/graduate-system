package com.tusofia.graduatesystem.service.impl;

import com.tusofia.graduatesystem.model.entity.Project;
import com.tusofia.graduatesystem.repository.ProjectRepository;
import com.tusofia.graduatesystem.repository.pagination.ProjectCriteriaRepository;
import com.tusofia.graduatesystem.repository.pagination.ProjectPage;
import com.tusofia.graduatesystem.repository.pagination.ProjectSearchCriteria;
import com.tusofia.graduatesystem.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

  private final ProjectRepository projectRepository;
  private final ProjectCriteriaRepository projectCriteriaRepository;

  public Page<Project> getProjects(ProjectPage projectPage, ProjectSearchCriteria employeeSearchCriteria) {
    return projectCriteriaRepository.findAllWithFilters(projectPage, employeeSearchCriteria);
  }
}
