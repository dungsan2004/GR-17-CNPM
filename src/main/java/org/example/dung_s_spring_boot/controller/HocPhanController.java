package org.example.dung_s_spring_boot.controller;

import jakarta.validation.Valid;
import org.example.dung_s_spring_boot.dto.HocPhanRequest;
import org.example.dung_s_spring_boot.dto.HocPhanResponse;
import org.example.dung_s_spring_boot.service.HocPhanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hoc-phan")
public class HocPhanController {
    private final HocPhanService hocPhanService;

    public HocPhanController(HocPhanService hocPhanService) {
        this.hocPhanService = hocPhanService;
    }

    @PostMapping
    public ResponseEntity<HocPhanResponse> create(@RequestBody @Valid HocPhanRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(hocPhanService.create(request));
    }

    @GetMapping("/{id}")
    public HocPhanResponse get(@PathVariable Long id) {
        return hocPhanService.get(id);
    }

    @GetMapping
    public List<HocPhanResponse> list() {
        return hocPhanService.list();
    }

    @PutMapping("/{id}")
    public HocPhanResponse update(@PathVariable Long id, @RequestBody @Valid HocPhanRequest request) {
        return hocPhanService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        hocPhanService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
