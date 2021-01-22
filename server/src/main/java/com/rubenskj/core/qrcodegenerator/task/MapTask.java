package com.rubenskj.core.qrcodegenerator.task;

import com.rubenskj.core.qrcodegenerator.service.QrCodeMapService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MapTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(MapTask.class);

    private final QrCodeMapService qrCodeMapService;

    public MapTask(QrCodeMapService qrCodeMapService) {
        this.qrCodeMapService = qrCodeMapService;
    }

    @Scheduled(fixedDelay = 3600000, initialDelay = 1000)
    public void resetQRCodeMap() {
        LOGGER.info("Starting cleaning map QR Codes..");

        qrCodeMapService.reset();

        LOGGER.info("Finishing cleaning map QR Codes..");
    }
}
