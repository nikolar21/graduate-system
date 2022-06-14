package com.tusofia.graduatesystem.controller;

import com.tusofia.graduatesystem.model.entity.Mentor;
import com.tusofia.graduatesystem.model.entity.Project;
import com.tusofia.graduatesystem.model.request.ProjectRequest;
import com.tusofia.graduatesystem.model.response.MessageResponse;
import com.tusofia.graduatesystem.repository.MentorRepository;
import com.tusofia.graduatesystem.repository.ProjectRepository;
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
@RequestMapping("/api/inspector/")
@RequiredArgsConstructor
public class InspectorController {

  private final ProjectRepository projectRepository;
  private final MentorRepository mentorRepository;

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

    Project project = new Project();
    project.setStudentFirstName(request.getStudentFirstName());
    project.setStudentLastName(request.getStudentLastName());
    project.setStudentSpecialty(request.getStudentSpecialty());
    project.setGraduationYear(request.getGraduationYear());

    project.setTitle(request.getTitle());
    project.setSubject(request.getSubject());
    project.setDescription(request.getDescription());
    project.setProjectFileName(request.getProjectFileName());

    Mentor mentor = new Mentor();
    mentor.setFirstname(request.getMentorFirstName());
    mentor.setLastname(request.getMentorLastName());
    mentor.setFaculty(request.getMentorFaculty());
    mentor.setDisciplines(request.getMentorDiscipline());

    project.setMentor(mentor);

    mentorRepository.save(mentor);
    projectRepository.save(project);

    return ResponseEntity.ok(new MessageResponse("Successfully added new project!"));
  }
}
