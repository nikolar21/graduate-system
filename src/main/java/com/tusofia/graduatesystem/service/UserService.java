package com.tusofia.graduatesystem.service;

import com.tusofia.graduatesystem.model.entity.User;
import com.tusofia.graduatesystem.model.response.MessageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface UserService {
  Page<User> findAllUsers(Pageable pageable);

  ResponseEntity<MessageResponse> deleteUserById(Long id);
}
