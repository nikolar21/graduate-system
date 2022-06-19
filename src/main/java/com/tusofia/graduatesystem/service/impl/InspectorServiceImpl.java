package com.tusofia.graduatesystem.service.impl;

import static com.tusofia.graduatesystem.util.Constants.MENTOR_NOT_FOUND_MESSAGE;

import com.tusofia.graduatesystem.exception.EntityNotFoundException;
import com.tusofia.graduatesystem.model.entity.Project;
import com.tusofia.graduatesystem.model.request.ProjectRequest;
import com.tusofia.graduatesystem.repository.MentorRepository;
import com.tusofia.graduatesystem.repository.ProjectRepository;
import com.tusofia.graduatesystem.service.InspectorService;
import java.text.MessageFormat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InspectorServiceImpl implements InspectorService {

  private final ProjectRepository projectRepository;
  private final MentorRepository mentorRepository;

  public Project addProject(ProjectRequest request) {
    Project project = new Project();
    project.setStudentFirstName(request.getStudent().getFirstName());
    project.setStudentLastName(request.getStudent().getLastName());
    project.setStudentFaculty(request.getStudent().getFaculty());
    project.setStudentSpecialty(request.getStudent().getSpecialty());
    project.setStudentGraduationYear(request.getStudent().getGraduationYear());

    project.setProjectType(request.getType());
    project.setProjectTitle(request.getTitle());
    project.setProjectSubject(request.getSubject());
    project.setProjectDescription(request.getDescription());

    project.setCommission(request.getCommission());

    project.setMentor(mentorRepository.findById(request.getMentorId()).orElseThrow(() ->
        new EntityNotFoundException(MessageFormat.format(MENTOR_NOT_FOUND_MESSAGE, request.getMentorId()))));

    return projectRepository.save(project);
  }
}
