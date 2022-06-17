package com.tusofia.graduatesystem.service;

import com.tusofia.graduatesystem.model.entity.Project;
import com.tusofia.graduatesystem.model.response.FileUploadResponse;
import com.tusofia.graduatesystem.repository.pagination.ProjectPage;
import com.tusofia.graduatesystem.repository.pagination.ProjectSearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface ProjectService {

  Page<Project> getProjects(ProjectPage projectPage, ProjectSearchCriteria employeeSearchCriteria);

  FileUploadResponse projectUpload(MultipartFile multipartFile, Long id);
}
