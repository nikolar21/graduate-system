package com.tusofia.graduatesystem.controller;

import com.tusofia.graduatesystem.model.request.MentorRequest;
import com.tusofia.graduatesystem.model.request.RegistrationRequest;
import com.tusofia.graduatesystem.model.request.StudentRequest;
import com.tusofia.graduatesystem.security.jwt.service.impl.RegistrationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin/")
@RequiredArgsConstructor
public class AdminController {

  private final RegistrationServiceImpl registrationService;

  @PostMapping("/register-admin")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<?> registerAdmin(@Valid @RequestBody RegistrationRequest signUpRequest) {
    return registrationService.adminRegistration(signUpRequest);
  }

  @PostMapping("/register-student")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<?> registerStudent(@Valid @RequestBody StudentRequest signUpRequest) {
    return registrationService.studentRegistration(signUpRequest);
  }

  @PostMapping("/register-mentor")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<?> registerMentor(@Valid @RequestBody MentorRequest signUpRequest) {
    return registrationService.mentorRegistration(signUpRequest);
  }
}
