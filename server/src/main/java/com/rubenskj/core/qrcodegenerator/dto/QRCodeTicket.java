package com.rubenskj.core.qrcodegenerator.dto;

public class QRCodeTicket {

    private String uuid;
    private String urlPath;

    public QRCodeTicket(String uuid, String requestPath) {
        this.uuid = uuid;
        this.urlPath = requestPath.concat("/").concat(uuid);
    }

    public String getUuid() {
        return uuid;
    }

    public String getUrlPath() {
        return urlPath;
    }
}
