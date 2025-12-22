package org.example.dung_s_spring_boot.service;

import org.example.dung_s_spring_boot.dto.GiangVienRequest;
import org.example.dung_s_spring_boot.dto.GiangVienResponse;
import org.example.dung_s_spring_boot.entity.GiangVien;
import org.example.dung_s_spring_boot.entity.Khoa;
import org.example.dung_s_spring_boot.repository.GiangVienRepository;
import org.example.dung_s_spring_boot.repository.KhoaRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GiangVienServiceImpl implements GiangVienService {
    private final GiangVienRepository repo;
    private final KhoaRepository khoaRepo;

    public GiangVienServiceImpl(GiangVienRepository repo, KhoaRepository khoaRepo) {
        this.repo = repo;
        this.khoaRepo = khoaRepo;
    }

    @Override
    public List<GiangVienResponse> findAll() {
        return repo.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    public Optional<GiangVienResponse> findById(Long id) {
        return repo.findById(id).map(this::toResponse);
    }

    @Override
    public GiangVienResponse create(GiangVienRequest request) {
        Khoa khoa = null;
        if (request.getKhoaId() != null) {
            khoa = khoaRepo.findById(request.getKhoaId()).orElse(null);
        }
        GiangVien g = new GiangVien(request.getCode(), request.getName(), request.getEmail(), khoa);
        return toResponse(repo.save(g));
    }

    @Override
    public Optional<GiangVienResponse> update(Long id, GiangVienRequest request) {
        return repo.findById(id).map(existing -> {
            existing.setCode(request.getCode());
            existing.setName(request.getName());
            existing.setEmail(request.getEmail());
            if (request.getKhoaId() != null) {
                Khoa k = khoaRepo.findById(request.getKhoaId()).orElse(null);
                existing.setKhoa(k);
            } else {
                existing.setKhoa(null);
            }
            return toResponse(repo.save(existing));
        });
    }

    @Override
    public boolean delete(Long id) {
        if (!repo.existsById(id)) return false;
        repo.deleteById(id);
        return true;
    }

    private GiangVienResponse toResponse(GiangVien g) {
        Long khoaId = g.getKhoa() != null ? g.getKhoa().getId() : null;
        String khoaName = g.getKhoa() != null ? g.getKhoa().getName() : null;
        return new GiangVienResponse(g.getId(), g.getCode(), g.getName(), g.getEmail(), khoaId, khoaName);
    }
}
