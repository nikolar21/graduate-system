package com.tusofia.graduatesystem;

import com.tusofia.graduatesystem.model.entity.ERole;
import com.tusofia.graduatesystem.model.entity.User;
import com.tusofia.graduatesystem.model.request.RegistrationRequest;
import com.tusofia.graduatesystem.model.response.MessageResponse;
import com.tusofia.graduatesystem.repository.RoleRepository;
import com.tusofia.graduatesystem.repository.UserRepository;
import com.tusofia.graduatesystem.service.RegistrationService;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Objects;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class GraduateSystemApplicationTests {

  @MockBean
  UserRepository userRepository;

  @MockBean
  RoleRepository roleRepository;

  @MockBean
  RegistrationService registrationService;

  @Test
  void contextLoads() {
  }

  @Test
  void testRegisterNewUser() {

    RegistrationRequest registrationRequest = createRegistrationRequest();
    User newUser = createUser(registrationRequest);

    Mockito.when(userRepository.save(Mockito.any(User.class)))
        .thenReturn(newUser);
    Mockito.when(registrationService.userRegistration(Mockito.any(RegistrationRequest.class)))
        .thenReturn(ResponseEntity.ok().body(new MessageResponse("User registered successfully!")));

    ResponseEntity<MessageResponse> response = registrationService.userRegistration(registrationRequest);

    Assertions.assertEquals("test@gmail.com", newUser.getEmail());
    Assertions.assertEquals("User registered successfully!", Objects.requireNonNull(response.getBody()).getMessage());
  }

  private RegistrationRequest createRegistrationRequest() {
    return new RegistrationRequest("Testname", "Testlastname",
        LocalDate.of(1997, 11, 8), "test123", "test@gmail.com",
        Collections.singleton(ERole.ROLE_ADMIN), "test123");
  }

  private User createUser(RegistrationRequest registrationRequest) {
    return new User(registrationRequest.getFirstName(), registrationRequest.getLastName(), registrationRequest.getBirthDate(),
        registrationRequest.getUsername(), registrationRequest.getEmail(), registrationRequest.getPassword());
  }
}
