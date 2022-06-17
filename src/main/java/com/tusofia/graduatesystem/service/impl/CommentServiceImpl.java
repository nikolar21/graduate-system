package com.tusofia.graduatesystem.service.impl;

import static com.tusofia.graduatesystem.util.Constants.COMMENT_NOT_FOUND_MESSAGE;
import static com.tusofia.graduatesystem.util.Constants.PROJECT_NOT_FOUND_MESSAGE;
import static com.tusofia.graduatesystem.util.Constants.USER_NOT_FOUND_MESSAGE;

import com.tusofia.graduatesystem.exception.EntityNotFoundException;
import com.tusofia.graduatesystem.model.dto.CommentDto;
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
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

  private final CommentRepository commentRepository;
  private final ProjectRepository projectRepository;
  private final UserRepository userRepository;
  private final ModelMapper modelMapper = new ModelMapper();

  public ResponseEntity<MessageResponse> deleteComment(Long id) {
    commentRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format(COMMENT_NOT_FOUND_MESSAGE, id)));

    commentRepository.deleteById(id);
    return ResponseEntity.ok().body(new MessageResponse("comment deleted"));
  }

  public CommentDto addComment(CommentRequest commentRequest) {
    Project project = projectRepository.findById(commentRequest.getProjectId()).orElseThrow(() ->
        new EntityNotFoundException(MessageFormat.format(PROJECT_NOT_FOUND_MESSAGE, commentRequest.getProjectId())));
    User user = userRepository.findById(commentRequest.getUserId()).orElseThrow(() ->
        new EntityNotFoundException(MessageFormat.format(USER_NOT_FOUND_MESSAGE, commentRequest.getUserId())));

    Comment comment = new Comment();
    comment.setComment(commentRequest.getComment());
    comment.setUser(user);
    comment.setProject(project);
    return modelMapper.map(commentRepository.save(comment), CommentDto.class);
  }
}
