package com.tusofia.graduatesystem.controller;

import com.tusofia.graduatesystem.model.dto.UserDto;
import com.tusofia.graduatesystem.model.entity.User;
import com.tusofia.graduatesystem.model.request.RegistrationRequest;
import com.tusofia.graduatesystem.model.response.MessageResponse;
import com.tusofia.graduatesystem.service.UserService;
import com.tusofia.graduatesystem.service.impl.RegistrationServiceImpl;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

  private final RegistrationServiceImpl registrationService;
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
  @PostMapping("/users")
  public ResponseEntity<MessageResponse> registerUser(@Valid @RequestBody RegistrationRequest signUpRequest) {
    return registrationService.userRegistration(signUpRequest);
  }

  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "List of all users",
              response = User.class,
              responseContainer = "List")
      })
  @GetMapping("/users")
  public ResponseEntity<List<UserDto>> getAllUsers(
      @PageableDefault(size = 20) @SortDefault.SortDefaults({
          @SortDefault(sort = "id", direction = Sort.Direction.ASC)}) Pageable pageable) {
    Page<User> users = userService.findAllUsers(pageable);
    Page<UserDto> dtoPage = users.map(user -> modelMapper.map(user, UserDto.class));
    List<UserDto> userDto = dtoPage.getContent();

    return ResponseEntity.ok().body(userDto);
  }

  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "User deleted successfully",
              response = MessageResponse.class,
              responseContainer = "List")
      })
  @DeleteMapping("/users/{id}")
  public ResponseEntity<MessageResponse> deleteUser(@PathVariable Long id) {
    return userService.deleteUserById(id);
  }
}
