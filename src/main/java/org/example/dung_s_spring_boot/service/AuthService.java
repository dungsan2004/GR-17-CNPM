package org.example.dung_s_spring_boot.service;

import org.example.dung_s_spring_boot.dto.LoginRequest;
import org.example.dung_s_spring_boot.dto.RegisterRequest;
import org.example.dung_s_spring_boot.dto.UserResponse;
import org.example.dung_s_spring_boot.entity.User;
import org.example.dung_s_spring_boot.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse register(RegisterRequest req) {
        if (req.password == null || !req.password.equals(req.confirmPassword)) {
            throw new RuntimeException("Mật khẩu không khớp");
        }
        // Email is stored as username
        if (userRepository.existsByUsername(req.email)) {
            throw new RuntimeException("Đia chỉ email đã được đăng ký");
        }
        if (userRepository.existsByStudentId(req.studentId)) {
            throw new RuntimeException("Mã sinh viên đã được đăng ký");
        }

        User u = new User();
        u.setUsername(req.email);
        u.setPassword(passwordEncoder.encode(req.password));
        u.setFullName(req.fullName);
        u.setStudentId(req.studentId);
        u.setRole("STUDENT");
        u.setEnabled(true);

        User saved = userRepository.save(u);
        return new UserResponse(saved.getId(), saved.getUsername(), saved.getFullName(), saved.getRole(), saved.getEnabled());
    }

    public UserResponse login(LoginRequest req) {
        Optional<User> opt = userRepository.findByUsername(req.username); // username = email
        if (opt.isEmpty()) throw new RuntimeException("Chưng thực không hợp lệ");
        User u = opt.get();
        if (!passwordEncoder.matches(req.password, u.getPassword())) {
            throw new RuntimeException("Chưng thực không hợp lệ");
        }
        return new UserResponse(u.getId(), u.getUsername(), u.getFullName(), u.getRole(), u.getEnabled());
    }
}
