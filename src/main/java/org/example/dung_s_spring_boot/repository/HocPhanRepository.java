package org.example.dung_s_spring_boot.repository;

import org.example.dung_s_spring_boot.entity.HocPhan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HocPhanRepository extends JpaRepository<HocPhan, Long> {
    Optional<HocPhan> findByCode(String code);
    boolean existsByCode(String code);
}
