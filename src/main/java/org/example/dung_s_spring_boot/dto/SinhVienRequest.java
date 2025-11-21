package org.example.dung_s_spring_boot.dto;

public class SinhVienRequest {
    private String code;
    private String name;
    private String email;
    private Long khoaId;
    private Long nganhId;
    private Long lopId;

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Long getKhoaId() { return khoaId; }
    public void setKhoaId(Long khoaId) { this.khoaId = khoaId; }

    public Long getNganhId() { return nganhId; }
    public void setNganhId(Long nganhId) { this.nganhId = nganhId; }

    public Long getLopId() { return lopId; }
    public void setLopId(Long lopId) { this.lopId = lopId; }
}
