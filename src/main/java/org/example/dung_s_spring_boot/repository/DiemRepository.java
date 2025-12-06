package org.example.dung_s_spring_boot.repository;

import org.example.dung_s_spring_boot.entity.Diem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DiemRepository extends JpaRepository<Diem, Long> {
    List<Diem> findBySinhVienId(Long sinhVienId);
    List<Diem> findByHocPhanId(Long hocPhanId);
    List<Diem> findBySinhVienIdAndHocPhanId(Long sinhVienId, Long hocPhanId);
}
