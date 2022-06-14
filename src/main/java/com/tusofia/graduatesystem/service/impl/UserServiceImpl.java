package com.tusofia.graduatesystem.service.impl;

import com.tusofia.graduatesystem.model.entity.User;
import com.tusofia.graduatesystem.model.response.MessageResponse;
import com.tusofia.graduatesystem.repository.UserRepository;
import com.tusofia.graduatesystem.service.UserService;
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
    userRepository.deleteById(id);
    return ResponseEntity.ok(new MessageResponse("User deleted successfully"));
  }
}
