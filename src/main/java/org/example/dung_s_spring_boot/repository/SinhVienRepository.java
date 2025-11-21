package org.example.dung_s_spring_boot.repository;

import org.example.dung_s_spring_boot.entity.SinhVien;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SinhVienRepository extends JpaRepository<SinhVien, Long> {
    List<SinhVien> findByKhoa_Id(Long khoaId);
    List<SinhVien> findByNganh_Id(Long nganhId);
    List<SinhVien> findByLop_Id(Long lopId);
}
