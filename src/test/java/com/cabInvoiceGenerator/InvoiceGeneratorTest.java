package com.cabInvoiceGenerator;

import org.junit.Assert;
import org.junit.Test;

public class InvoiceGeneratorTest {
    @Test
    public void givenDistanceAndTime_ShouldReturnTotalFare() {
        InvoiceGenerator invoiceGenerator = new InvoiceGenerator();
        double distance = 2.0;
        int time = 5;
        double fare = invoiceGenerator.calculateFare(distance, time);
        Assert.assertEquals(25, fare,0.0);
    }

    @Test
    public void givenLessDistanceAndTime_ShouldReturnMinimumFare() {
        InvoiceGenerator invoiceGenerator = new InvoiceGenerator();
        double distance = 0.1;
        int time = 1;
        double fare = invoiceGenerator.calculateFare(distance, time);
        Assert.assertEquals(5, fare,0.0);
    }
    @Test
    public void givenMultipleRides_ShouldReturnTotalFare() {
        InvoiceGenerator invoiceGenerator = new InvoiceGenerator();
        Ride[] rides = {
                new Ride(2.0, 5),
                new Ride(0.1,1),
                new Ride(10,6)  };
        InvoiceSummary summary = invoiceGenerator.calculateFare(rides);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(3, 136.0);
        Assert.assertEquals(expectedInvoiceSummary,summary);
    }

}
