package com.tusofia.graduatesystem.service;

import com.tusofia.graduatesystem.model.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;

public interface ProjectService {
  Page<Project> findAllProjects(
      @PageableDefault(size = 20)
          @SortDefault.SortDefaults({@SortDefault(sort = "id", direction = Sort.Direction.ASC)})
          Pageable pageable);
}
