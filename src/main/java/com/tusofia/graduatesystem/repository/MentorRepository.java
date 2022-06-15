package com.tusofia.graduatesystem.repository;

import com.tusofia.graduatesystem.model.entity.Mentor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MentorRepository extends JpaRepository<Mentor, Long> {
  @Query("select m from Mentor m")
  Page<Mentor> findAllMentors(Pageable pageable);
}
