package com.tusofia.graduatesystem.service.impl;

import com.tusofia.graduatesystem.exceptions.UserNotFoundException;
import com.tusofia.graduatesystem.model.entity.User;
import com.tusofia.graduatesystem.model.response.MessageResponse;
import com.tusofia.graduatesystem.repository.UserRepository;
import com.tusofia.graduatesystem.service.UserService;
import java.text.MessageFormat;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Override
  public Page<User> findAllUsers(Pageable pageable) {
    return userRepository.findAllUsers(pageable);
  }

  @Override
  public ResponseEntity<MessageResponse> deleteUserById(Long id) {
    userRepository.findById(id)
        .orElseThrow(() -> new UserNotFoundException(MessageFormat.format("User with id {0} not found!", id)));

    userRepository.deleteById(id);
    return ResponseEntity.ok(new MessageResponse("User deleted successfully"));
  }
}
