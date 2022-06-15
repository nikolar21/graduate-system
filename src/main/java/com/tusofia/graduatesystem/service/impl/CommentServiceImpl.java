package com.tusofia.graduatesystem.service.impl;

import com.tusofia.graduatesystem.model.entity.Comment;
import com.tusofia.graduatesystem.model.entity.Project;
import com.tusofia.graduatesystem.model.entity.User;
import com.tusofia.graduatesystem.model.request.CommentRequest;
import com.tusofia.graduatesystem.model.response.MessageResponse;
import com.tusofia.graduatesystem.repository.CommentRepository;
import com.tusofia.graduatesystem.repository.ProjectRepository;
import com.tusofia.graduatesystem.repository.UserRepository;
import com.tusofia.graduatesystem.service.CommentService;
import java.util.Optional;
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
    commentRepository.deleteById(id);

    return ResponseEntity.ok().body(new MessageResponse("comment deleted"));
  }

  public ResponseEntity<MessageResponse> addComment(CommentRequest commentRequest) {
    Optional<Project> project = projectRepository.findById(commentRequest.getProjectId());
    Optional<User> user = userRepository.findById(commentRequest.getUserId());

    project.map(
        project1 -> {
          Comment comment = new Comment();
          comment.setComment(commentRequest.getComment());
          user.ifPresent(comment::setUser);
          comment.setProject(project1);
          commentRepository.save(comment);

          return ResponseEntity.ok().body(new MessageResponse("comment added"));
        });

    return ResponseEntity.ok().body(new MessageResponse("comment not added"));
  }
}
