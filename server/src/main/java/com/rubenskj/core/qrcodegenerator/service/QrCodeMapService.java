package com.rubenskj.core.qrcodegenerator.service;

import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.*;

@Service
public class QrCodeMapService {

    private static final Map<String, BufferedImage> QR_CODES_GENERATED = new HashMap<>();

    public void add(String uuid, BufferedImage image) {
        QR_CODES_GENERATED.put(uuid, image);
    }

    public List<BufferedImage> findAll() {
        return new ArrayList<>(QR_CODES_GENERATED.values());
    }

    public Optional<BufferedImage> getQRCodeByTicket(String uuid) {
        BufferedImage bufferedImage = QR_CODES_GENERATED.get(uuid);

        if (bufferedImage == null) {
            return Optional.empty();
        }

        return Optional.of(bufferedImage);
    }

    public void reset() {
        QR_CODES_GENERATED.clear();
    }
}
