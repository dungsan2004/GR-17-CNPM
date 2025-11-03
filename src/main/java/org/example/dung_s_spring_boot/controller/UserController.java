package org.example.dung_s_spring_boot.controller;

import org.example.dung_s_spring_boot.dto.UserResponse;
import org.example.dung_s_spring_boot.entity.User;
import org.example.dung_s_spring_boot.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository repo;
    public UserController(UserRepository repo) { this.repo = repo; }

    private UserResponse toDto(User u) {
        return new UserResponse(u.getId(), u.getUsername(), u.getFullName(), u.getRole(), u.getEnabled());
    }

    @GetMapping
    public List<UserResponse> list() {
        return repo.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getOne(@PathVariable Long id) {
        return repo.findById(id).map(u -> ResponseEntity.ok(toDto(u)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable Long id, @RequestBody User updated) {
        return repo.findById(id).map(u -> {
            u.setFullName(updated.getFullName() != null ? updated.getFullName() : u.getFullName());
            u.setRole(updated.getRole() != null ? updated.getRole() : u.getRole());
            u.setEnabled(updated.getEnabled() != null ? updated.getEnabled() : u.getEnabled());
            // update password only if provided (plain text) — encode before saving
            if (updated.getPassword() != null && !updated.getPassword().isBlank()) {
                u.setPassword(updated.getPassword()); // will be encoded below by controller/service if needed
            }
            User saved = repo.save(u);
            return ResponseEntity.ok(toDto(saved));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
