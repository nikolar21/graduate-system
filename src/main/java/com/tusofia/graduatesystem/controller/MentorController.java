package com.tusofia.graduatesystem.controller;

import com.tusofia.graduatesystem.model.dto.MentorDto;
import com.tusofia.graduatesystem.model.entity.Mentor;
import com.tusofia.graduatesystem.model.request.MentorRequest;
import com.tusofia.graduatesystem.service.MentorService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/mentors")
@RequiredArgsConstructor
public class MentorController {

  private final MentorService mentorService;
  private final ModelMapper modelMapper = new ModelMapper();

  @PostMapping
  public Mentor addMentor(@Valid @RequestBody MentorRequest request) {
    return mentorService.addMentor(request);
  }

  @GetMapping
  public Page<Mentor> getAllMentors(
      @PageableDefault(size = 20)
          @SortDefault.SortDefaults({@SortDefault(sort = "id", direction = Sort.Direction.ASC)})
          Pageable pageable) {
    return mentorService.findAllMentors(pageable);
  }

  @GetMapping("/{mentorId}")
  public ResponseEntity<MentorDto> getMentor(@PathVariable Long mentorId) {
    Mentor mentor = mentorService.getMentorById(mentorId);

    return ResponseEntity
        .ok()
        .body(modelMapper.map(mentor, MentorDto.class));
  }
}
