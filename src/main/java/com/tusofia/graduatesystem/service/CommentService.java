package com.tusofia.graduatesystem.service;

import com.tusofia.graduatesystem.model.entity.Comment;
import com.tusofia.graduatesystem.model.request.CommentRequest;
import com.tusofia.graduatesystem.model.response.MessageResponse;
import org.springframework.http.ResponseEntity;

public interface CommentService {

  Comment addComment(CommentRequest commentRequest);

  ResponseEntity<MessageResponse> deleteComment(Long id);
}
