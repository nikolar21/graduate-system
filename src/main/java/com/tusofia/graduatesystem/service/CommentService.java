package com.tusofia.graduatesystem.service;

import com.tusofia.graduatesystem.model.request.CommentRequest;
import com.tusofia.graduatesystem.model.response.MessageResponse;
import org.springframework.http.ResponseEntity;

public interface CommentService {

  public ResponseEntity<MessageResponse> addComment(CommentRequest commentRequest);
  ResponseEntity<MessageResponse> deleteComment(Long id);

}
