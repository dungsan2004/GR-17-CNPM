package org.example.dung_s_spring_boot.service;

import java.util.List;

import org.example.dung_s_spring_boot.dto.NganhRequest;
import org.example.dung_s_spring_boot.dto.NganhResponse;
import org.example.dung_s_spring_boot.entity.Khoa;
import org.example.dung_s_spring_boot.entity.Nganh;
import org.example.dung_s_spring_boot.repository.KhoaRepository;
import org.example.dung_s_spring_boot.repository.NganhRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@Service
@Transactional
public class NganhService {
    private final NganhRepository nganhRepository;
    private final KhoaRepository khoaRepository;

    public NganhService(NganhRepository nganhRepository, KhoaRepository khoaRepository) {
        this.nganhRepository = nganhRepository;
        this.khoaRepository = khoaRepository;
    }

    private NganhResponse toDto(Nganh n) {
        Khoa k = n.getKhoa();
        return new NganhResponse(
                n.getId(),
                n.getMaNganh(),
                n.getTenNganh(),
                k != null ? k.getId() : null,
                k != null ? k.getTenKhoa() : null
        );
    }

    @Transactional(readOnly = true)
    public List<NganhResponse> findAll() {
        return nganhRepository.findAll().stream().map(this::toDto).toList();
    }

    @Transactional(readOnly = true)
    public List<NganhResponse> findByKhoa(Long khoaId) {
        return nganhRepository.findAllByKhoa_Id(khoaId).stream().map(this::toDto).toList();
    }

    public NganhResponse create(NganhRequest req) {
        nganhRepository.findByMaNganh(req.getMaNganh()).ifPresent(n -> {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Mã ngành đã tồn tại");
        });
        Khoa khoa = khoaRepository.findById(req.getKhoaId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Khoa không tồn tại"));

        Nganh n = new Nganh();
        n.setMaNganh(req.getMaNganh().trim());
        n.setTenNganh(req.getTenNganh().trim());
        n.setKhoa(khoa);
        return toDto(nganhRepository.save(n));
    }

    public NganhResponse update(Long id, NganhRequest req) {
        Nganh n = nganhRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ngành không tồn tại"));

        if (!n.getMaNganh().equals(req.getMaNganh())) {
            nganhRepository.findByMaNganh(req.getMaNganh()).ifPresent(other -> {
                if (!other.getId().equals(id)) {
                    throw new ResponseStatusException(HttpStatus.CONFLICT, "Mã ngành đã tồn tại");
                }
            });
        }

        Khoa khoa = khoaRepository.findById(req.getKhoaId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Khoa không tồn tại"));

        n.setMaNganh(req.getMaNganh().trim());
        n.setTenNganh(req.getTenNganh().trim());
        n.setKhoa(khoa);
        return toDto(nganhRepository.save(n));
    }

    public void delete(Long id) {
        if (!nganhRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ngành không tồn tại");
        }
        nganhRepository.deleteById(id);
    }
}
