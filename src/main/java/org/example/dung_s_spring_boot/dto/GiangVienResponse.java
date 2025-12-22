package org.example.dung_s_spring_boot.dto;

public class GiangVienResponse {
    private Long id;
    private String code;
    private String name;
    private String email;
    private Long khoaId;
    private String khoaName;

    public GiangVienResponse() {}

    public GiangVienResponse(Long id, String code, String name, String email, Long khoaId, String khoaName) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.email = email;
        this.khoaId = khoaId;
        this.khoaName = khoaName;
    }

    public Long getId() { return id; }
    public String getCode() { return code; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public Long getKhoaId() { return khoaId; }
    public String getKhoaName() { return khoaName; }

    public void setId(Long id) { this.id = id; }
    public void setCode(String code) { this.code = code; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setKhoaId(Long khoaId) { this.khoaId = khoaId; }
    public void setKhoaName(String khoaName) { this.khoaName = khoaName; }
}
