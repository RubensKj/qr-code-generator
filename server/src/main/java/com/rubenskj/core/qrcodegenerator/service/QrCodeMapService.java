package com.rubenskj.core.qrcodegenerator.service;

import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QrCodeMapService {

    private static final Map<String, BufferedImage> QR_CODES_GENERATED = new HashMap<>();

    public void add(String uuid, BufferedImage image) {
        QR_CODES_GENERATED.put(uuid, image);
    }

    public List<BufferedImage> findAll() {
        return QR_CODES_GENERATED.values().stream().collect(Collectors.toList());
    }

    public Optional<BufferedImage> getQRCodeByTicket(String uuid) {
        BufferedImage bufferedImage = QR_CODES_GENERATED.get(uuid);

        if (bufferedImage == null) {
            return Optional.empty();
        }

        return Optional.of(bufferedImage);
    }
}
