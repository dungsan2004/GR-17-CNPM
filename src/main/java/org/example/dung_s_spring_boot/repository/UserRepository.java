package org.example.dung_s_spring_boot.repository;

import java.util.Optional;
import org.example.dung_s_spring_boot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByStudentId(String studentId);
}
