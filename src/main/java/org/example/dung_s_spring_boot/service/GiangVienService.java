package org.example.dung_s_spring_boot.service;

import org.example.dung_s_spring_boot.dto.GiangVienRequest;
import org.example.dung_s_spring_boot.dto.GiangVienResponse;
import java.util.List;
import java.util.Optional;

public interface GiangVienService {
    List<GiangVienResponse> findAll();
    Optional<GiangVienResponse> findById(Long id);
    GiangVienResponse create(GiangVienRequest request);
    Optional<GiangVienResponse> update(Long id, GiangVienRequest request);
    boolean delete(Long id);
}
