package com.tusofia.graduatesystem.service.impl;

import static com.tusofia.graduatesystem.util.Constants.MENTOR_NOT_FOUND_MESSAGE;

import com.tusofia.graduatesystem.exception.EntityNotFoundException;
import com.tusofia.graduatesystem.model.entity.Mentor;
import com.tusofia.graduatesystem.model.request.MentorRequest;
import com.tusofia.graduatesystem.repository.MentorRepository;
import com.tusofia.graduatesystem.service.MentorService;
import java.text.MessageFormat;
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
    mentor.setTitle(request.getMentorTitle());

    return mentorRepository.save(mentor);
  }

  @Override
  public Page<Mentor> findAllMentors(Pageable pageable) {
    return mentorRepository.findAllMentors(pageable);
  }

  @Override
  public Mentor getMentorById(Long mentorId) {
    return mentorRepository.findById(mentorId)
        .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format(MENTOR_NOT_FOUND_MESSAGE, mentorId)));
  }
}
