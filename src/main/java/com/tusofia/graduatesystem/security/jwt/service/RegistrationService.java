package com.tusofia.graduatesystem.security.jwt.service;

import com.tusofia.graduatesystem.model.request.MentorRequest;
import com.tusofia.graduatesystem.model.request.RegistrationRequest;
import com.tusofia.graduatesystem.model.request.StudentRequest;
import com.tusofia.graduatesystem.model.response.MessageResponse;
import org.springframework.http.ResponseEntity;

public interface RegistrationService {
  ResponseEntity<MessageResponse> studentRegistration(StudentRequest signUpRequest);

  ResponseEntity<MessageResponse> mentorRegistration(MentorRequest signUpRequest);

  ResponseEntity<MessageResponse> adminRegistration(RegistrationRequest signUpRequest);
}
