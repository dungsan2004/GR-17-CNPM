package org.example.dung_s_spring_boot.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "giang_vien")
public class GiangVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String code;

    private String name;

    private String email;

    @ManyToOne
    @JoinColumn(name = "khoa_id")
    private Khoa khoa;

    public GiangVien() {}

    public GiangVien(String code, String name, String email, Khoa khoa) {
        this.code = code;
        this.name = name;
        this.email = email;
        this.khoa = khoa;
    }

    public Long getId() { return id; }
    public String getCode() { return code; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public Khoa getKhoa() { return khoa; }

    public void setId(Long id) { this.id = id; }
    public void setCode(String code) { this.code = code; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setKhoa(Khoa khoa) { this.khoa = khoa; }
}
