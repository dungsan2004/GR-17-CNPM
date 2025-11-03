package org.example.dung_s_spring_boot.user;

import org.example.dung_s_spring_boot.entity.User;
import org.example.dung_s_spring_boot.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository repo;
    private final PasswordEncoder encoder;

    public UserService(UserRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    public User register(String username, String rawPassword) {
        User u = new User();
        u.setUsername(username);
        u.setPassword(encoder.encode(rawPassword));
        u.setRole("USER");
        u.setEnabled(true);
        return repo.save(u);
    }
}
