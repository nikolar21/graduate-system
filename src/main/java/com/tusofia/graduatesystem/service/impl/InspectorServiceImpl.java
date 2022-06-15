package com.tusofia.graduatesystem.service.impl;

import com.tusofia.graduatesystem.model.entity.Mentor;
import com.tusofia.graduatesystem.model.entity.Project;
import com.tusofia.graduatesystem.model.request.ProjectRequest;
import com.tusofia.graduatesystem.model.response.MessageResponse;
import com.tusofia.graduatesystem.repository.MentorRepository;
import com.tusofia.graduatesystem.repository.ProjectRepository;
import com.tusofia.graduatesystem.service.InspectorService;
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
