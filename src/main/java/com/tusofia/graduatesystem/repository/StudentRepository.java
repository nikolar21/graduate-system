package com.tusofia.graduatesystem.repository;

import com.tusofia.graduatesystem.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
