package com.tusofia.graduatesystem.controller;

import com.tusofia.graduatesystem.model.entity.Project;
import com.tusofia.graduatesystem.model.request.ProjectRequest;
import com.tusofia.graduatesystem.model.response.FileUploadResponse;
import com.tusofia.graduatesystem.model.response.MessageResponse;
import com.tusofia.graduatesystem.service.InspectorService;
import com.tusofia.graduatesystem.service.ProjectService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/inspector")
@RequiredArgsConstructor
public class InspectorController {

  private final InspectorService inspectorService;
  private final ProjectService projectService;

  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "Successfully added project",
              response = MessageResponse.class,
              responseContainer = "List")
      })
  @PostMapping("/projects")
  public Project addProject(@Valid @RequestBody ProjectRequest request) {

    return inspectorService.addProject(request);
  }

  @PostMapping("/projects/upload")
  public ResponseEntity<FileUploadResponse> uploadFile(@RequestParam("file") MultipartFile multipartFile, Long id) {

    FileUploadResponse response = projectService.projectUpload(multipartFile, id);

    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
