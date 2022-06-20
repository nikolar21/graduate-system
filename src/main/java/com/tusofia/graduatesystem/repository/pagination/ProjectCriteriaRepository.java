package com.tusofia.graduatesystem.repository.pagination;

import com.tusofia.graduatesystem.model.entity.Project;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProjectCriteriaRepository {

  private final EntityManager entityManager;

  public Page<Project> findAllWithFilters(
      ProjectPage projectPage, ProjectSearchCriteria projectSearchCriteria) {
    final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Project> criteriaQuery = criteriaBuilder.createQuery(Project.class);
    Root<Project> projectRoot = criteriaQuery.from(Project.class);
    Predicate predicate = getPredicate(projectSearchCriteria, projectRoot);
    criteriaQuery.where(predicate);
    setOrder(projectPage, criteriaQuery, projectRoot);

    TypedQuery<Project> typedQuery = entityManager.createQuery(criteriaQuery);
    typedQuery.setFirstResult(projectPage.getPageNumber() * projectPage.getPageSize());
    typedQuery.setMaxResults(projectPage.getPageSize());

    Pageable pageable = getPageable(projectPage);

    long projectsCount = getProjectsCount(predicate);

    return new PageImpl<>(typedQuery.getResultList(), pageable, projectsCount);
  }

  private Predicate getPredicate(
      ProjectSearchCriteria projectSearchCriteria, Root<Project> projectRoot) {
    final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    List<Predicate> predicates = new ArrayList<>();
    if (Objects.nonNull(projectSearchCriteria.getStudentFirstName())) {
      predicates.add(
          criteriaBuilder.like(
              projectRoot.get("studentFirstName"),
              "%" + projectSearchCriteria.getStudentFirstName() + "%"));
    }
    if (Objects.nonNull(projectSearchCriteria.getStudentLastName())) {
      predicates.add(
          criteriaBuilder.like(
              projectRoot.get("studentLastName"),
              "%" + projectSearchCriteria.getStudentLastName() + "%"));
    }
    if (Objects.nonNull(projectSearchCriteria.getTopic())) {
      predicates.add(
          criteriaBuilder.like(
              projectRoot.get("topic"),
              "%" + projectSearchCriteria.getTopic() + "%"));
    }
    return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
  }

  private void setOrder(ProjectPage projectPage, CriteriaQuery<Project> criteriaQuery, Root<Project> projectRoot) {
    final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    if (projectPage.getSortDirection().equals(Sort.Direction.ASC)) {
      criteriaQuery.orderBy(criteriaBuilder.asc(projectRoot.get(projectPage.getSortBy())));
    } else {
      criteriaQuery.orderBy(criteriaBuilder.desc(projectRoot.get(projectPage.getSortBy())));
    }
  }

  private Pageable getPageable(ProjectPage projectPage) {
    Sort sort = Sort.by(projectPage.getSortDirection(), projectPage.getSortBy());
    return PageRequest.of(projectPage.getPageNumber(), projectPage.getPageSize(), sort);
  }

  private long getProjectsCount(Predicate predicate) {
    final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
    Root<Project> countRoot = countQuery.from(Project.class);
    countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
    return entityManager.createQuery(countQuery).getSingleResult();
  }
}
