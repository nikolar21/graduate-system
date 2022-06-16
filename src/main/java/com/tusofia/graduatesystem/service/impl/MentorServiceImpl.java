package com.tusofia.graduatesystem.service.impl;

import com.tusofia.graduatesystem.model.entity.Mentor;
import com.tusofia.graduatesystem.model.request.MentorRequest;
import com.tusofia.graduatesystem.repository.MentorRepository;
import com.tusofia.graduatesystem.service.MentorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MentorServiceImpl implements MentorService {

  private final MentorRepository mentorRepository;

  @Override
  public Mentor addMentor(MentorRequest request) {
    Mentor mentor = new Mentor();
    mentor.setFirstname(request.getMentorFirstName());
    mentor.setLastname(request.getMentorLastName());
    mentor.setFaculty(request.getMentorFaculty());
    mentor.setDisciplines(request.getMentorDiscipline());

    return mentorRepository.save(mentor);
  }

  @Override
  public Page<Mentor> findAllMentors(Pageable pageable) {
    return mentorRepository.findAllMentors(pageable);
  }
}
