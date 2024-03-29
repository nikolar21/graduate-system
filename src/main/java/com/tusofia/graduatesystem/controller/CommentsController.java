package com.tusofia.graduatesystem.controller;

import com.tusofia.graduatesystem.model.dto.CommentDto;
import com.tusofia.graduatesystem.model.request.CommentRequest;
import com.tusofia.graduatesystem.model.response.MessageResponse;
import com.tusofia.graduatesystem.service.CommentService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentsController {

  private final CommentService commentService;

  @PostMapping
  public CommentDto addComment(@Valid @RequestBody CommentRequest commentRequest) {
    return commentService.addComment(commentRequest);
  }

  @DeleteMapping("/{commentId}")
  public ResponseEntity<MessageResponse> deleteComment(@PathVariable Long commentId) {
    return commentService.deleteComment(commentId);
  }
}
