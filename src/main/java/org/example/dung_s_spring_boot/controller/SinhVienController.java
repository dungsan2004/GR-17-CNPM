package org.example.dung_s_spring_boot.controller;

import org.example.dung_s_spring_boot.dto.SinhVienRequest;
import org.example.dung_s_spring_boot.dto.SinhVienResponse;
import org.example.dung_s_spring_boot.service.SinhVienService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sinhvien")
public class SinhVienController {
    private final SinhVienService service;

    public SinhVienController(SinhVienService service) { this.service = service; }

    @GetMapping
    public List<SinhVienResponse> list(@RequestParam(required = false) Long khoaId,
                                       @RequestParam(required = false) Long nganhId,
                                       @RequestParam(required = false) Long lopId) {
        return service.list(khoaId, nganhId, lopId);
    }

    @GetMapping("/{id}")
    public SinhVienResponse get(@PathVariable Long id) {
        return service.get(id);
    }

    @PostMapping
    public SinhVienResponse create(@RequestBody SinhVienRequest req) {
        return service.create(req);
    }

    @PutMapping("/{id}")
    public SinhVienResponse update(@PathVariable Long id, @RequestBody SinhVienRequest req) {
        return service.update(id, req);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
