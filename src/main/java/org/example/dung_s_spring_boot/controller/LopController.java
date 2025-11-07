package org.example.dung_s_spring_boot.controller;

import org.example.dung_s_spring_boot.dto.LopRequest;
import org.example.dung_s_spring_boot.dto.LopResponse;
import org.example.dung_s_spring_boot.service.LopService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lop")
public class LopController {

    private final LopService lopService;

    public LopController(LopService lopService) {
        this.lopService = lopService;
    }

    @PostMapping
    public ResponseEntity<LopResponse> create(@RequestBody LopRequest req) {
        return ResponseEntity.ok(lopService.create(req));
    }

    @GetMapping
    public ResponseEntity<List<LopResponse>> list(
            @RequestParam(required = false) Long khoaId,
            @RequestParam(required = false) Long nganhId) {
        return ResponseEntity.ok(lopService.list(khoaId, nganhId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LopResponse> get(@PathVariable Long id) {
        return ResponseEntity.ok(lopService.get(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LopResponse> update(@PathVariable Long id, @RequestBody LopRequest req) {
        return ResponseEntity.ok(lopService.update(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        lopService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
