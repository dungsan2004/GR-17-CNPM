package org.example.dung_s_spring_boot.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "diem")
public class Diem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sinh_vien_id")
    private SinhVien sinhVien;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hoc_phan_id")
    private HocPhan hocPhan;

    private LocalDateTime createdAt;

    public Diem() { this.createdAt = LocalDateTime.now(); }

    // getters / setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Double getValue() { return value; }
    public void setValue(Double value) { this.value = value; }
    public SinhVien getSinhVien() { return sinhVien; }
    public void setSinhVien(SinhVien sinhVien) { this.sinhVien = sinhVien; }
    public HocPhan getHocPhan() { return hocPhan; }
    public void setHocPhan(HocPhan hocPhan) { this.hocPhan = hocPhan; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
