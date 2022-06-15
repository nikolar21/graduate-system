package com.tusofia.graduatesystem.service.impl;

import com.tusofia.graduatesystem.model.entity.Project;
import com.tusofia.graduatesystem.repository.ProjectRepository;
import com.tusofia.graduatesystem.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

  private final ProjectRepository projectRepository;

  public Page<Project> findAllProjects(
      @PageableDefault(size = 20)
          @SortDefault.SortDefaults({@SortDefault(sort = "id", direction = Sort.Direction.ASC)})
          Pageable pageable) {
    return projectRepository.findAllProjects(pageable);
  }
}
