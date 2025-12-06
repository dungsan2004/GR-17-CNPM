package org.example.dung_s_spring_boot.service;

import org.example.dung_s_spring_boot.dto.DiemRequest;
import org.example.dung_s_spring_boot.dto.DiemResponse;
import org.example.dung_s_spring_boot.entity.Diem;
import org.example.dung_s_spring_boot.entity.SinhVien;
import org.example.dung_s_spring_boot.entity.HocPhan;
import org.example.dung_s_spring_boot.repository.DiemRepository;
import org.example.dung_s_spring_boot.repository.SinhVienRepository;
import org.example.dung_s_spring_boot.repository.HocPhanRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DiemService {
    private final DiemRepository diemRepo;
    private final SinhVienRepository svRepo;
    private final HocPhanRepository hpRepo;

    public DiemService(DiemRepository diemRepo, SinhVienRepository svRepo, HocPhanRepository hpRepo) {
        this.diemRepo = diemRepo;
        this.svRepo = svRepo;
        this.hpRepo = hpRepo;
    }

    public List<DiemResponse> list(Long sinhVienId, Long hocPhanId) {
        List<Diem> items;
        if (sinhVienId != null && hocPhanId != null) items = diemRepo.findBySinhVienIdAndHocPhanId(sinhVienId, hocPhanId);
        else if (sinhVienId != null) items = diemRepo.findBySinhVienId(sinhVienId);
        else if (hocPhanId != null) items = diemRepo.findByHocPhanId(hocPhanId);
        else items = diemRepo.findAll();
        return items.stream().map(this::toDto).collect(Collectors.toList());
    }

    public DiemResponse get(Long id) {
        return diemRepo.findById(id).map(this::toDto).orElse(null);
    }

    public DiemResponse create(DiemRequest req) {
        SinhVien sv = svRepo.findById(req.sinhVienId).orElseThrow(() -> new IllegalArgumentException("SinhVien not found"));
        HocPhan hp = hpRepo.findById(req.hocPhanId).orElseThrow(() -> new IllegalArgumentException("HocPhan not found"));
        Diem d = new Diem();
        d.setValue(req.value);
        d.setSinhVien(sv);
        d.setHocPhan(hp);
        Diem saved = diemRepo.save(d);
        return toDto(saved);
    }

    public DiemResponse update(Long id, DiemRequest req) {
        Diem d = diemRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Diem not found"));
        if (req.value != null) d.setValue(req.value);
        if (req.sinhVienId != null) d.setSinhVien(svRepo.findById(req.sinhVienId).orElseThrow(() -> new IllegalArgumentException("SinhVien not found")));
        if (req.hocPhanId != null) d.setHocPhan(hpRepo.findById(req.hocPhanId).orElseThrow(() -> new IllegalArgumentException("HocPhan not found")));
        return toDto(diemRepo.save(d));
    }

    public void delete(Long id) { diemRepo.deleteById(id); }

    private DiemResponse toDto(Diem d) {
        DiemResponse r = new DiemResponse();
        r.id = d.getId();
        r.value = d.getValue();
        r.createdAt = d.getCreatedAt();
        if (d.getSinhVien() != null) {
            r.sinhVienId = d.getSinhVien().getId();
            r.sinhVienName = d.getSinhVien().getName();
        }
        if (d.getHocPhan() != null) {
            r.hocPhanId = d.getHocPhan().getId();
            r.hocPhanName = d.getHocPhan().getName();
        }
        return r;
    }
}
