package org.example.dung_s_spring_boot.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "nganh", uniqueConstraints = {
        @UniqueConstraint(name = "uk_nganh_ma", columnNames = "ma_nganh")
})
public class Nganh {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ma_nganh", nullable = false, length = 50)
    private String maNganh;

    @Column(name = "ten_nganh", nullable = false, length = 255)
    private String tenNganh;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "khoa_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_nganh_khoa"))
    private Khoa khoa;

    public Nganh() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getMaNganh() { return maNganh; }
    public void setMaNganh(String maNganh) { this.maNganh = maNganh; }

    public String getTenNganh() { return tenNganh; }
    public void setTenNganh(String tenNganh) { this.tenNganh = tenNganh; }

    public Khoa getKhoa() { return khoa; }
    public void setKhoa(Khoa khoa) { this.khoa = khoa; }
}
