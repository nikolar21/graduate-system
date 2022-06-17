package com.tusofia.graduatesystem.service.impl;

import static com.tusofia.graduatesystem.util.Constants.PROJECT_NOT_FOUND_MESSAGE;

import com.tusofia.graduatesystem.exception.EntityNotFoundException;
import com.tusofia.graduatesystem.model.entity.Project;
import com.tusofia.graduatesystem.model.response.FileUploadResponse;
import com.tusofia.graduatesystem.repository.ProjectRepository;
import com.tusofia.graduatesystem.repository.pagination.ProjectCriteriaRepository;
import com.tusofia.graduatesystem.repository.pagination.ProjectPage;
import com.tusofia.graduatesystem.repository.pagination.ProjectSearchCriteria;
import com.tusofia.graduatesystem.service.ProjectService;
import com.tusofia.graduatesystem.util.FileUploadUtil;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

  private final ProjectRepository projectRepository;
  private final ProjectCriteriaRepository projectCriteriaRepository;

  public Page<Project> getProjects(ProjectPage projectPage, ProjectSearchCriteria employeeSearchCriteria) {
    return projectCriteriaRepository.findAllWithFilters(projectPage, employeeSearchCriteria);
  }

  public FileUploadResponse projectUpload(MultipartFile multipartFile, Long id) {
    String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
    long size = multipartFile.getSize();
    String filecode = null;
    try {
      filecode = FileUploadUtil.saveFile(fileName, multipartFile);
    } catch (IOException e) {
      e.printStackTrace();
    }

    FileUploadResponse response = new FileUploadResponse();
    response.setFileName(fileName);
    response.setSize(size);
    response.setDownloadUri("/downloadFile/" + filecode);

    Project project = projectRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
        MessageFormat.format(PROJECT_NOT_FOUND_MESSAGE, id)));

    project.setProjectFileName(Objects.requireNonNull(filecode).concat(fileName));
    projectRepository.save(project);

    return response;
  }
}
