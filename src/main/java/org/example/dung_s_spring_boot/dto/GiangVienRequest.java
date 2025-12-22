package org.example.dung_s_spring_boot.dto;

public class GiangVienRequest {
    private String code;
    private String name;
    private String email;
    private Long khoaId;

    public GiangVienRequest() {}

    public String getCode() { return code; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public Long getKhoaId() { return khoaId; }

    public void setCode(String code) { this.code = code; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setKhoaId(Long khoaId) { this.khoaId = khoaId; }
}
