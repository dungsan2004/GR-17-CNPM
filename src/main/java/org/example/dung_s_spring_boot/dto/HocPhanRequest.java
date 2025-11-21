package org.example.dung_s_spring_boot.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class HocPhanRequest {
    @NotBlank
    @Size(max = 64)
    private String code;

    @NotBlank
    @Size(max = 255)
    private String name;

    @NotNull
    private Long nganhId;

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Long getNganhId() { return nganhId; }
    public void setNganhId(Long nganhId) { this.nganhId = nganhId; }
}
