package com.jo.paris2024.services;

import com.jo.paris2024.entities.Billet;
import com.jo.paris2024.entities.Qrcode;

public interface QrcodeService {
    Qrcode createAndSaveQRCode(String data, Billet idBillet);
}
