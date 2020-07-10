package com.cabInvoiceGenerator;

import java.util.Arrays;

public class InvoiceGenerator {
    public enum RideType {
        NORMAL, PREMIUM
    }

    private static final double MIN_COST_PER_KM_NORMAL_RIDE = 10;
    private static final int COST_PER_TIME_NORMAL_RIDE = 1;
    private static final double MIN_FARE_NORMAL_RIDE = 5;

    private static final double MIN_COST_PER_KM_PREMIUM_RIDE = 15;
    private static final int COST_PER_TIME_PREMIUM_RIDE = 2;
    private static final double MIN_FARE_PREMIUM_RIDE = 20;

    private RideRepository rideRepository;

    public InvoiceGenerator() {
        this.rideRepository = new RideRepository();
    }

    public double calculateFare(double distance, int time, RideType rideType) {
        return this.calculateBasedOnRideType(distance, time, rideType);
    }

    public InvoiceSummary calculateFare(Ride[] rides) {
        double totalFare = Arrays.stream(rides)
                .mapToDouble(ride -> this.calculateFare(ride.distance, ride.time, ride.rideType))
                .sum();
        return new InvoiceSummary(rides.length, totalFare);
    }

    public void addRides(String userId, Ride[] rides) {
        rideRepository.addRides(userId, rides);
    }

    public InvoiceSummary getInvoiceSummary(String userId) {
        return this.calculateFare(rideRepository.getRides(userId));
    }

    public double calculateBasedOnRideType(double distance, int time, RideType rideType) {
        switch (rideType) {
            case PREMIUM:
                return Math.max(distance * MIN_COST_PER_KM_PREMIUM_RIDE + time * COST_PER_TIME_PREMIUM_RIDE,
                        MIN_FARE_PREMIUM_RIDE);
            case NORMAL:
                return Math.max(distance * MIN_COST_PER_KM_NORMAL_RIDE + time * COST_PER_TIME_NORMAL_RIDE,
                        MIN_FARE_NORMAL_RIDE);
            default:
                return 0;
        }
    }
}
