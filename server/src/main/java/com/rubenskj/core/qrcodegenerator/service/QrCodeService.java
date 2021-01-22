package com.rubenskj.core.qrcodegenerator.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.rubenskj.core.qrcodegenerator.dto.QRCodeTicket;
import com.rubenskj.core.qrcodegenerator.dto.QrCodeDTO;
import com.rubenskj.core.qrcodegenerator.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.util.UUID;

import static com.rubenskj.core.qrcodegenerator.util.StaticUtil.CONTROLLER_PATH;

@Service
public class QrCodeService {

    private final HttpServletRequest request;
    private final QrCodeMapService qrCodeMapService;

    @Autowired
    public QrCodeService(HttpServletRequest request, QrCodeMapService qrCodeMapService) {
        this.request = request;
        this.qrCodeMapService = qrCodeMapService;
    }

    public QRCodeTicket generateQRCodeImage(QrCodeDTO qrCodeDTO) throws Exception {
        QRCodeWriter barcodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = barcodeWriter.encode(qrCodeDTO.getBarcodeText(), BarcodeFormat.QR_CODE, qrCodeDTO.getWidth(), qrCodeDTO.getHeight());

        BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
        String uuid = UUID.randomUUID().toString();

        this.qrCodeMapService.add(uuid, bufferedImage);

        String requestPath = this.createRequestPath(request);

        return new QRCodeTicket(uuid, requestPath);
    }

    private String createRequestPath(HttpServletRequest request) {
        return request.getRequestURL().toString().replace(request.getRequestURI(), "").concat(CONTROLLER_PATH);
    }

    public BufferedImage getQrCodeFromTicket(String uuid) {
        return this.qrCodeMapService.getQRCodeByTicket(uuid).orElseThrow(() -> new NotFoundException("Wasn't able to find some QRCode with this ticket. Ticket: " + uuid));
    }
}
