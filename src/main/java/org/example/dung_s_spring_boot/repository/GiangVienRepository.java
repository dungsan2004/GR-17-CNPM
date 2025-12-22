package org.example.dung_s_spring_boot.repository;

import org.example.dung_s_spring_boot.entity.GiangVien;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface GiangVienRepository extends JpaRepository<GiangVien, Long> {
    Optional<GiangVien> findByCode(String code);
}
