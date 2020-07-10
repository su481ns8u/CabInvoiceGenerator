package com.cabInvoiceGenerator;

public class InvoiceSummary {

    public int numberOfRide;
    public double totalFare;
    public double averageFare;

    public InvoiceSummary(int numOfRides, double totalFair) {
        this.numberOfRide = numOfRides;
        this.totalFare = totalFare;
        this.averageFare = totalFare;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceSummary that = (InvoiceSummary) o;
        return numberOfRide == that.numberOfRide &&
                Double.compare(that.totalFare, totalFare) == 0 &&
                Double.compare(that.averageFare, averageFare) == 0;
    }
}
