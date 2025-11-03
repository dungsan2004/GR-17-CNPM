package org.example.dung_s_spring_boot.service;

import org.example.dung_s_spring_boot.entity.Khoa;
import org.example.dung_s_spring_boot.repository.KhoaRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class KhoaService {
    private final KhoaRepository repo;

    public KhoaService(KhoaRepository repo) {
        this.repo = repo;
    }

    public List<Khoa> findAll() {
        return repo.findAll();
    }

    public Khoa create(Khoa input) {
        input.setMaKhoa(input.getMaKhoa().trim());
        if (repo.existsByMaKhoa(input.getMaKhoa())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "maKhoa already exists");
        }
        try {
            return repo.save(input);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Duplicate maKhoa", e);
        }
    }

    public Khoa update(Long id, Khoa input) {
        Khoa existed = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Khoa not found"));
        existed.setMaKhoa(input.getMaKhoa());
        existed.setTenKhoa(input.getTenKhoa());
        existed.setMoTa(input.getMoTa());
        try {
            return repo.save(existed);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Duplicate maKhoa", e);
        }
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Khoa not found");
        }
        repo.deleteById(id);
    }
}
