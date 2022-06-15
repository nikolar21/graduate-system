package com.tusofia.graduatesystem.service;

import com.tusofia.graduatesystem.model.entity.Project;
import com.tusofia.graduatesystem.repository.pagination.ProjectPage;
import com.tusofia.graduatesystem.repository.pagination.ProjectSearchCriteria;
import org.springframework.data.domain.Page;

public interface ProjectService {

  Page<Project> getProjects(ProjectPage projectPage, ProjectSearchCriteria employeeSearchCriteria);
}
