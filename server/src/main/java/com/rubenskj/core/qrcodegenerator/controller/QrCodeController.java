package com.rubenskj.core.qrcodegenerator.controller;

import com.rubenskj.core.qrcodegenerator.dto.QRCodeTicket;
import com.rubenskj.core.qrcodegenerator.dto.QrCodeDTO;
import com.rubenskj.core.qrcodegenerator.service.QrCodeService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.image.BufferedImage;

import static com.rubenskj.core.qrcodegenerator.util.StaticUtil.CONTROLLER_PATH;

@CrossOrigin("*")
@RestController
@RequestMapping(CONTROLLER_PATH)
public class QrCodeController {

    private final QrCodeService qrCodeService;

    public QrCodeController(QrCodeService qrCodeService) {
        this.qrCodeService = qrCodeService;
    }

    @PostMapping(value = "/generate")
    @ResponseBody
    public QRCodeTicket generateQRCode(@Valid @RequestBody QrCodeDTO qrCodeDTO) throws Exception {
        return this.qrCodeService.generateQRCodeImage(qrCodeDTO);
    }

    @GetMapping(value = "/{uuid}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> getQrCodeFromTicket(@PathVariable("uuid") String uuid) {
        return ResponseEntity.ok(this.qrCodeService.getQrCodeFromTicket(uuid));
    }

    @GetMapping(value = "/download/{uuid}")
    public ResponseEntity<BufferedImage> downloadQRCodeFromTicket(@PathVariable("uuid") String uuid) {
        BufferedImage qrCodeFromTicket = this.qrCodeService.getQrCodeFromTicket(uuid);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=qrcode.png")
                .body(qrCodeFromTicket);
    }
}
