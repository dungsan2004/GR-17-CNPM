package org.example.dung_s_spring_boot.controller;

import jakarta.validation.Valid;
import org.example.dung_s_spring_boot.entity.Khoa;
import org.example.dung_s_spring_boot.service.KhoaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/khoa")
public class KhoaController {
    private final KhoaService service;

    public KhoaController(KhoaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Khoa> list() {
        return service.findAll();
    }

    @PostMapping
    public ResponseEntity<Khoa> create(@Valid @RequestBody Khoa body) {
        Khoa saved = service.create(body);
        return ResponseEntity.created(URI.create("/api/khoa/" + saved.getId())).body(saved);
    }

    @PutMapping("/{id}")
    public Khoa update(@PathVariable Long id, @Valid @RequestBody Khoa body) {
        return service.update(id, body);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
