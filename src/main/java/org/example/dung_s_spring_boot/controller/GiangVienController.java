package org.example.dung_s_spring_boot.controller;

import org.example.dung_s_spring_boot.dto.GiangVienRequest;
import org.example.dung_s_spring_boot.dto.GiangVienResponse;
import org.example.dung_s_spring_boot.service.GiangVienService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/giangviens")
public class GiangVienController {
    private final GiangVienService service;

    public GiangVienController(GiangVienService service) {
        this.service = service;
    }

    @GetMapping
    public List<GiangVienResponse> list() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GiangVienResponse> get(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<GiangVienResponse> create(@RequestBody GiangVienRequest request) {
        GiangVienResponse created = service.create(request);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GiangVienResponse> update(@PathVariable Long id, @RequestBody GiangVienRequest request) {
        return service.update(id, request)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.delete(id)) return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }
}
