package com.parth.example.tracking_service.repository;

import com.parth.example.tracking_service.entity.TrackingRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrackingRepository
        extends JpaRepository<TrackingRecord, Long> {

    List<TrackingRecord>
    findByShipmentId(Long shipmentId);
}