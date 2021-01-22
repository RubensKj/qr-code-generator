package com.rubenskj.core.qrcodegenerator.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class QrCodeDTO {

    @NotBlank(message = "BarcodeText cannot be blank")
    @NotNull(message = "BarcodeText cannot be null")
    private String barcodeText;
    private Integer width = 200;
    private Integer height = 200;

    public QrCodeDTO() {
    }

    public QrCodeDTO(String barcodeText, Integer width, Integer height) {
        this.barcodeText = barcodeText;
        this.width = width;
        this.height = height;
    }

    public String getBarcodeText() {
        return barcodeText;
    }

    public Integer getWidth() {
        return width;
    }

    public Integer getHeight() {
        return height;
    }
}
