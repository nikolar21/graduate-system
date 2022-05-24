package com.tusofia.graduatesystem.security.jwt.service.impl;

import com.tusofia.graduatesystem.model.entity.*;
import com.tusofia.graduatesystem.model.request.MentorRequest;
import com.tusofia.graduatesystem.model.request.RegistrationRequest;
import com.tusofia.graduatesystem.model.request.StudentRequest;
import com.tusofia.graduatesystem.model.response.MessageResponse;
import com.tusofia.graduatesystem.repository.MentorRepository;
import com.tusofia.graduatesystem.repository.RoleRepository;
import com.tusofia.graduatesystem.repository.StudentRepository;
import com.tusofia.graduatesystem.repository.UserRepository;
import com.tusofia.graduatesystem.security.jwt.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

  private final UserRepository userRepository;
  private final StudentRepository studentRepository;
  private final MentorRepository mentorRepository;
  private final RoleRepository roleRepository;

  private final PasswordEncoder encoder;

  public ResponseEntity<MessageResponse> studentRegistration(StudentRequest signUpRequest) {
    ResponseEntity<MessageResponse> body = checkIfUsernameOrEmailIsAlreadyTaken(signUpRequest);
    if (body != null)
      return body;

    // Create new user's account
    User user = new User(signUpRequest.getUsername(),
            signUpRequest.getEmail(),
            encoder.encode(signUpRequest.getPassword()));

    Student student = new Student();
    student.setCourse(signUpRequest.getCourse());
    student.setFirstName(signUpRequest.getFirstName());
    student.setLastName(signUpRequest.getLastName());

    Set<Role> roles = new HashSet<>();

    Role userRole = roleRepository.findByName(ERole.ROLE_STUDENT)
            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
    roles.add(userRole);

    student.setUser(user);
    user.setRoles(roles);
    userRepository.save(user);
    studentRepository.save(student);

    return ResponseEntity.ok(new MessageResponse("Student registered successfully!"));
  }

  public ResponseEntity<MessageResponse> mentorRegistration(MentorRequest signUpRequest) {
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity.badRequest()
          .body(new MessageResponse("Error: Username is already taken!"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity.badRequest()
          .body(new MessageResponse("Error: Email is already in use!"));
    }

    // Create new user's account
    User user = new User(signUpRequest.getUsername(),
            signUpRequest.getEmail(),
            encoder.encode(signUpRequest.getPassword()));

    Mentor mentor = new Mentor();
    mentor.setDiscipline(signUpRequest.getDiscipline());
    mentor.setFirstName(signUpRequest.getFirstName());
    mentor.setLastName(signUpRequest.getLastName());

    Set<ERole> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();

    strRoles.forEach(role -> {
      switch (role) {
        case ROLE_ADMIN -> {
          Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                  .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(adminRole);
        }
        case ROLE_MENTOR -> {
          Role mentorRole = roleRepository.findByName(ERole.ROLE_MENTOR)
                  .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(mentorRole);
        }
      }
    });


    mentor.setUser(user);
    user.setRoles(roles);
    userRepository.save(user);
    mentorRepository.save(mentor);

    return ResponseEntity.ok(new MessageResponse("Mentor registered successfully!"));
  }

  public ResponseEntity<MessageResponse> adminRegistration(RegistrationRequest signUpRequest) {
    ResponseEntity<MessageResponse> body = checkIfUsernameOrEmailIsAlreadyTaken(signUpRequest);
    if (body != null)
      return body;

    // Create new user's account
    User user = new User(signUpRequest.getUsername(),
            signUpRequest.getEmail(),
            encoder.encode(signUpRequest.getPassword()));

    Set<Role> roles = new HashSet<>();

    Role userRole = roleRepository.findByName(ERole.ROLE_ADMIN)
            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
    roles.add(userRole);

    user.setRoles(roles);
    userRepository.save(user);

    return ResponseEntity.ok(new MessageResponse("Admin registered successfully!"));
  }

  private ResponseEntity<MessageResponse> checkIfUsernameOrEmailIsAlreadyTaken(RegistrationRequest signUpRequest) {
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity.badRequest()
              .body(new MessageResponse("Error: Username is already taken!"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity.badRequest()
              .body(new MessageResponse("Error: Email is already in use!"));
    }
    return null;
  }
}
