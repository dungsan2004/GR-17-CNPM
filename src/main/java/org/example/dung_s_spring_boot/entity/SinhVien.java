package org.example.dung_s_spring_boot.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "sinh_vien", uniqueConstraints = {
        @UniqueConstraint(name = "uk_sinh_vien_code", columnNames = "code"),
        @UniqueConstraint(name = "uk_sinh_vien_email", columnNames = "email")
})
public class SinhVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 64)
    private String code;

    @Column(nullable = false, length = 128)
    private String name;

    @Column(nullable = false, length = 128)
    private String email;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "khoa_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_sinh_vien_khoa"))
    private Khoa khoa;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "nganh_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_sinh_vien_nganh"))
    private Nganh nganh;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "lop_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_sinh_vien_lop"))
    private Lop lop;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Khoa getKhoa() { return khoa; }
    public void setKhoa(Khoa khoa) { this.khoa = khoa; }

    public Nganh getNganh() { return nganh; }
    public void setNganh(Nganh nganh) { this.nganh = nganh; }

    public Lop getLop() { return lop; }
    public void setLop(Lop lop) { this.lop = lop; }
}
