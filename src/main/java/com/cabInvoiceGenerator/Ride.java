package com.cabInvoiceGenerator;

public class Ride {
    public final double distance;
    public final int time;
    public InvoiceGenerator.RideType rideType;

    public Ride(double distance, int time, InvoiceGenerator.RideType rideType) {
        this.distance = distance;
        this.time = time;
        this.rideType = rideType;
    }
}