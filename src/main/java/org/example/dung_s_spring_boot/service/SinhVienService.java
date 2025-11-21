package org.example.dung_s_spring_boot.service;

import org.example.dung_s_spring_boot.dto.SinhVienRequest;
import org.example.dung_s_spring_boot.dto.SinhVienResponse;
import org.example.dung_s_spring_boot.entity.Khoa;
import org.example.dung_s_spring_boot.entity.Lop;
import org.example.dung_s_spring_boot.entity.Nganh;
import org.example.dung_s_spring_boot.entity.SinhVien;
import org.example.dung_s_spring_boot.repository.KhoaRepository;
import org.example.dung_s_spring_boot.repository.LopRepository;
import org.example.dung_s_spring_boot.repository.NganhRepository;
import org.example.dung_s_spring_boot.repository.SinhVienRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class SinhVienService {
    private final SinhVienRepository repo;
    private final KhoaRepository khoaRepo;
    private final NganhRepository nganhRepo;
    private final LopRepository lopRepo;

    public SinhVienService(SinhVienRepository repo, KhoaRepository khoaRepo,
                           NganhRepository nganhRepo, LopRepository lopRepo) {
        this.repo = repo;
        this.khoaRepo = khoaRepo;
        this.nganhRepo = nganhRepo;
        this.lopRepo = lopRepo;
    }

    @Transactional(readOnly = true)
    public List<SinhVienResponse> list(Long khoaId, Long nganhId, Long lopId) {
        List<SinhVien> data;
        if (lopId != null) data = repo.findByLop_Id(lopId);
        else if (nganhId != null) data = repo.findByNganh_Id(nganhId);
        else if (khoaId != null) data = repo.findByKhoa_Id(khoaId);
        else data = repo.findAll();
        return data.stream().map(this::toDto).toList();
    }

    @Transactional(readOnly = true)
    public SinhVienResponse get(Long id) {
        SinhVien sv = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));
        return toDto(sv);
    }

    @Transactional
    public SinhVienResponse create(SinhVienRequest req) {
        validate(req);
        Khoa khoa = khoaRepo.findById(req.getKhoaId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Khoa not found"));
        Nganh nganh = nganhRepo.findById(req.getNganhId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nganh not found"));
        Lop lop = lopRepo.findById(req.getLopId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lop not found"));

        SinhVien sv = new SinhVien();
        sv.setCode(req.getCode().trim());
        sv.setName(req.getName().trim());
        sv.setEmail(req.getEmail().trim());
        sv.setKhoa(khoa);
        sv.setNganh(nganh);
        sv.setLop(lop);

        return toDto(repo.save(sv));
    }

    @Transactional
    public SinhVienResponse update(Long id, SinhVienRequest req) {
        validate(req);
        SinhVien sv = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));

        Khoa khoa = khoaRepo.findById(req.getKhoaId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Khoa not found"));
        Nganh nganh = nganhRepo.findById(req.getNganhId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nganh not found"));
        Lop lop = lopRepo.findById(req.getLopId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lop not found"));

        sv.setCode(req.getCode().trim());
        sv.setName(req.getName().trim());
        sv.setEmail(req.getEmail().trim());
        sv.setKhoa(khoa);
        sv.setNganh(nganh);
        sv.setLop(lop);

        return toDto(repo.save(sv));
    }

    @Transactional
    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found");
        }
        repo.deleteById(id);
    }

    private void validate(SinhVienRequest req) {
        if (req.getCode() == null || req.getCode().isBlank()
                || req.getName() == null || req.getName().isBlank()
                || req.getEmail() == null || req.getEmail().isBlank()
                || req.getKhoaId() == null || req.getNganhId() == null || req.getLopId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing required fields");
        }
    }

    private SinhVienResponse toDto(SinhVien e) {
        SinhVienResponse r = new SinhVienResponse();
        r.setId(e.getId());
        r.setCode(e.getCode());
        r.setName(e.getName());
        r.setEmail(e.getEmail());
        r.setKhoaId(e.getKhoa().getId());
        r.setKhoaName(e.getKhoa().getName());
        r.setNganhId(e.getNganh().getId());
        r.setNganhName(e.getNganh().getName());
        r.setLopId(e.getLop().getId());
        r.setLopName(e.getLop().getName());
        return r;
    }
}
