package org.example.dung_s_spring_boot.repository;
import java.util.List;
import java.util.Optional;

import org.example.dung_s_spring_boot.entity.Nganh;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NganhRepository extends JpaRepository<Nganh, Long> {
    Optional<Nganh> findByMaNganh(String maNganh);
    List<Nganh> findAllByKhoa_Id(Long khoaId);
}
