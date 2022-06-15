package com.tusofia.graduatesystem.controller;

import com.tusofia.graduatesystem.model.request.ProjectRequest;
import com.tusofia.graduatesystem.model.response.MessageResponse;
import com.tusofia.graduatesystem.service.InspectorService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inspector")
@RequiredArgsConstructor
public class InspectorController {

  private final InspectorService inspectorService;

  @ApiResponses(
      value = {
        @ApiResponse(
            code = 200,
            message = "Successfully added project",
            response = MessageResponse.class,
            responseContainer = "List")
      })
  @PostMapping("/projects")
  public ResponseEntity<MessageResponse> addProject(@Valid @RequestBody ProjectRequest request) {

    return inspectorService.addProject(request);
  }
}
