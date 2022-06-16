package com.tusofia.graduatesystem.service;

import com.tusofia.graduatesystem.model.entity.Project;
import com.tusofia.graduatesystem.model.request.ProjectRequest;

public interface InspectorService {
  Project addProject(ProjectRequest request);
}
