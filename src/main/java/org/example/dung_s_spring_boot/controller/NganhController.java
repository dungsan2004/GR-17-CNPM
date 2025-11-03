package org.example.dung_s_spring_boot.controller;

import java.util.List;

import org.example.dung_s_spring_boot.dto.NganhRequest;
import org.example.dung_s_spring_boot.dto.NganhResponse;
import org.example.dung_s_spring_boot.service.NganhService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/nganh")
public class NganhController {

    private final NganhService nganhService;

    public NganhController(NganhService nganhService) {
        this.nganhService = nganhService;
    }

    @GetMapping
    public List<NganhResponse> all() {
        return nganhService.findAll();
    }

    @GetMapping("/by-khoa/{khoaId}")
    public List<NganhResponse> byKhoa(@PathVariable Long khoaId) {
        return nganhService.findByKhoa(khoaId);
    }

    @PostMapping
    public ResponseEntity<NganhResponse> create(@Validated @RequestBody NganhRequest req) {
        return ResponseEntity.ok(nganhService.create(req));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NganhResponse> update(@PathVariable Long id, @Validated @RequestBody NganhRequest req) {
        return ResponseEntity.ok(nganhService.update(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        nganhService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
