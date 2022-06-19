package com.tusofia.graduatesystem.controller;

import com.tusofia.graduatesystem.model.dto.UserDto;
import com.tusofia.graduatesystem.model.entity.User;
import com.tusofia.graduatesystem.model.request.RegistrationRequest;
import com.tusofia.graduatesystem.model.response.MessageResponse;
import com.tusofia.graduatesystem.service.RegistrationService;
import com.tusofia.graduatesystem.service.UserService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

  private final RegistrationService registrationService;
  private final UserService userService;
  private final ModelMapper modelMapper = new ModelMapper();

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

  @GetMapping("/{userId}")
  public ResponseEntity<UserDto> getUserInfo(@PathVariable Long userId) {
    User user = userService.getUserById(userId);

    return ResponseEntity
        .ok()
        .body(modelMapper.map(user, UserDto.class));
  }
}
