package org.example.dung_s_spring_boot.dto;

public class NganhResponse {
    private Long id;
    private String maNganh;
    private String tenNganh;
    private Long khoaId;
    private String khoaTen;

    public NganhResponse(Long id, String maNganh, String tenNganh, Long khoaId, String khoaTen) {
        this.id = id;
        this.maNganh = maNganh;
        this.tenNganh = tenNganh;
        this.khoaId = khoaId;
        this.khoaTen = khoaTen;
    }

    public Long getId() { return id; }
    public String getMaNganh() { return maNganh; }
    public String getTenNganh() { return tenNganh; }
    public Long getKhoaId() { return khoaId; }
    public String getKhoaTen() { return khoaTen; }
}
