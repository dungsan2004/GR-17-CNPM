package org.example.dung_s_spring_boot.repository;

import org.example.dung_s_spring_boot.entity.Lop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LopRepository extends JpaRepository<Lop, Long> {
    List<Lop> findByKhoa_Id(Long khoaId);
    List<Lop> findByNganh_Id(Long nganhId);
    List<Lop> findByKhoa_IdAndNganh_Id(Long khoaId, Long nganhId);
    boolean existsByCode(String code);
}
