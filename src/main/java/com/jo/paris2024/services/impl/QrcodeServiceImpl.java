package com.jo.paris2024.services.impl;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.jo.paris2024.entities.Billet;
import com.jo.paris2024.entities.Qrcode;
import com.jo.paris2024.repository.QrcodeRepository;
import com.jo.paris2024.services.QrcodeService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Service
public class QrcodeServiceImpl implements QrcodeService {

    @Autowired
    private QrcodeRepository qrcodeRepository;

    @Value("${qr.code.directory}")
    private String qrCodeDirectory;

    private static final Logger logger = Logger.getLogger(QrcodeServiceImpl.class.getName());


    @Transactional
    @Override
    public Qrcode createAndSaveQRCode(String data, Billet idBillet) {
        try
        {
            String qrCodeFileName = System.currentTimeMillis() + ".svg";
            String qrCodeFilePath = qrCodeDirectory + qrCodeFileName;

            String svgContent = generateQRCodeSVG(data, 250, 250);
            Files.write(Paths.get(qrCodeFilePath), svgContent.getBytes(StandardCharsets.UTF_8));

            Qrcode qrCode = new Qrcode();
            qrCode.setData(data);
            qrCode.setQrImage(svgContent);
            qrCode.setBillet(idBillet);
            Logger.getLogger(QrcodeServiceImpl.class.getName()).info("QRCode created: " + qrCodeFilePath);

            return qrcodeRepository.save(qrCode);
        }catch (Exception e)
        {
            logger.severe("Erreur lors de la génération du QRCode: " + e.getMessage());
            return null;
        }
    }
    public static String generateQRCodeSVG(String data, int width, int height) throws Exception {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

        BitMatrix bitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, width, height, hints);

        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        sb.append("<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"" + width + "\" height=\"" + height + "\">");
        sb.append("<rect width=\"100%\" height=\"100%\" fill=\"#FFFFFF\"/>");

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (bitMatrix.get(x, y)) {
                    sb.append("<rect x=\"" + x + "\" y=\"" + y + "\" width=\"1\" height=\"1\" fill=\"#000000\"/>");
                }
            }
        }

        sb.append("</svg>");
        return sb.toString();
    }
    private void generateQRCodeImage(String data, int width, int height, String filePath) throws Exception {
        Path path = Paths.get(filePath);
        Files.createDirectories(path.getParent());  // This will create the directory if it doesn't exist

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, width, height);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
    }
}
