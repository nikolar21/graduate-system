package com.tusofia.graduatesystem.service.impl;

import com.tusofia.graduatesystem.model.entity.Mentor;
import com.tusofia.graduatesystem.model.entity.Project;
import com.tusofia.graduatesystem.model.request.ProjectRequest;
import com.tusofia.graduatesystem.model.response.MessageResponse;
import com.tusofia.graduatesystem.repository.MentorRepository;
import com.tusofia.graduatesystem.repository.ProjectRepository;
import com.tusofia.graduatesystem.service.InspectorService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InspectorServiceImpl implements InspectorService {
  private final ProjectRepository projectRepository;
  private final MentorRepository mentorRepository;

  public ResponseEntity<MessageResponse> addProject(ProjectRequest request) {

    Project project = new Project();
    project.setStudentFirstName(request.getStudent().getFirstName());
    project.setStudentLastName(request.getStudent().getLastName());
    project.setStudentSpecialty(request.getStudent().getSpecialty());
    project.setGraduationYear(request.getStudent().getGraduationYear());

    project.setTitle(request.getTitle());
    project.setSubject(request.getSubject());
    project.setDescription(request.getDescription());
    project.setProjectFileName(request.getProjectFileName());

    Optional<Mentor> mentor = mentorRepository.findById(request.getMentorId());

    mentor.ifPresent(project::setMentor);

    projectRepository.save(project);

    return ResponseEntity.ok(new MessageResponse("Successfully added new project!"));
  }
}
