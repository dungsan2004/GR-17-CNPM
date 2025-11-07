package org.example.dung_s_spring_boot.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "khoa", uniqueConstraints = {
        @UniqueConstraint(name = "uk_khoa_ma", columnNames = "ma_khoa")
})
public class Khoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 64)
    @Column(name = "ma_khoa", nullable = false, length = 64)
    private String maKhoa;

    @NotBlank
    @Size(max = 255)
    @Column(name = "ten_khoa", nullable = false, length = 255)
    private String tenKhoa;

    @Column(name = "mo_ta", columnDefinition = "TEXT")
    private String moTa;

    public Khoa() {}
    public Khoa(String maKhoa, String tenKhoa, String moTa) {
        this.maKhoa = maKhoa;
        this.tenKhoa = tenKhoa;
        this.moTa = moTa;
    }

    public Long getId() { return id; }
    public String getMaKhoa() { return maKhoa; }
    public void setMaKhoa(String maKhoa) { this.maKhoa = maKhoa; }
    public String getTenKhoa() { return tenKhoa; }
    public void setTenKhoa(String tenKhoa) { this.tenKhoa = tenKhoa; }
    public String getMoTa() { return moTa; }
    public void setMoTa(String moTa) { this.moTa = moTa; }
    public String getName() { return getTenKhoa(); }
}