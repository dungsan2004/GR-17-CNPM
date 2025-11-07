package org.example.dung_s_spring_boot.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "nganh", uniqueConstraints = {
        @UniqueConstraint(name = "uk_nganh_ma", columnNames = "ma_nganh")
})
public class Nganh {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 64)
    @Column(name = "ma_nganh", nullable = false, length = 64)
    private String maNganh;

    @NotBlank
    @Size(max = 255)
    @Column(name = "ten_nganh", nullable = false, length = 255)
    private String tenNganh;

    @Column(name = "mo_ta", columnDefinition = "TEXT")
    private String moTa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "khoa_id")
    private Khoa khoa;

    public Nganh() {}

    public Nganh(String maNganh, String tenNganh, String moTa, Khoa khoa) {
        this.maNganh = maNganh;
        this.tenNganh = tenNganh;
        this.moTa = moTa;
        this.khoa = khoa;
    }

    public Long getId() { return id; }

    public String getMaNganh() { return maNganh; }
    public void setMaNganh(String maNganh) { this.maNganh = maNganh; }

    public String getTenNganh() { return tenNganh; }
    public void setTenNganh(String tenNganh) { this.tenNganh = tenNganh; }

    public String getMoTa() { return moTa; }
    public void setMoTa(String moTa) { this.moTa = moTa; }

    public Khoa getKhoa() { return khoa; }
    public void setKhoa(Khoa khoa) { this.khoa = khoa; }

    public String getName() { return getTenNganh(); }
}
