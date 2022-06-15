package com.tusofia.graduatesystem.service;

import com.tusofia.graduatesystem.model.entity.Mentor;
import com.tusofia.graduatesystem.model.request.MentorRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface MentorService {

  ResponseEntity<Long> addMentor(MentorRequest request);

  Page<Mentor> findAllMentors(Pageable pageable);
}
