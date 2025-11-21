package org.example.dung_s_spring_boot.dto;

public class HocPhanResponse {
    private Long id;
    private String code;
    private String name;
    private Long nganhId;
    private String nganhName;

    public HocPhanResponse() {}

    public HocPhanResponse(Long id, String code, String name, Long nganhId, String nganhName) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.nganhId = nganhId;
        this.nganhName = nganhName;
    }

    public Long getId() { return id; }
    public String getCode() { return code; }
    public String getName() { return name; }
    public Long getNganhId() { return nganhId; }
    public String getNganhName() { return nganhName; }

    public void setId(Long id) { this.id = id; }
    public void setCode(String code) { this.code = code; }
    public void setName(String name) { this.name = name; }
    public void setNganhId(Long nganhId) { this.nganhId = nganhId; }
    public void setNganhName(String nganhName) { this.nganhName = nganhName; }
}
