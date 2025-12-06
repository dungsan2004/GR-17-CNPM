package org.example.dung_s_spring_boot.controller;

import org.example.dung_s_spring_boot.dto.DiemRequest;
import org.example.dung_s_spring_boot.dto.DiemResponse;
import org.example.dung_s_spring_boot.service.DiemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/diem")
public class DiemController {
    private final DiemService diemService;
    public DiemController(DiemService diemService) { this.diemService = diemService; }

    @GetMapping
    public List<DiemResponse> list(@RequestParam(required = false) Long sinhVienId,
                                   @RequestParam(required = false) Long hocPhanId) {
        return diemService.list(sinhVienId, hocPhanId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiemResponse> get(@PathVariable Long id) {
        DiemResponse r = diemService.get(id);
        return r == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(r);
    }

    @PostMapping
    public ResponseEntity<DiemResponse> create(@RequestBody DiemRequest req) {
        return ResponseEntity.ok(diemService.create(req));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DiemResponse> update(@PathVariable Long id, @RequestBody DiemRequest req) {
        return ResponseEntity.ok(diemService.update(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        diemService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
