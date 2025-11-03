package org.example.dung_s_spring_boot.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class NganhRequest {
    @NotBlank
    private String maNganh;

    @NotBlank
    private String tenNganh;

    @NotNull
    private Long khoaId;

    public String getMaNganh() { return maNganh; }
    public void setMaNganh(String maNganh) { this.maNganh = maNganh; }

    public String getTenNganh() { return tenNganh; }
    public void setTenNganh(String tenNganh) { this.tenNganh = tenNganh; }

    public Long getKhoaId() { return khoaId; }
    public void setKhoaId(Long khoaId) { this.khoaId = khoaId; }
}
