package com.tusofia.graduatesystem.controller;

import com.tusofia.graduatesystem.model.entity.Mentor;
import com.tusofia.graduatesystem.model.request.MentorRequest;
import com.tusofia.graduatesystem.service.MentorService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mentors")
@RequiredArgsConstructor
public class MentorController {

  private final MentorService mentorService;

  @PostMapping
  public ResponseEntity<Long> addMentor(@Valid @RequestBody MentorRequest request) {
    return mentorService.addMentor(request);
  }

  @GetMapping
  public Page<Mentor> getAllMentors(
      @PageableDefault(size = 20)
          @SortDefault.SortDefaults({@SortDefault(sort = "id", direction = Sort.Direction.ASC)})
          Pageable pageable) {
    return mentorService.findAllMentors(pageable);
  }
}
