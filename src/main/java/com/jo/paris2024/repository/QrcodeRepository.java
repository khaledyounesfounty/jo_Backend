package com.jo.paris2024.repository;

import com.jo.paris2024.entities.Qrcode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QrcodeRepository extends JpaRepository<Qrcode, Integer> {
}