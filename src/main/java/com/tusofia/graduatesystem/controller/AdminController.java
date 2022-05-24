package com.tusofia.graduatesystem.controller;

import com.tusofia.graduatesystem.model.request.MentorRequest;
import com.tusofia.graduatesystem.model.request.RegistrationRequest;
import com.tusofia.graduatesystem.model.request.StudentRequest;
import com.tusofia.graduatesystem.model.response.MessageResponse;
import com.tusofia.graduatesystem.security.jwt.service.impl.RegistrationServiceImpl;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

  @ApiResponses(value = {
          @ApiResponse(code = 400, message = "Username or Email already taken"),
          @ApiResponse(code = 200, message = "Successful registration",
                  response = MessageResponse.class, responseContainer = "List")})
  @PostMapping("/register-admin")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<MessageResponse> registerAdmin(@Valid @RequestBody RegistrationRequest signUpRequest) {
    return registrationService.adminRegistration(signUpRequest);
  }

  @ApiResponses(value = {
          @ApiResponse(code = 400, message = "Username or Email already taken"),
          @ApiResponse(code = 200, message = "Successful registration",
                  response = MessageResponse.class, responseContainer = "List")})
  @PostMapping("/register-student")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<MessageResponse> registerStudent(@Valid @RequestBody StudentRequest signUpRequest) {
    return registrationService.studentRegistration(signUpRequest);
  }

  @ApiResponses(value = {
          @ApiResponse(code = 400, message = "Username or Email already taken"),
          @ApiResponse(code = 200, message = "Successful registration",
                  response = MessageResponse.class, responseContainer = "List")})
  @PostMapping("/register-mentor")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<MessageResponse> registerMentor(@Valid @RequestBody MentorRequest signUpRequest) {
    return registrationService.mentorRegistration(signUpRequest);
  }
}
