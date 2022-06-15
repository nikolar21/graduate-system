package com.tusofia.graduatesystem.service;

import com.tusofia.graduatesystem.model.request.ProjectRequest;
import com.tusofia.graduatesystem.model.response.MessageResponse;
import org.springframework.http.ResponseEntity;

public interface InspectorService {
  ResponseEntity<MessageResponse> addProject(ProjectRequest request);
}
