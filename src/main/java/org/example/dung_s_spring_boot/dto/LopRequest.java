package org.example.dung_s_spring_boot.dto;

public class LopRequest {
    private String code;
    private String name;
    private Long khoaId;
    private Long nganhId;

    public LopRequest() {}

    public String getCode() { return code; }
    public String getName() { return name; }
    public Long getKhoaId() { return khoaId; }
    public Long getNganhId() { return nganhId; }

    public void setCode(String code) { this.code = code; }
    public void setName(String name) { this.name = name; }
    public void setKhoaId(Long khoaId) { this.khoaId = khoaId; }
    public void setNganhId(Long nganhId) { this.nganhId = nganhId; }
}
