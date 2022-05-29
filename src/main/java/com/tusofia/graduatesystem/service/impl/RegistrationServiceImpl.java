package com.tusofia.graduatesystem.service.impl;

import com.tusofia.graduatesystem.model.entity.ERole;
import com.tusofia.graduatesystem.model.entity.Role;
import com.tusofia.graduatesystem.model.entity.User;
import com.tusofia.graduatesystem.model.request.RegistrationRequest;
import com.tusofia.graduatesystem.model.response.MessageResponse;
import com.tusofia.graduatesystem.repository.RoleRepository;
import com.tusofia.graduatesystem.repository.UserRepository;
import com.tusofia.graduatesystem.service.RegistrationService;
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
  private final RoleRepository roleRepository;

  private final PasswordEncoder encoder;

  public ResponseEntity<MessageResponse> userRegistration(RegistrationRequest signUpRequest) {
    ResponseEntity<MessageResponse> body = checkIfUsernameOrEmailIsAlreadyTaken(signUpRequest);
    if (body != null)
      return body;

    // Create new user's account
    User user = new User(signUpRequest.getFirstName(),
            signUpRequest.getLastName(),
            signUpRequest.getBirthDate(),
            signUpRequest.getUsername(),
            signUpRequest.getEmail(),
            encoder.encode(signUpRequest.getPassword()));

    Set<ERole> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Role userRole = roleRepository.findByName(ERole.ROLE_USER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
          case ROLE_ADMIN -> {
            Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(adminRole);
          }
          case ROLE_INSPECTOR -> {
            Role modRole = roleRepository.findByName(ERole.ROLE_INSPECTOR)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(modRole);
          }
          default -> {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
          }
        }
      });
    }

    user.setRoles(roles);
    userRepository.save(user);

    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }

  public ResponseEntity<MessageResponse> basicUserRegistration(RegistrationRequest signUpRequest) {
    ResponseEntity<MessageResponse> body = checkIfUsernameOrEmailIsAlreadyTaken(signUpRequest);
    if (body != null)
      return body;

    // Create new user's account
    User user = new User(signUpRequest.getFirstName(),
            signUpRequest.getLastName(),
            signUpRequest.getBirthDate(),
            signUpRequest.getUsername(),
            signUpRequest.getEmail(),
            encoder.encode(signUpRequest.getPassword()));

    Set<Role> roles = new HashSet<>();

    Role userRole = roleRepository.findByName(ERole.ROLE_USER)
            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
    roles.add(userRole);

    user.setRoles(roles);
    userRepository.save(user);

    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
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
