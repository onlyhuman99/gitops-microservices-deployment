package com.parth.example.tracking_service.exception;

public class TrackingRecordNotFoundException
        extends RuntimeException {

    public TrackingRecordNotFoundException(Long shipmentId) {
        super("No tracking records found for shipment ID: "
                + shipmentId);
    }
}