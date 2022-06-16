package com.tusofia.graduatesystem.service.impl;

import com.tusofia.graduatesystem.exceptions.MentorNotFoundException;
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
    project.setStudentSpecialty(request.getStudent().getSpecialty());
    project.setGraduationYear(request.getStudent().getGraduationYear());

    project.setTitle(request.getTitle());
    project.setSubject(request.getSubject());
    project.setDescription(request.getDescription());
    project.setProjectFileName(request.getProjectFileName());

    project.setMentor(mentorRepository.findById(request.getMentorId()).orElseThrow(() ->
        new MentorNotFoundException(MessageFormat.format("Mentor with id {0} not found!", request.getMentorId()))));

    return projectRepository.save(project);
  }
}
