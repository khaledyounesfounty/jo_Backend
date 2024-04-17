package com.jo.paris2024.services.impl;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.jo.paris2024.entities.Qrcode;
import com.jo.paris2024.repository.QrcodeRepository;
import com.jo.paris2024.services.QrcodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

@Service
public class QrcodeServiceImpl implements QrcodeService {
    @Autowired
    private QrcodeRepository qrcodeRepository;

    @Value("${app.qrcode.storage-path}")
    private String qrCodeBasePath;


    @Override
    public Qrcode creerQrcode(String data) {
        Qrcode qrcode = new Qrcode();
        qrcode.setData(data);
        try {
            qrcode.setQrImage(generateQRCode(data));
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la génération du QRCode");
        }
        return qrcode;
    }

    public static void generateQRCodeImage(String barcodeText, String filePath) throws Exception {
        QRCodeWriter barcodeWriter = new QRCodeWriter();
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

        BitMatrix bitMatrix = barcodeWriter.encode(barcodeText, BarcodeFormat.QR_CODE, 200, 200, hints);

        Path path = Path.of(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
    }

    public String generateQRCode(String barcodeText) throws Exception {
        String fileName = "QRCode_" + System.currentTimeMillis() + ".png";
        String filePath = qrCodeBasePath + fileName;

        File resource = new ClassPathResource(filePath).getFile();

        generateQRCodeImage(barcodeText, resource.getAbsolutePath());
        return fileName;
    }

}
