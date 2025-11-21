package org.example.dung_s_spring_boot.dto;

public class SinhVienResponse {
    private Long id;
    private String code;
    private String name;
    private String email;

    private Long khoaId;
    private String khoaName;

    private Long nganhId;
    private String nganhName;

    private Long lopId;
    private String lopName;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Long getKhoaId() { return khoaId; }
    public void setKhoaId(Long khoaId) { this.khoaId = khoaId; }

    public String getKhoaName() { return khoaName; }
    public void setKhoaName(String khoaName) { this.khoaName = khoaName; }

    public Long getNganhId() { return nganhId; }
    public void setNganhId(Long nganhId) { this.nganhId = nganhId; }

    public String getNganhName() { return nganhName; }
    public void setNganhName(String nganhName) { this.nganhName = nganhName; }

    public Long getLopId() { return lopId; }
    public void setLopId(Long lopId) { this.lopId = lopId; }

    public String getLopName() { return lopName; }
    public void setLopName(String lopName) { this.lopName = lopName; }
}
