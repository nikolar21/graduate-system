package com.tusofia.graduatesystem.controller;

import com.tusofia.graduatesystem.model.request.RegistrationRequest;
import com.tusofia.graduatesystem.model.response.MessageResponse;
import com.tusofia.graduatesystem.service.impl.RegistrationServiceImpl;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

  private final RegistrationServiceImpl registrationService;

  @ApiResponses(
      value = {
          @ApiResponse(code = 400, message = "Username or Email already taken"),
          @ApiResponse(
              code = 200,
              message = "Successful registration",
              response = MessageResponse.class,
              responseContainer = "List")
      })
  @PostMapping
  public ResponseEntity<MessageResponse> registerUser(@Valid @RequestBody RegistrationRequest signUpRequest) {
    return registrationService.basicUserRegistration(signUpRequest);
  }
}
