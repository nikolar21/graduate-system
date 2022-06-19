package com.tusofia.graduatesystem.service;

import com.tusofia.graduatesystem.model.entity.User;
import com.tusofia.graduatesystem.model.response.MessageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

  Page<User> findAllUsers(Pageable pageable);

  MessageResponse deleteUserById(Long id);

  User getUserById(Long userId);
}
