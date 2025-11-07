package org.example.dung_s_spring_boot.dto;

public class LopResponse {
    private Long id;
    private String code;
    private String name;
    private Long khoaId;
    private String khoaName;
    private Long nganhId;
    private String nganhName;

    public LopResponse() {}

    public LopResponse(Long id, String code, String name,
                       Long khoaId, String khoaName,
                       Long nganhId, String nganhName) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.khoaId = khoaId;
        this.khoaName = khoaName;
        this.nganhId = nganhId;
        this.nganhName = nganhName;
    }

    public Long getId() { return id; }
    public String getCode() { return code; }
    public String getName() { return name; }
    public Long getKhoaId() { return khoaId; }
    public String getKhoaName() { return khoaName; }
    public Long getNganhId() { return nganhId; }
    public String getNganhName() { return nganhName; }

    public void setId(Long id) { this.id = id; }
    public void setCode(String code) { this.code = code; }
    public void setName(String name) { this.name = name; }
    public void setKhoaId(Long khoaId) { this.khoaId = khoaId; }
    public void setKhoaName(String khoaName) { this.khoaName = khoaName; }
    public void setNganhId(Long nganhId) { this.nganhId = nganhId; }
    public void setNganhName(String nganhName) { this.nganhName = nganhName; }
}
