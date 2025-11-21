package org.example.dung_s_spring_boot.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.dung_s_spring_boot.dto.HocPhanRequest;
import org.example.dung_s_spring_boot.dto.HocPhanResponse;
import org.example.dung_s_spring_boot.entity.HocPhan;
import org.example.dung_s_spring_boot.entity.Nganh;
import org.example.dung_s_spring_boot.repository.HocPhanRepository;
import org.example.dung_s_spring_boot.repository.NganhRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class HocPhanService {
    private final HocPhanRepository hocPhanRepository;
    private final NganhRepository nganhRepository;

    public HocPhanService(HocPhanRepository hocPhanRepository, NganhRepository nganhRepository) {
        this.hocPhanRepository = hocPhanRepository;
        this.nganhRepository = nganhRepository;
    }

    public HocPhanResponse create(HocPhanRequest request) {
        if (hocPhanRepository.existsByCode(request.getCode())) {
            throw new IllegalArgumentException("HocPhan code already exists");
        }
        Nganh nganh = nganhRepository.findById(request.getNganhId())
                .orElseThrow(() -> new EntityNotFoundException("Nganh not found"));
        HocPhan hp = new HocPhan();
        hp.setCode(request.getCode());
        hp.setName(request.getName());
        hp.setNganh(nganh);
        hocPhanRepository.save(hp);
        return map(hp);
    }

    @Transactional(readOnly = true)
    public HocPhanResponse get(Long id) {
        HocPhan hp = hocPhanRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("HocPhan not found"));
        return map(hp);
    }

    @Transactional(readOnly = true)
    public List<HocPhanResponse> list() {
        return hocPhanRepository.findAll().stream().map(this::map).toList();
    }

    public HocPhanResponse update(Long id, HocPhanRequest request) {
        HocPhan hp = hocPhanRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("HocPhan not found"));
        if (!hp.getCode().equals(request.getCode()) && hocPhanRepository.existsByCode(request.getCode())) {
            throw new IllegalArgumentException("HocPhan code already exists");
        }
        if (!hp.getNganh().getId().equals(request.getNganhId())) {
            Nganh nganh = nganhRepository.findById(request.getNganhId())
                    .orElseThrow(() -> new EntityNotFoundException("Nganh not found"));
            hp.setNganh(nganh);
        }
        hp.setCode(request.getCode());
        hp.setName(request.getName());
        return map(hocPhanRepository.save(hp));
    }

    public void delete(Long id) {
        if (!hocPhanRepository.existsById(id)) {
            throw new EntityNotFoundException("HocPhan not found");
        }
        hocPhanRepository.deleteById(id);
    }

    private HocPhanResponse map(HocPhan hp) {
        return new HocPhanResponse(
                hp.getId(),
                hp.getCode(),
                hp.getName(),
                hp.getNganh().getId(),
                hp.getNganh().getName()
        );
    }
}
