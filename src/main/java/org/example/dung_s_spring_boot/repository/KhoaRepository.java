package org.example.dung_s_spring_boot.repository;

import org.example.dung_s_spring_boot.entity.Khoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KhoaRepository extends JpaRepository<Khoa, Long> {
    boolean existsByMaKhoa(String maKhoa);
}
