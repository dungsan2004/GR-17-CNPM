package org.example.dung_s_spring_boot.service;

import org.example.dung_s_spring_boot.dto.LopRequest;
import org.example.dung_s_spring_boot.dto.LopResponse;
import org.example.dung_s_spring_boot.entity.Khoa;
import org.example.dung_s_spring_boot.entity.Nganh;
import org.example.dung_s_spring_boot.entity.Lop;
import org.example.dung_s_spring_boot.repository.KhoaRepository;
import org.example.dung_s_spring_boot.repository.NganhRepository;
import org.example.dung_s_spring_boot.repository.LopRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LopService {

    private final LopRepository lopRepository;
    private final KhoaRepository khoaRepository;
    private final NganhRepository nganhRepository;

    public LopService(LopRepository lopRepository, KhoaRepository khoaRepository, NganhRepository nganhRepository) {
        this.lopRepository = lopRepository;
        this.khoaRepository = khoaRepository;
        this.nganhRepository = nganhRepository;
    }

    @Transactional
    public LopResponse create(LopRequest req) {
        if (lopRepository.existsByCode(req.getCode())) {
            throw new IllegalArgumentException("Class code already exists");
        }
        Khoa khoa = khoaRepository.findById(req.getKhoaId())
                .orElseThrow(() -> new IllegalArgumentException("Khoa not found"));
        Nganh nganh = nganhRepository.findById(req.getNganhId())
                .orElseThrow(() -> new IllegalArgumentException("Nganh not found"));

        Lop lop = new Lop(req.getCode(), req.getName(), khoa, nganh);
        return toResponse(lopRepository.save(lop));
    }

    @Transactional(readOnly = true)
    public List<LopResponse> list(Long khoaId, Long nganhId) {
        List<Lop> data;
        if (khoaId != null && nganhId != null) {
            data = lopRepository.findByKhoa_IdAndNganh_Id(khoaId, nganhId);
        } else if (khoaId != null) {
            data = lopRepository.findByKhoa_Id(khoaId);
        } else if (nganhId != null) {
            data = lopRepository.findByNganh_Id(nganhId);
        } else {
            data = lopRepository.findAll();
        }
        return data.stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public LopResponse get(Long id) {
        Lop lop = lopRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Lop not found"));
        return toResponse(lop);
    }

    @Transactional
    public LopResponse update(Long id, LopRequest req) {
        Lop lop = lopRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Lop not found"));

        if (req.getCode() != null && !req.getCode().equals(lop.getCode()) && lopRepository.existsByCode(req.getCode())) {
            throw new IllegalArgumentException("Class code already exists");
        }
        if (req.getCode() != null) lop.setCode(req.getCode());
        if (req.getName() != null) lop.setName(req.getName());
        if (req.getKhoaId() != null) {
            Khoa khoa = khoaRepository.findById(req.getKhoaId())
                    .orElseThrow(() -> new IllegalArgumentException("Khoa not found"));
            lop.setKhoa(khoa);
        }
        if (req.getNganhId() != null) {
            Nganh nganh = nganhRepository.findById(req.getNganhId())
                    .orElseThrow(() -> new IllegalArgumentException("Nganh not found"));
            lop.setNganh(nganh);
        }
        return toResponse(lopRepository.save(lop));
    }

    @Transactional
    public void delete(Long id) {
        if (!lopRepository.existsById(id)) {
            throw new IllegalArgumentException("Lop not found");
        }
        lopRepository.deleteById(id);
    }

    private LopResponse toResponse(Lop lop) {
        return new LopResponse(
                lop.getId(),
                lop.getCode(),
                lop.getName(),
                lop.getKhoa().getId(),
                lop.getKhoa().getName(),
                lop.getNganh().getId(),
                lop.getNganh().getName()
        );
    }
}
