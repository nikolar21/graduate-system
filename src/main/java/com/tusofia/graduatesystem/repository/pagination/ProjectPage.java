package com.tusofia.graduatesystem.repository.pagination;

import lombok.Data;
import org.springframework.data.domain.Sort;

@Data
public class ProjectPage {

  private int pageNumber = 0;
  private int pageSize = 50;
  private Sort.Direction sortDirection = Sort.Direction.ASC;
  private String sortBy = "id";
}
