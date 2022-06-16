package com.tusofia.graduatesystem.service.impl;

import com.tusofia.graduatesystem.exceptions.CommentNotFoundException;
import com.tusofia.graduatesystem.exceptions.ProjectNotFoundException;
import com.tusofia.graduatesystem.exceptions.UserNotFoundException;
import com.tusofia.graduatesystem.model.entity.Comment;
import com.tusofia.graduatesystem.model.entity.Project;
import com.tusofia.graduatesystem.model.entity.User;
import com.tusofia.graduatesystem.model.request.CommentRequest;
import com.tusofia.graduatesystem.model.response.MessageResponse;
import com.tusofia.graduatesystem.repository.CommentRepository;
import com.tusofia.graduatesystem.repository.ProjectRepository;
import com.tusofia.graduatesystem.repository.UserRepository;
import com.tusofia.graduatesystem.service.CommentService;
import java.text.MessageFormat;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

  private final CommentRepository commentRepository;
  private final ProjectRepository projectRepository;
  private final UserRepository userRepository;

  public ResponseEntity<MessageResponse> deleteComment(Long id) {
    commentRepository.findById(id)
        .orElseThrow(() -> new CommentNotFoundException(MessageFormat.format("Comment with id {0} not found!", id)));

    commentRepository.deleteById(id);
    return ResponseEntity.ok().body(new MessageResponse("comment deleted"));
  }

  public Comment addComment(CommentRequest commentRequest) {
    Project project = projectRepository.findById(commentRequest.getProjectId()).orElseThrow(() ->
        new ProjectNotFoundException(MessageFormat.format("Project with id {0} not found!", commentRequest.getProjectId())));
    User user = userRepository.findById(commentRequest.getUserId()).orElseThrow(() ->
        new UserNotFoundException(MessageFormat.format("User with id {0} not found!", commentRequest.getUserId())));

    Comment comment = new Comment();
    comment.setComment(commentRequest.getComment());
    comment.setUser(user);
    comment.setProject(project);
    return commentRepository.save(comment);
  }
}
