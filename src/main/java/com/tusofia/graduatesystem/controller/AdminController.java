package com.tusofia.graduatesystem.controller;

import com.tusofia.graduatesystem.model.request.RegistrationRequest;
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

  @PostMapping("/register-user")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<?> registerUser(@Valid @RequestBody RegistrationRequest signUpRequest) {
    return registrationService.userRegistration(signUpRequest);
  }
}
