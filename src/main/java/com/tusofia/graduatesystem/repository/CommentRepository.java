package com.tusofia.graduatesystem.repository;

import com.tusofia.graduatesystem.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
