package com.tusofia.graduatesystem.service;

import com.tusofia.graduatesystem.model.entity.Mentor;
import com.tusofia.graduatesystem.model.request.MentorRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MentorService {

  Mentor addMentor(MentorRequest request);

  Page<Mentor> findAllMentors(Pageable pageable);

  Mentor getMentorById(Long mentorId);
}
